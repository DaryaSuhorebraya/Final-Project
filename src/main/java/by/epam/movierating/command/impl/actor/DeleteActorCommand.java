package by.epam.movierating.command.impl.actor;

import by.epam.movierating.command.Command;
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
import java.io.IOException;

/**
 * Created by Даша on 28.03.2017.
 */
public class DeleteActorCommand implements Command {
    private static final Logger logger = Logger.getLogger(DeleteActorCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");

        int idActor = Integer.parseInt(request.getParameter(ParameterName.ACTOR_ID));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ActorService actorService = serviceFactory.getActorService();
        try {
            boolean result = actorService.deleteActor(idActor);
            response.getWriter().print(result);
        } catch (ServiceException e) {
            logger.error(e);
            response.getWriter().print(false);
        }

    }
}
