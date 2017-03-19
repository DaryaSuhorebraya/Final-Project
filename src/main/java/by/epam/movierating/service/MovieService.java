package by.epam.movierating.service;

import by.epam.movierating.bean.Movie;
import by.epam.movierating.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Даша on 14.02.2017.
 */
public interface MovieService {
    List<Movie> getAllMovies(String language) throws ServiceException;
    List<Movie> getMoviesByGenre(int idGenre, String language) throws ServiceException;
    List<Movie> getMoviesByCountry(String countryCode, String language) throws ServiceException;
    boolean deleteMovie(int idMovie) throws ServiceException;
    Movie getMovieById(int idMovie, String language) throws ServiceException;
    boolean editMovieField(int idMovie, String field, String value,String language) throws ServiceException;
}
