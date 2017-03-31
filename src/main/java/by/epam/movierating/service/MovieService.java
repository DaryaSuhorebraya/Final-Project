package by.epam.movierating.service;

import by.epam.movierating.bean.Movie;
import by.epam.movierating.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Даша on 14.02.2017.
 */
public interface MovieService {
    List<Movie> getAllMovies(String language) throws ServiceException;
    List<Movie> getTopMovies(String language) throws ServiceException;
    List<Movie> getMoviesByGenre(int idGenre, String language) throws ServiceException;
    List<Movie> getMoviesByCountry(String countryCode, String language) throws ServiceException;
    boolean deleteMovie(int idMovie) throws ServiceException;
    Movie getMovieById(int idMovie, String language) throws ServiceException;
    boolean editMovieField(int idMovie, String field, String value,String language) throws ServiceException;
    boolean deleteGenreForMovie(String genreName, String language, int movieId) throws ServiceException;
    boolean addGenreForMovie(int idMovie, String genreName, String language) throws ServiceException;
    List<Movie> getMoviesByGenreName(String genreName, String language) throws ServiceException;
    List<Movie> getMoviesByCountryName(String countryName, String language) throws ServiceException;
    List<Movie> getMoviesByActorInitial(String firstName, String lastName, String language) throws ServiceException;
    boolean deleteCountryForMovie(String countryName, String language, int movieId) throws ServiceException;
    boolean addCountryForMovie(int idMovie, String countryName, String language) throws ServiceException;
    boolean deleteActorForMovie(String firstName, String lastName, String language, int movieId) throws ServiceException;
    boolean addActorForMovie(int idMovie, String firstName, String lastName, String language) throws ServiceException;
    int addMovie(String nameEn, String nameRu, int releaseYear, String descrEn, String descrRu) throws ServiceException;
    boolean uploadMoviePoster(int idMovie, String imgPath) throws ServiceException;
    List<Movie> getNewestMovies(String language) throws ServiceException;
}
