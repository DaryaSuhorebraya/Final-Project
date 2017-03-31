package by.epam.movierating.command.actor;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.service.ActorService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.factory.ServiceFactory;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Даша on 22.03.2017.
 */
public class ViewActorsNotInMovieCommand implements Command {
    private static final Logger logger=Logger.getLogger(ViewActorsNotInMovieCommand.class);
    private static final String CONTENT_TYPE="application/json";
    private static final String ENCODING="UTF-8";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String language = (String) session.getAttribute(AttributeName.LANGUAGE);
        int idMovie=Integer.parseInt(request.getParameter(ParameterName.MOVIE_ID));
        ServiceFactory serviceFactory=new ServiceFactory();
        try {
            ActorService actorService=serviceFactory.getActorService();
            List<Actor> actorList=actorService.getActorsNotInMovie(idMovie, language);

            String json = new Gson().toJson(actorList);
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(ENCODING);
            response.getWriter().print(json);
        } catch (ServiceException e) {
            logger.error(e);
        }

    }
}
