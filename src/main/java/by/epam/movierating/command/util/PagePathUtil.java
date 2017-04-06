package by.epam.movierating.command.util;

import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.JSPPageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Даша on 24.02.2017.
 */
public class PagePathUtil {
    private static final String WELCOME_PAGE_COMMAND = "/Controller?command=welcome-page";

    public static String getPagePath(HttpSession session) {
        String pagePath = (String) session.getAttribute(AttributeName.PREV_QUERY);
        if (pagePath == null) {
            return WELCOME_PAGE_COMMAND;
        } else {
            return pagePath;
        }
    }

    public static void setQueryString(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString == null) {
            session.setAttribute(AttributeName.PREV_QUERY, requestURI);
        } else {
            session.setAttribute(AttributeName.PREV_QUERY, requestURI + '?' + queryString);
        }
    }
}
