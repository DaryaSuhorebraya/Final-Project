package by.epam.movierating.command.impl.actor;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.command.security.RoleType;
import by.epam.movierating.command.security.SecurityManager;
import by.epam.movierating.service.ActorService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Даша on 28.03.2017.
 */
public class EditActorCommand implements Command {
    private static final Logger logger = Logger.getLogger(EditActorCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (SecurityManager.getInstance().checkRoles(request, response, RoleType.ADMIN)) {
            response.setContentType("text/plain");
            HttpSession session = request.getSession(true);

            String language = (String) session.getAttribute(AttributeName.LANGUAGE);
            int isActor = Integer.parseInt(request.getParameter(ParameterName.ACTOR_ID));
            String firstName = request.getParameter(ParameterName.FIRSTNAME);
            String lastName = request.getParameter(ParameterName.LASTNAME);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ActorService actorService = serviceFactory.getActorService();
            try {
                boolean result = actorService.editActor(isActor, firstName, lastName, language);
                response.getWriter().print(result);
            } catch (ServiceException e) {
                logger.error(e);
                response.getWriter().print(false);
            }
        }
    }
}
