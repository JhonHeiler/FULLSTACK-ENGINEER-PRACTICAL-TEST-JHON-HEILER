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
			stmt.executeUpdate("INSERT INTO CLIENT (EMAIL,END_DATE,NAME,PHONE,SHARED_KEY,START_DATE) VALUES ('jhon.heiler@gmail.com','2024-02-14','Jhon Heiler','3241687435','jMosquera','2024-02-15')");

			// Agregar usuarios adicionales
			stmt.executeUpdate("INSERT INTO CLIENT (EMAIL,END_DATE,NAME,PHONE,SHARED_KEY,START_DATE) VALUES ('user1@example.com','2024-03-01','User One','1234567890','user1key','2024-03-01')");
			stmt.executeUpdate("INSERT INTO CLIENT (EMAIL,END_DATE,NAME,PHONE,SHARED_KEY,START_DATE) VALUES ('user2@example.com','2024-03-02','User Two','2345678901','user2key','2024-03-02')");
			stmt.executeUpdate("INSERT INTO CLIENT (EMAIL,END_DATE,NAME,PHONE,SHARED_KEY,START_DATE) VALUES ('user3@example.com','2024-03-03','User Three','3456789012','user3key','2024-03-03')");
			stmt.executeUpdate("INSERT INTO CLIENT (EMAIL,END_DATE,NAME,PHONE,SHARED_KEY,START_DATE) VALUES ('user4@example.com','2024-03-04','User Four','4567890123','user4key','2024-03-04')");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
