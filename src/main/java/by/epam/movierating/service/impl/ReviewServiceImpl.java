package by.epam.movierating.service.impl;

import by.epam.movierating.bean.Review;
import by.epam.movierating.bean.dto.ReviewDTO;
import by.epam.movierating.bean.dto.StaticticsDTO;
import by.epam.movierating.dao.ReviewDAO;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.factory.DAOFactory;
import by.epam.movierating.service.ReviewService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.util.Validator;

import java.util.List;

/**
 * Created by Даша on 25.03.2017.
 */
public class ReviewServiceImpl implements ReviewService {
    @Override
    public List<Review> getAllReviewsOrderByDate()
            throws ServiceException {
        List<Review> reviewList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ReviewDAO reviewDAO = daoFactory.getReviewDAO();
            reviewList = reviewDAO.getAllReviewsOrderByDate();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return reviewList;
    }

    @Override
    public List<ReviewDTO> getAllFullInfoReviewsOrderByDate(String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        List<ReviewDTO> reviewList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ReviewDAO reviewDAO = daoFactory.getReviewDAO();
            reviewList = reviewDAO.getAllFullInfoReviewsOrderByDate(language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return reviewList;
    }

    @Override
    public List<ReviewDTO> getLimitedReviews(String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        List<ReviewDTO> reviewList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ReviewDAO reviewDAO = daoFactory.getReviewDAO();
            reviewList = reviewDAO.getLimitedReviews(language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return reviewList;
    }

    @Override
    public boolean checkReviewOpportunity(int idMovie, int idUser)
            throws ServiceException {
        Validator.validateIntData(idMovie, idUser);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ReviewDAO reviewDAO = daoFactory.getReviewDAO();
            return reviewDAO.checkReviewOpportunity(idMovie, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ReviewDTO> getReviewsByIdMovie(int idMovie, String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        Validator.validateIntData(idMovie);
        List<ReviewDTO> reviewList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ReviewDAO reviewDAO = daoFactory.getReviewDAO();
            reviewList = reviewDAO.getReviewsByIdMovie(idMovie, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return reviewList;
    }

    @Override
    public boolean reviewMovie(int idMovie, int idUser, String title, String review)
            throws ServiceException {
        Validator.validateIntData(idMovie, idUser);
        Validator.validateStringData(title, review);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ReviewDAO reviewDAO = daoFactory.getReviewDAO();
            return reviewDAO.reviewMovie(idMovie, idUser, title, review);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<StaticticsDTO> getReviewStatistics(String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ReviewDAO reviewDAO = daoFactory.getReviewDAO();
            return reviewDAO.getReviewStatistics(language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteReview(int idMovie, int idUser)
            throws ServiceException {
        Validator.validateIntData(idMovie, idUser);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ReviewDAO reviewDAO = daoFactory.getReviewDAO();
            return reviewDAO.deleteReview(idMovie, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(int idUser, String language)
            throws ServiceException {
        Validator.validateIntData(idUser);
        Validator.validateLanguage(language);
        List<ReviewDTO> reviewList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ReviewDAO reviewDAO = daoFactory.getReviewDAO();
            reviewList = reviewDAO.getReviewsByUserId(idUser, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return reviewList;
    }
}
