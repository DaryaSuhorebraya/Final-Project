package by.epam.movierating.controller;

import by.epam.movierating.command.Command;
import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Даша on 14.02.2017.
 */
public class Controller extends HttpServlet {
    private static final String COMMAND = "command";

    public Controller() {
        super();
       /* ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandString = request.getParameter(COMMAND);
        Command command = CommandProvider.getInstance().getCommand(commandString);
        command.execute(request, response);
    }
}
