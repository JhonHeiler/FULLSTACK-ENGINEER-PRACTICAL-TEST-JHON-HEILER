package com.example.alianzabackend;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@SpringBootApplication
public class AlianzaBackendApplication {
	@Autowired
	private DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(AlianzaBackendApplication.class, args);
	}

	@PostConstruct
	public void initializeDatabase() throws Exception {
		try (Connection conn = dataSource.getConnection();
			 Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("INSERT INTO CLIENT (EMAIL,END_DATE,NAME,PHONE,SHARED_KEY,START_DATE) VALUES ('jhon.heiler@gmail.com','2024-02-14','Jhon Heiler','3234960276','jMosquera','2024-02-15')");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
