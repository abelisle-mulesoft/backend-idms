The Identity Management microservice is locked down and not accessible directly. We leverage Anypoint Flex Gateway to manage and secure access. More specifically, the Identity Management microservice is protected using a [Client ID Enforcement Policy](https://docs.mulesoft.com/gateway/latest/policies-included-client-id-enforcement), which allows access only to authorized client applications. To access it, you must include the following key-value pair in the request header:

| Key | Value |
| ------ | ------ |
| client_id | The client ID associated to your registered client application |
| client_secret | The client secret associated to your registered client application |

