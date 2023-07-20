# basic-rest

Esse projeto é um template funcional utilizando spring boot 3.1, redis e postgres

## Tecnologias

 * Gradle
 * Kotlin
 * JdbcTemplate
 * Spring Boot 3
 * Redis
 * Postgres
  
 ## API
 
  * Cadastrar um alimento -> ``` POST /api/v1/foods```
  * Encontrar um alimento  -> ``` GET /api/v1/foods/{id} ```
      
    Modelos podem ser consultados em **host da aplicação**/swagger-ui.html
      
## Como rodar

### Testes
  ``` ./gradlew test ```

### Localmente

 Dependecias: Gradle 7.6+, Kotlin 1.8+, JVM 17, Postgres 12 e Redis 7

  ``` gradle build ```

  ``` ./gradlew bootRun ```
 
### Containers

 Dependencias: Docker e Docker-compose

  ``` docker-compose build```
  
  ``` docker-compose up```
  
  Porta default do app é 9011
  
  
  ## To-do Melhorias
  - [ ] Aplicar coroutines p/ concorrência e possivel paralelismo
  - [ ] Refatorar para não usar exceptions at all
  - [ ] Ajuste nas properties p/ ambiente dev e docker
  - [ ] Ajuste pastas command e query na camada de infrastructure
  - [ ] GraalVM p/ startup mais rápido
