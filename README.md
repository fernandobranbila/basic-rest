# basic-rest

## Tecnologias

 * Gradle
 * Kotlin
 * JdbcTemplate
 * Spring Boot
 * Redis
 * Postgres
  
 ## API
 
  * Cadastrar um prato -> ``` v1/foods``
  * Encontrar um prato  -> ``` v1/farms/foods ```
      
    Modelos podem ser consultados em **host da aplicação**/swagger-ui.html
      
## Como rodar

### Testes
  ``` ./gradlew test ```

### Localmente

 Dependecias: Gradle 7.6+, Kotlin 1.8+, JVM 17, Postgres e Redis

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
