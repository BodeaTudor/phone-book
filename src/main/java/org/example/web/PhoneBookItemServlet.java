package org.example.web;

import org.example.config.ObjectMapperConfig;
import org.example.service.PhoneBookItemService;
import org.example.transfer.CreateItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/phone-book")
public class PhoneBookItemServlet extends HttpServlet {

    private PhoneBookItemService phoneBookItemService = new PhoneBookItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreateItemRequest request = ObjectMapperConfig.getObjectMapper().readValue(req.getReader(), CreateItemRequest.class);

        try {
            phoneBookItemService.createPhoneBookItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }
}
