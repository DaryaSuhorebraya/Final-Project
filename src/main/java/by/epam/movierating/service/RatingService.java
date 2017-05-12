package by.epam.movierating.service;

import by.epam.movierating.bean.Rating;
import by.epam.movierating.service.exception.ServiceException;

/**
 * Created by Даша on 01.04.2017.
 */
public interface RatingService {
    boolean rateMovie(int idMovie, int idUser, int mark) throws ServiceException;
    boolean checkRateOpportunity(int idMovie, int idUser) throws ServiceException;
    Rating getRatingOnMovieByUserId(int idMovie, int idUser) throws ServiceException;
    boolean deleteRating(int idMovie, int idUser) throws ServiceException;
}
