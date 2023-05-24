/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.webclient

import application.presenter.api.process.SurgicalProcessDto
import application.presenter.api.process.toSurgicalProcess
import application.presenter.api.report.SurgeryReportEntryDto
import application.presenter.api.report.SurgeryReportInfoDto
import application.presenter.api.report.toSurgeryNameInfo
import application.presenter.api.report.toSurgeryReportEntry
import application.presenter.api.report.toSurgeryReportIngrationDto
import application.presenter.api.room.RoomPresentation
import application.presenter.api.room.RoomSerialization.toRoom
import application.presenter.api.tracking.RoomsTrackingDataDto
import application.presenter.api.user.HealthProfessionalDto
import application.presenter.api.user.toUserDto
import application.presenter.api.util.ApiResponses
import application.presenter.api.util.ApiResponses.ResponseEntryList
import entity.process.SurgicalProcess
import entity.report.SurgeryReportEntry
import entity.report.SurgeryReportInfo
import entity.report.SurgeryReportIntegration
import entity.room.Room
import entity.room.RoomData
import entity.tracking.HealthProfessionalId
import entity.tracking.HealthProfessionalName
import entity.tracking.HealthProfessionalRole
import entity.tracking.HealthProfessionalSurname
import entity.tracking.HealthProfessionalTrackingInfo
import entity.tracking.RoomId
import entity.user.User
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import usecase.repository.AuthenticationRepository
import usecase.repository.RoomRepository
import usecase.repository.SurgeryReportRepository
import usecase.repository.SurgicalProcessRepository
import usecase.repository.TrackingRepository

/** The Web Client responsible to make HTTP request to other microservices. */
class WebClient(vertx: Vertx) :
    AuthenticationRepository,
    SurgeryReportRepository,
    RoomRepository,
    SurgicalProcessRepository,
    TrackingRepository {

    init {
        MICROSERVICES_URL.forEach {
            checkNotNull(it) {
                "Invalid Microservice URL!"
            }
        }
    }

    private val client: WebClient = WebClient.create(vertx)

    override fun getUserAuthentication(user: User): Future<Boolean> =
        client.postAbs("$UMI_URI/auth").sendJson(Json.encodeToString(user.toUserDto())).map {
            it.statusCode() == HttpResponseStatus.OK.code()
        }

    override fun getRooms(): Future<List<Room>> =
        client.getAbs("$BM_URI/rooms").send().map {
            Json.decodeFromString<ResponseEntryList<ApiResponses.ResponseEntry<RoomPresentation.RoomEntryDto>>>(
                it.bodyAsString(),
            ).entries.map { responseEntry ->
                responseEntry.entry.toRoom()
            }
        }

    override fun getSurgeryReportArchive(): Future<List<SurgeryReportEntry>> =
        client.getAbs("$SR_URI/reports").send().map { res ->
            Json.decodeFromString<List<SurgeryReportEntryDto>>(res.bodyAsString()).map { sr ->
                sr.toSurgeryReportEntry()
            }
        }

    override fun getSurgeryReportInfo(processId: String): Future<SurgeryReportInfo> =
        client.getAbs("$SR_URI/reports/$processId").send().map { res ->
            Json.decodeFromString<SurgeryReportInfoDto>(res.bodyAsString()).toSurgeryNameInfo()
        }

    override fun integrateReport(surgeryReportIntegration: SurgeryReportIntegration): Future<Boolean> =
        client.patchAbs("$SR_URI/reports")
            .sendJson(Json.encodeToString(surgeryReportIntegration.toSurgeryReportIngrationDto())).map {
                it.statusCode() == HttpResponseStatus.OK.code()
            }

    override fun getRoomEnvironmentalInfo(roomId: RoomData.RoomId): Future<Room> =
        client.getAbs("$BM_URI/rooms/${roomId.id}").send().map {
            Json.decodeFromString<RoomPresentation.RoomDto>(
                it.bodyAsString(),
            ).toRoom()
        }

    override fun getSurgicalProcessInfoByRoomId(
        preOperatingRoomId: RoomData.RoomId?,
        operatingRoomId: RoomData.RoomId?,
    ): Future<SurgicalProcess?> =
        client.getAbs("$SPMS_URI/processes").send().map {
            Json.decodeFromString<ResponseEntryList<SurgicalProcessDto>>(
                it.bodyAsString(),
            ).entries.map { processDto ->
                processDto.toSurgicalProcess()
            }.firstOrNull { process ->
                process.preOperatingRoom.id.id == preOperatingRoomId?.id ||
                    process.operatingRoom.id.id == operatingRoomId?.id
            }
        }

    override fun getZoneHealthProfessionalTrackingInfo(
        preOperatingRoomId: String,
        operatingRoomId: String,
    ): Pair<Future<List<Future<HealthProfessionalTrackingInfo>>>,
        Future<List<Future<HealthProfessionalTrackingInfo>>>,> {
        val operatingTrackingData = client.getAbs("$ST_URI/rooms-tracking-data/$operatingRoomId").send().map {
            Json.decodeFromString<RoomsTrackingDataDto>(it.bodyAsString())
        }.map {
            it.entries.map { en ->
                getHealthProfessionalInfo(en.healthProfessionalId, en.roomId)
            }
        }
        val preOperatingTrackingData = client.getAbs("$ST_URI/rooms-tracking-data/$preOperatingRoomId").send().map {
            Json.decodeFromString<RoomsTrackingDataDto>(it.bodyAsString())
        }.map {
            it.entries.map { en ->
                getHealthProfessionalInfo(en.healthProfessionalId, en.roomId)
            }
        }
        return Pair(operatingTrackingData, preOperatingTrackingData)
    }

    private fun getHealthProfessionalInfo(hpId: String, roomId: String): Future<HealthProfessionalTrackingInfo> {
        return client.getAbs("$UMI_URI/healthProfessionals/$hpId").send().map {
            val hp = Json.decodeFromString<HealthProfessionalDto>(it.bodyAsString())
            println("hp name = ${hp.name}")
            HealthProfessionalTrackingInfo(
                HealthProfessionalId(hpId),
                HealthProfessionalName(hp.name),
                HealthProfessionalSurname(hp.surname),
                HealthProfessionalRole(hp.role),
                RoomId(roomId),
            )
        }
    }

    override fun getBlockHealthProfessionalTrackingInfo(): Future<List<Future<HealthProfessionalTrackingInfo>>> =
        client.getAbs("$ST_URI/block-tracking-data").send().map {
            Json.decodeFromString<RoomsTrackingDataDto>(it.bodyAsString())
        }.map {
            it.entries.map { en ->
                getHealthProfessionalInfo(en.healthProfessionalId, en.roomId)
            }
        }

    companion object {
        private val UMI_URI = System.getenv("USER_MANAGEMENT_MICROSERVICE_URL")
        private val BM_URI = System.getenv("BUILDING_MANAGEMENT_MICROSERVICE_URL")
        private val SR_URI = System.getenv("SURGERY_REPORT_MICROSERVICE_URL")
        private val SPMS_URI = System.getenv("SURGICAL_PROCESS_MONITORING_MICROSERVICE_URL")
        private val ST_URI = System.getenv("STAFF_TRACKING_MICROSERVICE_URL")

        private val MICROSERVICES_URL = listOf<String?>(
            UMI_URI,
            BM_URI,
            SR_URI,
            SPMS_URI,
            ST_URI,
        )
    }
}
