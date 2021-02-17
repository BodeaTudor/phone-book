package org.example.persistence;

import org.example.config.DatabaseConfig;
import org.example.domain.PhoneBookItem;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<PhoneBookItem> getItems() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT id, first_name, last_name, phone_number FROM phone_book_items";

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);

            List<PhoneBookItem> phoneBookItems = new ArrayList<>();

            while (resultSet.next()) {
                PhoneBookItem item = new PhoneBookItem();
                item.setId(resultSet.getLong("id"));
                item.setFirstName(resultSet.getString("first_name"));
                item.setLastName(resultSet.getString("last_name"));
                item.setPhoneNumber(resultSet.getInt("phone_number"));

                phoneBookItems.add(item);
            }
            return phoneBookItems;
        }
    }
}
