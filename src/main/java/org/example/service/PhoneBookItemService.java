package org.example.service;

import org.example.domain.PhoneBookItem;
import org.example.persistence.PhoneBookItemRepository;
import org.example.transfer.CreateItemRequest;
import org.example.transfer.UpdateItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhoneBookItemService {

    private PhoneBookItemRepository phoneBookItemRepository = new PhoneBookItemRepository();

    public void createPhoneBookItem(CreateItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating item: " + request);
        phoneBookItemRepository.createItem(request.getFirstName(), request.getLastName(), request.getPhoneNumber());
    }

    public List<PhoneBookItem> getPhoneBookItems() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving items...");
        return phoneBookItemRepository.getItems();
    }

    public void updatePhoneBookItem(long id, UpdateItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating item with id " + id + ": " + request);
        phoneBookItemRepository.updateItem(id, request.getPhoneNumber());
    }

    public void deletePhoneBookItem(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting item with id: " + id);
        phoneBookItemRepository.deleteItem(id);
    }
}