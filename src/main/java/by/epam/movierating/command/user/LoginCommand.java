package by.epam.movierating.command.user;

import by.epam.movierating.bean.User;
import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.JSPPageName;
import by.epam.movierating.command.util.PagePathUtil;
import by.epam.movierating.service.UserService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.exception.ServiceWrongDataException;
import by.epam.movierating.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Даша on 14.02.2017.
 */
public class LoginCommand implements Command {
    private static final Logger logger=Logger.getLogger(LoginCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String context = request.getContextPath();
        String login=request.getParameter(AttributeName.LOGIN);
        byte[] password=request.getParameter(AttributeName.PASSWORD).getBytes();

        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        UserService userService=serviceFactory.getUserService();
        try {
            User user=userService.login(login,password);
            if (user!=null){
                session.setAttribute(AttributeName.USER_ID,user.getId());
                session.setAttribute(AttributeName.USER,user);
                if (user.isAdmin()){
                    session.setAttribute(AttributeName.ROLE,AttributeName.ADMIN);
                    response.sendRedirect(JSPPageName.REDIRECT_TO_WELC_PAGE);
                } else {
                    session.setAttribute(AttributeName.ROLE,AttributeName.USER);
                    String pagePath= PagePathUtil.getPagePath(session);
                    response.sendRedirect(pagePath);
                }
            }

        } catch (ServiceWrongDataException e){
            logger.error(e);
            response.sendRedirect(JSPPageName.ERROR_500_PAGE);
            //
        } catch (ServiceException e) {
            logger.error(e);
            response.sendRedirect(JSPPageName.ERROR_500_PAGE);
        }

    }
}
