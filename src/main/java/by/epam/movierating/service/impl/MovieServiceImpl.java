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
    public List<Movie> getTopMovies(String language) throws ServiceException {
        Validator.validateLanguage(language);
        List<Movie> movieList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            movieList=movieDAO.getTopMovies(language);
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

    @Override
    public boolean deleteGenreForMovie(String genreName, String language, int movieId)
            throws ServiceException {
        Validator.validateStringData(genreName);
        Validator.validateLanguage(language);
        Validator.validateIntData(movieId);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.deleteGenreForMovie(genreName, language, movieId);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addGenreForMovie(int idMovie, String genreName, String language)
            throws ServiceException {
        Validator.validateStringData(genreName);
        Validator.validateLanguage(language);
        Validator.validateIntData(idMovie);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.addGenreForMovie(idMovie, genreName, language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Movie> getMoviesByGenreName(String genreName, String language)
            throws ServiceException {
        Validator.validateStringData(genreName);
        Validator.validateLanguage(language);
        List<Movie> movieList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            movieList=movieDAO.getMoviesByGenreName(genreName,language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return movieList;
    }

    @Override
    public List<Movie> getMoviesByCountryName(String countryName, String language)
            throws ServiceException {
        Validator.validateStringData(countryName);
        Validator.validateLanguage(language);
        List<Movie> movieList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            movieList=movieDAO.getMoviesByCountryName(countryName,language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return movieList;
    }

    @Override
    public List<Movie> getMoviesByActorInitial(String firstName, String lastName, String language)
            throws ServiceException {
        Validator.validateStringData(firstName, lastName);
        Validator.validateLanguage(language);
        List<Movie> movieList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            movieList=movieDAO.getMoviesByActorInitial(firstName,lastName,language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return movieList;
    }

    @Override
    public boolean deleteCountryForMovie(String countryName, String language, int movieId)
            throws ServiceException {
        Validator.validateStringData(countryName);
        Validator.validateLanguage(language);
        Validator.validateIntData(movieId);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.deleteCountryForMovie(countryName, language, movieId);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addCountryForMovie(int idMovie, String countryName, String language)
            throws ServiceException {
        Validator.validateStringData(countryName);
        Validator.validateLanguage(language);
        Validator.validateIntData(idMovie);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.addCountryForMovie(idMovie, countryName, language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteActorForMovie(String firstName, String lastName,
                                       String language, int movieId)
            throws ServiceException {
        Validator.validateStringData(firstName,lastName);
        Validator.validateLanguage(language);
        Validator.validateIntData(movieId);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.deleteActorForMovie(firstName,lastName, language, movieId);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addActorForMovie(int idMovie, String firstName,
                                    String lastName, String language)
            throws ServiceException {
        Validator.validateStringData(firstName,lastName);
        Validator.validateLanguage(language);
        Validator.validateIntData(idMovie);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.addActorForMovie(idMovie, firstName,lastName, language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public int addMovie(String nameEn, String nameRu,
                          int releaseYear, String descrEn,
                          String descrRu)
            throws ServiceException {
        Validator.validateIntData(releaseYear);
        Validator.validateStringData(nameEn, nameRu, descrEn, descrRu);
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.addMovie(nameEn, nameRu, releaseYear, descrEn, descrRu);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean uploadMoviePoster(int idMovie, String imgPath)
            throws ServiceException {
        Validator.validateIntData(idMovie);
        Validator.validateStringData(imgPath);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            return movieDAO.uploadMoviePoster(idMovie, imgPath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Movie> getNewestMovies(String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        List<Movie> movieList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            MovieDAO movieDAO=daoFactory.getMovieDAO();
            movieList=movieDAO.getNewestMovies(language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return movieList;
    }
}
