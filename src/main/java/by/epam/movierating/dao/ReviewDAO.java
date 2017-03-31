package by.epam.movierating.dao;

import by.epam.movierating.bean.Review;
import by.epam.movierating.bean.dto.ReviewDTO;
import by.epam.movierating.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 25.03.2017.
 */
public interface ReviewDAO {
    List<Review> getAllReviewsOrderByDate(String language) throws DAOException;
    List<ReviewDTO> getAllFullInfoReviewsOrderByDate(String language) throws DAOException;
    List<ReviewDTO> getLimitedReviews(String language) throws DAOException;
}
