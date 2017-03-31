package by.epam.movierating.service;

import by.epam.movierating.bean.Review;
import by.epam.movierating.bean.dto.ReviewDTO;
import by.epam.movierating.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Даша on 25.03.2017.
 */
public interface ReviewService {
    List<Review> getAllReviewsOrderByDate(String language) throws ServiceException;
    List<ReviewDTO> getAllFullInfoReviewsOrderByDate(String language) throws ServiceException;
    List<ReviewDTO> getLimitedReviews(String language) throws ServiceException;
}
