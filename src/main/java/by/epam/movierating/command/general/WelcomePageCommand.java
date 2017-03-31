package by.epam.movierating.command.general;

import by.epam.movierating.bean.Country;
import by.epam.movierating.bean.Genre;
import by.epam.movierating.bean.dto.ReviewDTO;
import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.JSPPageName;
import by.epam.movierating.service.CountryService;
import by.epam.movierating.service.GenreService;
import by.epam.movierating.service.ReviewService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Даша on 17.02.2017.
 */
public class WelcomePageCommand implements Command {
    private final static Logger logger = Logger.getLogger(WelcomePageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String language = (String) session.getAttribute(AttributeName.LANGUAGE);
        ServiceFactory serviceFactory = new ServiceFactory();

        try {
            /*GenreService genreService = serviceFactory.getGenreService();
            CountryService countryService = serviceFactory.getCountryService();*/
            ReviewService reviewService = serviceFactory.getReviewService();
            /*List<Genre> genreList = genreService.getAllActiveGenre(language);
            List<Country> countryList = countryService.getAllActiveCountries(language);

            ServletContext servletContext = session.getServletContext();
            servletContext.setAttribute(AttributeName.COUNTRIES, countryList);
            servletContext.setAttribute(AttributeName.GENRES, genreList);*/

            List<ReviewDTO> reviewList = reviewService.getLimitedReviews(language);
            request.setAttribute(AttributeName.REVIEWS, reviewList);

        } catch (ServiceException e) {
            logger.error(e);
        }
        if (session.getAttribute(AttributeName.ROLE).equals(AttributeName.ADMIN)) {
            request.getRequestDispatcher(JSPPageName.ADMIN_PAGE).forward(request, response);
        } else {
            request.getRequestDispatcher(JSPPageName.WELCOME_PAGE).forward(request, response);
        }
    }
}
