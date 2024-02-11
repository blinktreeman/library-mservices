# Library microservices

## Задание 
1. Восстановить пример, рассмотренный на уроке (запустить эврику и 2 сервиса; заставить их взаимодействовать)
Сдать скриншот страницы /eureka/apps с зарегистрированными приложениями.
На скрине должно быть видно оба сервиса (book-service, issuer-service)
2. Добавить третий сервис: сервис читателей.
Обогатить ручку GET /issue, чтобы она возвращала подробную информацию:
```json
[
  {
    "id": "733a8a9f-7fbf-4eb6-9900-f3338007d848",
    "issuedAt": "2024-11-28",
    "book": {
      "id": "78a0d4d5-67db-45f8-b846-da410f01aa11",
      "name": "Absalom, Absalom!",
      "author": {
        "id": "4deeeb5b-f263-4c5f-9c8c-62b83b0977ee",
        "firstName": "Justen",
        "lastName": "Huels"
      }
    },
    "reader": {
      "id": "78a0d4d5-67db-45f8-b846-da410f01bc34",
      "firstName": "Имя читателя",
      "lastName": "Фамилия читателя"
    }
  }
]
```
## Решение
### Добавлены сервисы
* book-service
* reader-service
* issue-service

Дополнительно добавлен gateway-server (Spring Cloud Gateway) для удобства составления запросов. 
Запросы к сервисам осуществляются через единую точку `http://localhost:8765`
### Добавлен Discovery server (Netflix Eureka)
Сервисы выступают клиентами Discovery server:

`application.yml` на примере book-service
```yaml
server:
  port: 0

spring:
  application:
    name: book-service
  datasource:
    url: jdbc:h2:mem:library_db
    username: user
    password: SecretPassword
    driver-class-name: org.h2.Driver

eureka:
  client:
    service-url:
      defaultZone: ${EUREKA_SERVER:http://localhost:8761/eureka}
  instance:
    instance-id: ${spring.application.name}:${random.uuid}
```
### Межсервисное взаимодействие
Для `issue-service` определен конфиг с WebClient

`IssueConfig.java`
```java
@Configuration
public class IssueConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebClient webClient(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        return WebClient
                .builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }
}
```
Часть сервиса IssueService с запросами к Book и Reader
`IssueService.java`
```java
...

public IssueResponse findById(UUID uuid) {
        Optional<Issue> issue = repository.findById(uuid);
        IssueResponse issueResponse = modelMapper.map(issue, IssueResponse.class);
        BookResponse bookResponse = webClient
                .get()
                .uri("http://book-service/api/v1/book?uuid=" + issue.get().getBookUuid())
                .retrieve()
                .bodyToMono(BookResponse.class).block();
        issueResponse.setBookResponse(bookResponse);
        ReaderResponse readerResponse = webClient
                .get()
                .uri("http://reader-service/api/v1/reader?uuid=" + issue.get().getReaderUuid())
                .retrieve()
                .bodyToMono(ReaderResponse.class)
                .block();
        issueResponse.setReaderResponse(readerResponse);
        return issueResponse;
    }
...
```



