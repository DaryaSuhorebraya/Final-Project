package by.epam.movierating.command.movie;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.JSPPageName;
import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.service.MovieService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Даша on 23.02.2017.
 */
public class DeleteMovieCommand implements Command {
    private static final Logger logger=Logger.getLogger(DeleteMovieCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        int idMovie=Integer.parseInt(request.getParameter(ParameterName.MOVIE_ID));
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        MovieService movieService=serviceFactory.getMovieService();
        try {
            boolean result=movieService.deleteMovie(idMovie);
            response.getWriter().print(result);
            //request.setAttribute(AttributeName.MOVIES,movieList);
            //request.getRequestDispatcher(JSPPageName.MOVIES_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            response.getWriter().print(false);
        }
    }
}
