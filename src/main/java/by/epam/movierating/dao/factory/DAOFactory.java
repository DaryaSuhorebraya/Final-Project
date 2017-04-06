package by.epam.movierating.dao.factory;

import by.epam.movierating.dao.*;
import by.epam.movierating.dao.impl.*;

/**
 * Created by Даша on 14.02.2017.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private CountryDAO countryDAO = new CountryDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();
    private MovieDAO movieDAO = new MovieDAOImpl();
    private GenreDAO genreDAO = new GenreDAOImpl();
    private UserInfoDAO userInfoDAO = new UserInfoImpl();
    private ActorDAO actorDAO = new ActorDAOImpl();
    private ReviewDAO reviewDAO = new ReviewDAOImpl();
    private RatingDAO ratingDAO = new RatingDAOImpl();

    public DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public MovieDAO getMovieDAO() {
        return movieDAO;
    }

    public GenreDAO getGenreDAO() {
        return genreDAO;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public ActorDAO getActorDAO() {
        return actorDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public RatingDAO getRatingDAO() {
        return ratingDAO;
    }
}
