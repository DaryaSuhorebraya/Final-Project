package by.epam.movierating.service.impl;

import by.epam.movierating.bean.Rating;
import by.epam.movierating.dao.RatingDAO;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.factory.DAOFactory;
import by.epam.movierating.service.RatingService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.util.Validator;

/**
 * Created by Даша on 01.04.2017.
 */
public class RatingServiceImpl implements RatingService {

    @Override
    public boolean rateMovie(int idMovie, int idUser, int mark)
            throws ServiceException {
        Validator.validateIntData(idMovie, idUser, mark);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            RatingDAO ratingDAO = daoFactory.getRatingDAO();
            return ratingDAO.rateMovie(idMovie, idUser, mark);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkRateOpportunity(int idMovie, int idUser)
            throws ServiceException {
        Validator.validateIntData(idMovie, idUser);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            RatingDAO ratingDAO = daoFactory.getRatingDAO();
            return ratingDAO.checkRateOpportunity(idMovie, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating getRatingOnMovieByUserId(int idMovie, int idUser)
            throws ServiceException {
        Validator.validateIntData(idMovie, idUser);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            RatingDAO ratingDAO = daoFactory.getRatingDAO();
            return ratingDAO.getRatingOnMovieByUserId(idMovie, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteRating(int idMovie, int idUser)
            throws ServiceException {
        Validator.validateIntData(idMovie, idUser);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            RatingDAO ratingDAO = daoFactory.getRatingDAO();
            return ratingDAO.deleteRating(idMovie, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
