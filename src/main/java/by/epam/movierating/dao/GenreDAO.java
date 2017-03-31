package by.epam.movierating.dao;

import by.epam.movierating.bean.Genre;
import by.epam.movierating.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 30.01.2017.
 */
public interface GenreDAO {
    List<Genre> getAllGenres(String language) throws DAOException;
    List<Genre> getAllActiveGenres(String language) throws DAOException;
    void deleteGenre(int idGenre) throws DAOException;
    void updateGenre(int idGenre, String name, String language) throws DAOException;
    List<Genre> getGenresByIdMovie(int idMovie, String language) throws DAOException;
    List<Genre> getGenresNotInMovie(int idMovie, String language) throws DAOException;
}
