mkdir -p hr-service/src/main/java/uk/co/jb303/domain
mkdir -p hr-service/src/main/java/uk/co/jb303/repository
mkdir -p hr-service/src/main/java/uk/co/jb303/controller
mkdir -p hr-service/src/main/resources/db/changelog

# 1. Create Maven POM (Using Micronaut BOM)
cat <<EOF > hr-service/pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>uk.co.jb303</groupId>
    <artifactId>hr-service</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <release.version>25</release.version>
        <micronaut.version>4.7.4</micronaut.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.micronaut.platform</groupId>
                <artifactId>micronaut-platform</artifactId>
                <version>\${micronaut.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>io.micronaut</groupId>
            <artifactId>micronaut-http-server-netty</artifactId>
        </dependency>
        <dependency>
            <groupId>io.micronaut.serde</groupId>
            <artifactId>micronaut-serde-jackson</artifactId>
        </dependency>
        <dependency>
            <groupId>io.micronaut.data</groupId>
            <artifactId>micronaut-data-hibernate-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>io.micronaut.sql</groupId>
            <artifactId>micronaut-jdbc-hikari</artifactId>
        </dependency>
        <dependency>
            <groupId>io.micronaut.liquibase</groupId>
            <artifactId>micronaut-liquibase</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.micronaut.openapi</groupId>
            <artifactId>micronaut-openapi-annotations</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <scope>runtime</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>io.micronaut.maven</groupId>
                <artifactId>micronaut-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <annotationProcessorPaths>
                        <path><groupId>io.micronaut</groupId><artifactId>micronaut-inject-java</artifactId></path>
                        <path><groupId>io.micronaut.data</groupId><artifactId>micronaut-data-processor</artifactId></path>
                        <path><groupId>io.micronaut.openapi</groupId><artifactId>micronaut-openapi</artifactId></path>
                        <path><groupId>io.micronaut.serde</groupId><artifactId>micronaut-serde-processor</artifactId></path>
                    </annotationProcessorPaths>
                    <compilerArgs>
                        <arg>-Amicronaut.openapi.views.spec=swagger-ui.enabled=true,swagger-ui.theme=material</arg>
                    </compilerArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
EOF

# 2. Create YAML Application Config
cat <<EOF > hr-service/src/main/resources/application.yml
micronaut:
  application:
    name: hr-service
  router:
    static-resources:
      swagger:
        paths: classpath:META-INF/swagger
        mapping: /swagger/**

datasources:
  default:
    url: jdbc:h2:mem:hr_db;DB_CLOSE_DELAY=-1;MODE=Oracle
    driverClassName: org.h2.Driver
    username: sa
    password: ""

liquibase:
  datasources:
    default:
      change-log: 'classpath:db/changelog/db.changelog-master.yaml'
EOF

# 3. Create YAML Liquibase Changelog
cat <<EOF > hr-service/src/main/resources/db/changelog/db.changelog-master.yaml
databaseChangeLog:
  - changeSet:
      id: 1
      author: jb303
      changes:
        - createTable:
            tableName: EMPLOYEES
            columns:
              - column:
                  name: employee_id
                  type: INT
                  autoIncrement: true
                  constraints:
                    primaryKey: true
              - column:
                  name: first_name
                  type: VARCHAR(255)
              - column:
                  name: last_name
                  type: VARCHAR(255)
                  constraints:
                    nullable: false
              - column:
                  name: email
                  type: VARCHAR(255)
                  constraints:
                    unique: true
EOF

# 4. Create Java Application Entry
cat <<EOF > hr-service/src/main/java/uk/co/jb303/Application.java
package uk.co.jb303;
import io.micronaut.runtime.Micronaut;

public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
EOF

# 5. Create Entity (Java 25 Record)
cat <<EOF > hr-service/src/main/java/uk/co/jb303/domain/Employee.java
package uk.co.jb303.domain;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Serdeable
@Entity
@Table(name = "EMPLOYEES")
public record Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer employeeId,
    String firstName,
    String lastName,
    String email
) {}
EOF

# 6. Create Repository
cat <<EOF > hr-service/src/main/java/uk/co/jb303/repository/EmployeeRepository.java
package uk.co.jb303.repository;
import uk.co.jb303.domain.Employee;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
EOF

# 7. Create Controller with Swagger and File Download
cat <<EOF > hr-service/src/main/java/uk/co/jb303/controller/EmployeeController.java
package uk.co.jb303.controller;

import uk.co.jb303.domain.Employee;
import uk.co.jb303.repository.EmployeeRepository;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.types.files.StreamedFile;
import io.swagger.v3.oas.annotations.Operation;
import java.io.ByteArrayInputStream;
import java.util.List;

@Controller("/employees")
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Get("/")
    @Operation(summary = "List all employees")
    public List<Employee> list() {
        return repository.findAll();
    }

    @Post("/")
    public Employee save(@Body Employee employee) {
        return repository.save(employee);
    }

    @Get("/download")
    @Operation(summary = "Download employees as CSV")
    public StreamedFile download() {
        StringBuilder csv = new StringBuilder("ID,Name,Email\n");
        repository.findAll().forEach(e ->
            csv.append(e.employeeId()).append(",").append(e.lastName()).append(",").append(e.email()).append("\n")
        );
        return new StreamedFile(new ByteArrayInputStream(csv.toString().getBytes()), "employees.csv");
    }
}
EOF

# ZIP the project
zip -r hr-service.zip hr-service/
echo "--------------------------------------------------"
echo "Project created and zipped into: hr-service.zip"
echo "To build the JAR, run: cd hr-service && mvn package"
echo "--------------------------------------------------"