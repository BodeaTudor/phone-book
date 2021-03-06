package org.example.web;

import org.example.config.ObjectMapperConfig;
import org.example.domain.PhoneBookItem;
import org.example.service.PhoneBookItemService;
import org.example.transfer.CreateItemRequest;
import org.example.transfer.UpdateItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/phone-book")
public class PhoneBookItemServlet extends HttpServlet {

    private PhoneBookItemService phoneBookItemService = new PhoneBookItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        CreateItemRequest request = ObjectMapperConfig.getObjectMapper().readValue(req.getReader(), CreateItemRequest.class);

        try {
            phoneBookItemService.createPhoneBookItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        try {
            List<PhoneBookItem> phoneBookItems = phoneBookItemService.getPhoneBookItems();
            String responseJson = ObjectMapperConfig.getObjectMapper().writeValueAsString(phoneBookItems);

            resp.getWriter().print(responseJson);
            resp.getWriter().flush();
            resp.getWriter().close();

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        String id = req.getParameter("id");

        UpdateItemRequest request = ObjectMapperConfig.getObjectMapper().readValue(req.getReader(), UpdateItemRequest.class);

        try {
            phoneBookItemService.updatePhoneBookItem(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        String id = req.getParameter("id");

        try {
            phoneBookItemService.deletePhoneBookItem(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}
