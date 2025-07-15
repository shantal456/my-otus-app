package com.example.my_otus_app.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.my_otus_app.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UUID register(String firstName, String secondName, String birthdate, String biography, String city, String password) {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "INSERT INTO users (id, first_name, second_name, birthdate, biography, city, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Generate a UUID for the user_id
            UUID userId = UUID.randomUUID();

            String hashedPassword = passwordEncoder.encode(password);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Или другой формат, если нужно
            LocalDate localDate = LocalDate.parse(birthdate, formatter);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setObject(1, userId); // Use setObject for UUID compatibility
                pstmt.setString(2, firstName);
                pstmt.setString(3, secondName);
                pstmt.setDate(4, Date.valueOf(localDate));
                pstmt.setString(5, biography);
                pstmt.setString(6, city);
                pstmt.setString(7, hashedPassword);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("User data saved successfully!");
                    return userId; // Return the generated userId
                } else {
                    System.err.println("Failed to save user data.");
                    return null; // Or throw an exception
                }
            }

        } catch (SQLException e) {
            System.err.println("Error saving user data: " + e.getMessage());
            return null; // Or throw an exception
        }
    }

    public Optional<User> getUser(UUID id) {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT * FROM users WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                String userId = resultSet.getObject("id").toString();
                String userFirstName = resultSet.getString("first_name");
                String userSecondName = resultSet.getString("second_name");
                LocalDate userBirthdate = resultSet.getDate("birthdate").toLocalDate();
                String userBiography = resultSet.getString("biography");
                String userCity = resultSet.getString("city");
                // Создание объекта User
                return Optional.of(new User(userId, userFirstName, userSecondName, userBirthdate, userBiography, userCity));
            } else {
                // Пользователь с таким ID не найден
                return Optional.empty();
            }


        }
         catch (SQLException e) {
            System.err.println("Error getting user data: " + e.getMessage());
            return null;
        }
    }
}
