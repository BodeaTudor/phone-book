package org.example.persistence;

import org.example.config.DatabaseConfig;
import org.example.domain.PhoneBookItem;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookItemRepository {

    public void createItem(String firstName, String lastName, int phoneNumber) throws SQLException, IOException, ClassNotFoundException {
        String insertSql = "INSERT INTO phone_book_items(first_name, last_name, phone_number) VALUES (?,?,?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)
        ) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, phoneNumber);

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

    public void updateItem(long id, int phoneNumber) throws SQLException, IOException, ClassNotFoundException {

        String sql = "UPDATE phone_book_items SET phone_number = ? WHERE id = ? ";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, phoneNumber);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteItem(long id) throws SQLException, IOException, ClassNotFoundException {

        String sql = "DELETE FROM phone_book_items WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)

        ) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }
}
