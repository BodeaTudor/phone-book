package org.example.persistence;

import org.example.config.DatabaseConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhoneBookItemRepository {

    public void createItem(String firstName, String lastName, String phoneNumber) throws SQLException, IOException, ClassNotFoundException {
        String insertSql = "INSERT INTO phone_book_items(first_name, last_name, phone_number) VALUES (?,?,?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)
        ) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);

            preparedStatement.executeUpdate();
        }

    }
}
