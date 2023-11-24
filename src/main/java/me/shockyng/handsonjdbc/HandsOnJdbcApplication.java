package me.shockyng.handsonjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class HandsOnJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandsOnJdbcApplication.class, args);
    }

    @PostMapping
    public void initDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "password");
             PreparedStatement preparedStatement = connection.prepareStatement("""
                     CREATE TABLE CARS (
                        ID INT NOT NULL,
                        NAME VARCHAR(50) NOT NULL,
                        LICENSE_PLATE VARCHAR(20) NOT NULL,
                        RELEASED_YEAR DATE
                     );
                     """)) {

            preparedStatement.execute();
        }
    }

    @PutMapping
    public void populateDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "password");
             PreparedStatement preparedStatement = connection.prepareStatement("""
                     INSERT INTO CARS (ID, NAME, LICENSE_PLATE, RELEASED_YEAR)
                     VALUES (1, 'ASD', 'ASD', NOW());
                     """)) {

            preparedStatement.execute();
        }
    }

}
