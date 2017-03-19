package by.epam.movierating.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Даша on 20.02.2017.
 */
public class SecurityHandler {
    private static final String ROLE="role";

    private static RoleType defineRole(HttpServletRequest request){
        HttpSession session=request.getSession();
        String roleAttr=(String) session.getAttribute(ROLE);
        RoleType role=RoleType.valueOf(roleAttr.toUpperCase());
        switch (role){
            case ADMIN: {
                return RoleType.ADMIN;
            }
            case USER: {
                return RoleType.USER;
            }
            case GUEST: {
                return RoleType.GUEST;
            }
            default: {
                return RoleType.GUEST;
            }
        }
    }
}
