package by.epam.movierating.dao;

import by.epam.movierating.bean.Movie;
import by.epam.movierating.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 14.02.2017.
 */
public interface MovieDAO {
    List<Movie> getAllMovies(String language) throws DAOException;
    List<Movie> getMoviesByGenre(int idGenre, String language) throws DAOException;
    List<Movie> getMoviesByCountry(String countryCode, String language) throws DAOException;
    boolean deleteMovie(int idMovie) throws DAOException;
    Movie getMovieById(int idMovie, String language) throws DAOException;
    boolean editMovieField(int idMovie, String field, String value, String language) throws DAOException;
}
