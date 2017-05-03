package by.epam.movierating.service;

import by.epam.movierating.bean.Review;
import by.epam.movierating.bean.dto.ReviewDTO;
import by.epam.movierating.bean.dto.StaticticsDTO;
import by.epam.movierating.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Даша on 25.03.2017.
 */
public interface ReviewService {
    List<Review> getAllReviewsOrderByDate() throws ServiceException;
    List<ReviewDTO> getAllFullInfoReviewsOrderByDate(String language) throws ServiceException;
    List<ReviewDTO> getReviewsByIdMovie(int idMovie, String language) throws ServiceException;
    List<ReviewDTO> getLimitedReviews(String language) throws ServiceException;
    boolean checkReviewOpportunity(int idMovie, int idUser) throws ServiceException;
    boolean reviewMovie(int idMovie, int idUser, String title, String review) throws ServiceException;
    List<StaticticsDTO> getReviewStatistics(String language) throws ServiceException;
    boolean deleteReview(int idMovie, int idUser) throws ServiceException;
    List<ReviewDTO> getReviewsByUserId(int idUser, String language) throws ServiceException;
}
