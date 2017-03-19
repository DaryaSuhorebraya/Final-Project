package by.epam.movierating.command.user;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.JSPPageName;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Даша on 14.02.2017.
 */
public class LogoutCommand implements Command {
    //private static final String WELCOME_PAGE = "index.jsp";
    //private static final String REDIRECT_TO_WELC_PAGE = "/Controller?cmd=welcome-page";
    private static final Logger logger=Logger.getLogger(LogoutCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            response.sendRedirect(JSPPageName.REDIRECT_TO_WELC_PAGE);
        }
    }
}
