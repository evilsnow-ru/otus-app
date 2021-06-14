package ru.evilsnow.otususers;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class OtusUsersApplication {

    private static final Logger log = LoggerFactory.getLogger(OtusUsersApplication.class);

    public static void main(String[] args) {
        boolean isMigrate = Boolean.parseBoolean(System.getenv().getOrDefault("MIGRATE", "false"));

        if (isMigrate) {
            System.out.println("Prepare migration...");

            Map<String, String> env = System.getenv();

            Flyway flyway = Flyway
                    .configure()
                    .dataSource(
                            env.getOrDefault("SPRING_DATASOURCE_URL","jdbc:postgresql://host.docker.internal:5432/otus"),
                            env.getOrDefault("SPRING_DATASOURCE_USER", "otus"),
                            env.getOrDefault("SPRING_DATASOURCE_PASSWORD", "otus")
                    )
                    .load();

            flyway.migrate();

            System.out.println("Migrate done.");
        } else {
            SpringApplication.run(OtusUsersApplication.class, args);
        }
    }

}

