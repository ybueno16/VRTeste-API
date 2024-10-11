package com.br.testeVr;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class TesteVrApplication {

	public static void main(String[] args) throws IOException {
		Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));

		String ip = props.getProperty("postgres.ip");
		String port = props.getProperty("postgres.port");
		String driver = props.getProperty("postgres.driver");
		String database = props.getProperty("postgres.database");
		String username = props.getProperty("postgres.username");
		String password = props.getProperty("postgres.password");

		Flyway flyway = Flyway.configure().dataSource(driver + "://" + ip + ":" + port + "/" + database, username, password)
				.locations("classpath:db/migrations")
				.schemas("public")
				.table("flyway_schema_history")
				.baselineOnMigrate(true)
				.load();
		try {
			flyway.migrate();
			System.out.println("Migration successful!");
		} catch (FlywayException e) {
			System.err.println("Migration failed: " + e.getMessage());
		}

		SpringApplication.run(TesteVrApplication.class, args);
	}

}
