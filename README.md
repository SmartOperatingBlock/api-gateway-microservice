# API Gateway Microservice

![Release](https://github.com/smartoperatingblock/api-gateway-microservice/actions/workflows/build-and-deploy.yml/badge.svg?style=plastic)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=plastic)](https://opensource.org/licenses/MIT)
![Version](https://img.shields.io/github/v/release/smartoperatingblock/api-gateway-microservice?style=plastic)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_api-gateway-microservice&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_surgical-process-monitoring-microservice)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_api-gateway-microservice&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_surgical-process-monitoring-microservice)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_api-gateway-microservice&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_surgical-process-monitoring-microservice)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_api-gateway-microservice&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_surgical-process-monitoring-microservice)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_api-gateway-microservice&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_surgical-process-monitoring-microservice)

The **API Gateway** microservice responsible to handle clients requests and to route them to the appropriate service.

## Usage
1. Provide a `.env` file with the following variables:
    - `BOOTSTRAP_SERVER_URL`: the kafka connection endpoint
    - `SCHEMA_REGISTRY_URL`: the schema registry url
   
2. Run the container with the command:
    ```bash
    docker run ghcr.io/smartoperatingblock/api-gateway-microservice:latest

## Documentation
- Check out the website [here](https://smartoperatingblock.github.io/api-gateway-microservice)
- Check out the _REST-API_ documentation [here](https://smartoperatingblock.github.io/api-gateway-microservice/documentation/openapi-doc)
- Check out the _Async-API_ documentation [here](https://smartoperatingblock.github.io/api-gateway-microservice/documentation/asyncapi-doc)
- Check out the Code documentation here [here](https://smartoperatingblock.github.io/surgical-process-monitoring-microservice/documentation/code-doc)
