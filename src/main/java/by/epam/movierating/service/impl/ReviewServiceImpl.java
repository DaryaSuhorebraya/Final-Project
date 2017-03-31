package by.epam.movierating.service.impl;

import by.epam.movierating.bean.Review;
import by.epam.movierating.bean.dto.ReviewDTO;
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
    public List<Review> getAllReviewsOrderByDate(String language)
            throws ServiceException{
        Validator.validateLanguage(language);
        List<Review> reviewList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            ReviewDAO reviewDAO=daoFactory.getReviewDAO();
            reviewList=reviewDAO.getAllReviewsOrderByDate(language);
        } catch (DAOException e){
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
            DAOFactory daoFactory=DAOFactory.getInstance();
            ReviewDAO reviewDAO=daoFactory.getReviewDAO();
            reviewList=reviewDAO.getAllFullInfoReviewsOrderByDate(language);
        } catch (DAOException e){
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
            DAOFactory daoFactory=DAOFactory.getInstance();
            ReviewDAO reviewDAO=daoFactory.getReviewDAO();
            reviewList=reviewDAO.getLimitedReviews(language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return reviewList;
    }
}
