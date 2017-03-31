package by.epam.movierating.service;

import by.epam.movierating.bean.Genre;
import by.epam.movierating.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Даша on 22.02.2017.
 */
public interface GenreService {
    List<Genre> getAllGenres(String language) throws ServiceException;
    List<Genre> getAllActiveGenre(String language) throws ServiceException;
    void deleteGenre(int idGenre) throws ServiceException;
    void updateGenre(int idGenre, String name, String language) throws ServiceException;
    List<Genre> getGenresByIdMovie(int idMovie, String language) throws ServiceException;
    List<Genre> getGenresNotInMovie(int idMovie, String language) throws ServiceException;
}
