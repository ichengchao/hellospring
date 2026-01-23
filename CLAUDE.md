# CLAUDE.md - AI Assistant Guide for hellospring

## Project Overview

**hellospring** is a Spring Boot 3.0.0 demonstration application written in Java 17. It serves as a testing and development platform for system introspection, CPU load testing, and Alibaba Cloud (Aliyun) integration with support for Kubernetes deployment.

## Quick Start

```bash
# Build the project
mvn clean package

# Run locally
java -jar target/hellospring.jar

# Run with Docker profile (excludes local application.properties)
mvn clean package -Pdocker

# Access the application
# Main app: http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui/index.html
# Actuator: http://localhost:8080/actuator
```

## Project Structure

```
hellospring/
├── src/main/java/name/chengchao/hellospring/
│   ├── MyApplication.java           # Spring Boot entry point
│   ├── controller/
│   │   ├── SampleController.java    # Main REST endpoints (/, /hello, /env, /system)
│   │   ├── CpuController.java       # CPU stress testing (/cpu/eat)
│   │   └── PersonController.java    # CRUD endpoints (disabled)
│   ├── service/
│   │   ├── AliyunAPIService.java    # Aliyun STS/RRSA integration
│   │   ├── AliyunCredentiailsDemo.java # Credentials demo
│   │   ├── PersonService.java       # Person business logic (disabled)
│   │   └── PersonRepository.java    # Data access (disabled)
│   ├── model/
│   │   └── Person.java              # Data model (disabled)
│   └── util/
│       ├── LocalInfoUtils.java      # Network/hostname utilities
│       ├── ExecutorFactory.java     # Thread pool management
│       ├── NamedThreadFactory.java  # Custom thread naming
│       ├── OkHttpClientUtils.java   # HTTP client wrapper
│       ├── UUIDUtils.java           # UUID generation
│       └── DateFormatter.java       # Date formatting
├── src/main/resources/
│   ├── application.properties       # Main configuration
│   └── hellospring.properties       # Build metadata (populated by CI)
├── src/config/docker/resources/     # Docker-specific configs
├── kubernetes/
│   └── deploy.yaml                  # Kubernetes deployment (3 replicas)
├── disk/                            # Build artifacts directory
├── .github/workflows/
│   └── maven.yml                    # CI/CD pipeline
├── Dockerfile                       # Container build (OpenJDK 8)
├── Dockerfile-Aone                  # Alternative deployment variant
└── pom.xml                          # Maven configuration
```

## Key API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | GET | Health check / welcome message |
| `/sayhello/{name}` | GET | Simple greeting with name |
| `/hello` | GET | System info (IPs, hostname, build properties) |
| `/env` | GET | Environment variables |
| `/system` | GET | Java system properties |
| `/getCaller` | GET | Aliyun RRSA identity (OIDC-based) |
| `/getCallerTest` | GET | Aliyun STS test |
| `/cpu/eat?duration={s}&cpuUtil={%}` | GET | CPU stress test |

### Actuator Endpoints

Available at `/actuator/*`:
- `/actuator/health` - Health status
- `/actuator/info` - Application info
- `/actuator/env` - Environment properties
- `/actuator/threaddump` - Thread dump
- `/actuator/beans` - Spring beans
- `/actuator/loggers` - Logger levels

## Technology Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 3.0.0 | Core framework |
| Java | 17 | Language version |
| Spring Boot Web | (managed) | REST API |
| Spring Boot Actuator | (managed) | Monitoring |
| SpringDoc OpenAPI | 2.3.0 | Swagger UI |
| FastJSON2 | 2.0.43 | JSON serialization |
| Aliyun Credentials | 0.3.0 | Cloud authentication |
| Aliyun STS | 1.1.4 | Security Token Service |
| Commons Lang | 2.5 | String utilities |
| Commons IO | 2.6 | IO utilities |

## Build System

### Maven Commands

```bash
# Standard build
mvn clean package

# Build with Docker profile (uses docker-specific configs)
mvn clean package -Pdocker

# Run tests only
mvn test

# Skip tests
mvn package -DskipTests
```

### Build Output

