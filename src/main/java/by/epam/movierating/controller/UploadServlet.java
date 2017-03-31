package by.epam.movierating.controller;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.ParameterName;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Даша on 27.03.2017.
 */
public class UploadServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UploadServlet.class);
    private static final String TEMP_DIR = "javax.servlet.context.tempdir";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            logger.error("Request is not multipart");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);
        File tempDir = (File) getServletContext().getAttribute(TEMP_DIR);
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024 * 10);

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandString = request.getParameter(ParameterName.COMMAND);
        Command command = CommandProvider.getInstance().getCommand(commandString);
        command.execute(request, response);
    }

}
