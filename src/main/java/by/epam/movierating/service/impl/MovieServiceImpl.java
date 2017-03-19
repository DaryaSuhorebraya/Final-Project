package by.epam.movierating.service.impl;

import by.epam.movierating.bean.Movie;
import by.epam.movierating.dao.MovieDAO;
import by.epam.movierating.dao.UserDAO;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.factory.DAOFactory;
import by.epam.movierating.service.MovieService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.exception.ServiceWrongDataException;
import by.epam.movierating.service.util.Validator;

import java.util.List;

/**
 * Created by Даша on 15.02.2017.
 */
public class MovieServiceImpl implements MovieService {

    @Override
    public List<Movie> getAllMovies(String language) throws ServiceException {
        Validator.validateLanguage(language);
        List<Movie> movieList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            movieList=movieDAO.getAllMovies(language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return movieList;
    }

    @Override
    public List<Movie> getMoviesByGenre(int idGenre, String language)
            throws ServiceException {
        Validator.validateIntData(idGenre);
        Validator.validateLanguage(language);
        List<Movie> movieList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            movieList=movieDAO.getMoviesByGenre(idGenre,language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return movieList;
    }

    @Override
    public List<Movie> getMoviesByCountry(String countryCode, String language)
            throws ServiceException {
        Validator.validateStringData(countryCode);
        Validator.validateLanguage(language);
        List<Movie> movieList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            movieList=movieDAO.getMoviesByCountry(countryCode,language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return movieList;
    }

    @Override
    public boolean deleteMovie(int idMovie)
            throws ServiceException {
        Validator.validateIntData(idMovie);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.deleteMovie(idMovie);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Movie getMovieById(int idMovie, String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        Validator.validateIntData(idMovie);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.getMovieById(idMovie,language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean editMovieField(int idMovie, String field, String value, String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        Validator.validateIntData(idMovie);
        Validator.validateStringData(field,value);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.editMovieField(idMovie,field,value,language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
