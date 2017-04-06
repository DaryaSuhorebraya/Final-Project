package by.epam.movierating.command.impl.actor;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.JSPPageName;
import by.epam.movierating.command.util.PagePathUtil;
import by.epam.movierating.service.ActorService;
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
 * Created by Даша on 26.03.2017.
 */
public class ViewAllActorsCommand implements Command {

    private static final Logger logger = Logger.getLogger(ViewAllActorsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PagePathUtil.setQueryString(request);
        HttpSession session = request.getSession(true);
        String language = (String) session.getAttribute(AttributeName.LANGUAGE);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        try {
            ActorService actorService = serviceFactory.getActorService();
            List<Actor> actorList = actorService.getAllActors(language);

            request.setAttribute(AttributeName.ACTORS, actorList);

            request.getRequestDispatcher(JSPPageName.ACTORS_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
