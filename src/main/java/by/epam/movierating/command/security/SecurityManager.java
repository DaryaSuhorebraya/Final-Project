package by.epam.movierating.command.security;

import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.JSPPageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Даша on 20.04.2017.
 */
public class SecurityManager {
    private static final SecurityManager instance = new SecurityManager();

    private SecurityManager() {
    }

    public static SecurityManager getInstance() {
        return instance;
    }

    public boolean checkRoles(HttpServletRequest request,
                              HttpServletResponse response,
                              RoleType... roleTypes) throws IOException {
        HttpSession session = request.getSession();
        RoleType role = RoleType.valueOf(((String) session.getAttribute(AttributeName.ROLE)).toUpperCase());
        int roleCount = 0;
        for (RoleType roleType : roleTypes) {
            if (roleType == role) {
                roleCount++;
            }
        }
        if (roleCount==0){
            response.sendRedirect(JSPPageName.ERROR_404_PAGE);
            return false;
        } else {
            return true;
        }
    }
}
