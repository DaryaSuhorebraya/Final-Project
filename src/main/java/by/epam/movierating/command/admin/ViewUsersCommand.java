package by.epam.movierating.command.admin;

import by.epam.movierating.bean.User;
import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.JSPPageName;
import by.epam.movierating.command.util.PagePathUtil;
import by.epam.movierating.service.UserService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Даша on 02.03.2017.
 */
public class ViewUsersCommand implements Command {
    private final static Logger logger=Logger.getLogger(ViewUsersCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PagePathUtil.setQueryString(request);
        HttpSession session = request.getSession(true);
        String language=(String) session.getAttribute(AttributeName.LANGUAGE);
        List<User> userList;

        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        UserService userService=serviceFactory.getUserService();
        try {
            userList=userService.getAllUsers();
            request.setAttribute(AttributeName.USERS,userList);
            request.getRequestDispatcher(JSPPageName.USERS_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
