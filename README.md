Spring Boot Summary
- AutoWired: Including an instance of the Dao Service 
- ServletUriComponentsBuilder: Returning URI for objects created
- LinkBuilder: Returning links to other methods
- ApiModelProperty : Documenting the parameter types and examples
- JsonFilter: Filtering Responses
- CustomizedResponseEntityExceptionHandler: Generating org wide custom exceptions
- JPA: create data.sql, use localhost:8080/h2-console, set JDBC URL=jdbc:h2:mem:testdb
- Added Post to User, OneToMany relationship


Spring Cloud Summary
- ConfigServer : Maintain application config for multiple env (Dev, Test, Stage, Prod) in git, refresh config
- Eureka Naming Server : Services register, resolves service name to instance URL(round robin)
- Feign : make REST requests, setup to use Ribbon
- Ribbon : Load balanced inter service call, uses Eureka to make calls to service by name
- Zuul : API gateway(Auth, Tracing, Service Aggr, Fault Tolerance, Rate Limits)
- Zipkin : Track requests across services
- RabbitMQ : Bus for Zipkin to send/receive events
- Hysterix : Fault Tolerance
