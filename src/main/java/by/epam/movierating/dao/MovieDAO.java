package by.epam.movierating.dao;

import by.epam.movierating.bean.Movie;
import by.epam.movierating.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 14.02.2017.
 */
public interface MovieDAO {
    List<Movie> getAllMovies(String language) throws DAOException;
    List<Movie> getTopMovies(String language) throws DAOException;
    List<Movie> getMoviesByGenre(int idGenre, String language) throws DAOException;
    List<Movie> getMoviesByCountry(String countryCode, String language) throws DAOException;
    boolean deleteMovie(int idMovie) throws DAOException;
    Movie getMovieById(int idMovie, String language) throws DAOException;
    boolean editMovieField(int idMovie, String field, String value, String language) throws DAOException;
    boolean deleteGenreForMovie(String genreName, String language, int movieId) throws DAOException;
    boolean addGenreForMovie(int idMovie, String genreName, String language) throws DAOException;
    List<Movie> getMoviesByGenreName(String genreName, String language) throws DAOException;
    List<Movie> getMoviesByCountryName(String countryName, String language) throws DAOException;
    List<Movie> getMoviesByActorInitial(String firstName, String lastName, String language) throws DAOException;
    boolean deleteCountryForMovie(String countryName, String language, int movieId) throws DAOException;
    boolean addCountryForMovie(int idMovie, String countryName, String language) throws DAOException;
    boolean deleteActorForMovie(String firstName, String lastName, String language, int movieId) throws DAOException;
    boolean addActorForMovie(int idMovie, String firstName, String lastName, String language) throws DAOException;
    int addMovie(String titleEn, String titleRu, int releaseYear, String descrEn, String descrRu) throws DAOException;
    Movie getMovieByTitleEn(String titleEn) throws DAOException;
    boolean uploadMoviePoster(int idMovie, String imgPath) throws DAOException;
    List<Movie> getNewestMovies(String language) throws DAOException;
}
