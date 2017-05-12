package by.epam.movierating.dao;

import by.epam.movierating.bean.Rating;
import by.epam.movierating.dao.exception.DAOException;

/**
 * Created by Даша on 01.04.2017.
 */
public interface RatingDAO {
    boolean rateMovie(int idMovie, int idUser, int mark) throws DAOException;
    boolean checkRateOpportunity(int idMovie, int idUser) throws DAOException;
    Rating getRatingOnMovieByUserId(int idMovie, int idUser) throws DAOException;
    boolean deleteRating(int idMovie, int idUser) throws DAOException;
}
