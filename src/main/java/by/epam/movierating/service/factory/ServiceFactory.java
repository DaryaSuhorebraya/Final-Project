package by.epam.movierating.service.factory;

import by.epam.movierating.bean.Review;
import by.epam.movierating.service.*;
import by.epam.movierating.service.impl.*;

/**
 * Created by Даша on 14.02.2017.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private UserService userService = new UserServiceImpl();
    private MovieService movieService = new MovieServiceImpl();
    private GenreService genreService = new GenreServiceImpl();
    private CountryService countryService = new CountryServiceImpl();
    private ActorService actorService = new ActorServiceImpl();
    private ReviewService reviewService = new ReviewServiceImpl();
    private RatingService ratingService = new RatingServiceImpl();

    public ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public GenreService getGenreService() {
        return genreService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public ActorService getActorService() {
        return actorService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public RatingService getRatingService() {
        return ratingService;
    }
}
