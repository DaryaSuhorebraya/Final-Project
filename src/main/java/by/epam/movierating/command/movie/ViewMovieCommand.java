package by.epam.movierating.command.movie;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.bean.Country;
import by.epam.movierating.bean.Genre;
import by.epam.movierating.bean.Movie;
import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.JSPPageName;
import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.command.util.PagePathUtil;
import by.epam.movierating.service.ActorService;
import by.epam.movierating.service.CountryService;
import by.epam.movierating.service.GenreService;
import by.epam.movierating.service.MovieService;
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
 * Created by Даша on 17.03.2017.
 */
public class ViewMovieCommand implements Command {
    private final static Logger logger=Logger.getLogger(ViewMovieCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PagePathUtil.setQueryString(request);
        HttpSession session = request.getSession(true);
        int idMovie=Integer.parseInt(request.getParameter(ParameterName.MOVIE_ID));
        String language=(String) session.getAttribute(AttributeName.LANGUAGE);
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        MovieService movieService=serviceFactory.getMovieService();
        GenreService genreService=serviceFactory.getGenreService();
        CountryService countryService=serviceFactory.getCountryService();
        ActorService actorService=serviceFactory.getActorService();
        try {
            Movie movie=movieService.getMovieById(idMovie,language);
            request.setAttribute(AttributeName.MOVIE,movie);
            List<Genre> genreList=genreService.getGenresByIdMovie(idMovie,language);
            request.setAttribute(AttributeName.GENRES, genreList);
            List<Country> countryList=countryService.getCountriesByMovieId(idMovie,language);
            request.setAttribute(AttributeName.COUNTRIES,countryList);
            List<Actor> actorList=actorService.getActorsByMovieId(idMovie,language);
            request.setAttribute(AttributeName.ACTORS, actorList);
            request.getRequestDispatcher(JSPPageName.MOVIE_INFO_PAGE).forward(request, response);
            //response.sendRedirect(JSPPageName.USER_INFO_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}