package by.epam.movierating.command.general;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.JSPPageName;
import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.command.util.PagePathUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Даша on 24.02.2017.
 */
public class RedirectCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PagePathUtil.setQueryString(request);
        String redirectPage = request.getParameter(ParameterName.REDIRECT);
        String redirectPagePath = defineRedirectPagePath(redirectPage);
        request.getRequestDispatcher(redirectPagePath).forward(request, response);

    }

    private String defineRedirectPagePath(String redirectPage) {
        switch (redirectPage) {
            case ParameterName.REGISTRATION: {
                return JSPPageName.REGISTRATION_PAGE;
            }
            case ParameterName.ADD_MOVIE: {
                return JSPPageName.ADD_MOVIE_PAGE;
            }
        }
        return "";
    }
}