The build produces `target/hellospring.jar` - a self-contained executable JAR file.

## Development Conventions

### Package Structure

- **controller/**: REST API endpoints annotated with `@RestController`
- **service/**: Business logic annotated with `@Service`
- **model/**: Data entities
- **util/**: Static utility classes

### Code Patterns

1. **Controllers**: Use `@RestController` with `@GetMapping`/`@PostMapping`
2. **Services**: Inject with `@Autowired`, annotate with `@Service`
3. **Configuration**: Use `application.properties` for settings
4. **Utilities**: Prefer static methods in utility classes

### Naming Conventions

- Classes: PascalCase (e.g., `SampleController`)
- Methods: camelCase (e.g., `getCaller()`)
- Constants: UPPER_SNAKE_CASE
- Packages: lowercase (e.g., `name.chengchao.hellospring`)

## Configuration

### application.properties

```properties
server.port: 8080
management.endpoints.web.exposure.include=info,health,env,threaddump,beans,loggers
management.endpoint.env.show-values=always
```

### Profiles

- **default**: Local development (uses `src/main/resources/application.properties`)
- **docker**: Container deployment (uses `src/config/docker/resources/application.properties`)

## CI/CD Pipeline

The GitHub Actions workflow (`.github/workflows/maven.yml`) triggers on tags matching `v1*`:

1. **Build**: Compiles with Maven, injects build metadata
2. **Package**: Creates executable JAR
3. **Docker**: Builds and pushes to Aliyun Container Registry
4. **Deploy**: Applies Kubernetes manifests via kubectl
5. **Notify**: Sends DingTalk notification

### Required Secrets

- `aliyun_username` / `aliyun_key` - Aliyun registry credentials
- `KUBE_CONFIG_DATA` - Kubernetes cluster config
- `dingding_access_token` - DingTalk notification token

## Docker

### Build Image

```bash
# Build locally
mvn clean package
docker build -t hellospring:latest .

# Run container
docker run -p 8080:8080 hellospring:latest
```

### Container Details

- Base image: `openjdk:8`
- Exposed port: `8080`
- Timezone: `Asia/Shanghai`
- Entry point: `/root/init.sh`

## Kubernetes Deployment

```bash
# Apply deployment (after replacing ${version})
kubectl apply -f kubernetes/deploy.yaml
```

The deployment creates 3 replicas with resource limits.

## Important Notes for AI Assistants

### When Modifying Code

1. **Java Version**: Target Java 17 features when writing new code
2. **Spring Boot 3**: Use Jakarta EE namespaces (`jakarta.*` not `javax.*`)
3. **Testing**: No test framework is currently configured
4. **Database**: MySQL/JDBC support is commented out - do not enable without explicit request

### Disabled Features

The following are intentionally disabled (commented out in code):
- MySQL database connectivity
- `PersonController`, `PersonService`, `PersonRepository` - CRUD operations
- `Person` model JPA annotations

### File Locations

- Main application entry: `src/main/java/name/chengchao/hellospring/MyApplication.java`
- Configuration: `src/main/resources/application.properties`
- Build config: `pom.xml`
- Docker config: `Dockerfile`
- K8s config: `kubernetes/deploy.yaml`

### Build Artifacts

- JAR output: `target/hellospring.jar`
- Deployment JAR: `disk/hellospring.jar` (copied during CI)

## Common Tasks

### Add a New REST Endpoint

1. Create or modify a controller in `src/main/java/name/chengchao/hellospring/controller/`
2. Add `@GetMapping` or `@PostMapping` annotations
3. Inject services with `@Autowired` if needed
4. Rebuild with `mvn clean package`

### Add a New Dependency

1. Add dependency to `pom.xml` in the `<dependencies>` section
2. Run `mvn clean package` to download and verify

### Modify Configuration

1. Edit `src/main/resources/application.properties`
2. For Docker-specific config, also update `src/config/docker/resources/application.properties`

### Debug Locally

```bash
# Run with debug port
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar target/hellospring.jar
```

## Links

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- Actuator: http://localhost:8080/actuator
- Health Check: http://localhost:8080/actuator/health
