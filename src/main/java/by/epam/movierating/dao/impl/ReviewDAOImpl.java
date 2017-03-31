package by.epam.movierating.dao.impl;

import by.epam.movierating.bean.Review;
import by.epam.movierating.bean.dto.ReviewDTO;
import by.epam.movierating.dao.ReviewDAO;
import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.util.DAOUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 25.03.2017.
 */
public class ReviewDAOImpl implements ReviewDAO {
    private static final String SQL_GET_ALL_REVIEWS_ORDER_BY_DATE = "SELECT id_movie, id_user, title_, " +
            "review_ , publish_date FROM review WHERE is_deleted=0 " +
            "ORDER BY publish_date DESC";
    private static final String SQL_GET_ALL_FULL_INFO_REVIEWS_ORDER_BY_DATE = "SELECT review.id_movie, review.id_user, " +
            "movie.title_, user.login, rating.mark , review.title_, " +
            "review.review_ , review.publish_date " +
            "FROM review " +
            "LEFT JOIN movie ON review.id_movie=movie.id_movie " +
            "LEFT JOIN user ON review.id_user=user.id_user " +
            "LEFT JOIN rating ON review.id_user=rating.id_user and review.id_movie=rating.id_movie " +
            "WHERE review.is_deleted=0 " +
            "ORDER BY review.publish_date DESC";
    private static final String SQL_GET_LIMITED_REVIEWS="SELECT review.id_movie, review.id_user, " +
            "movie.title_, user.login, rating.mark , review.title_, " +
            "review.review_ , review.publish_date " +
            "FROM review " +
            "LEFT JOIN movie ON review.id_movie=movie.id_movie " +
            "LEFT JOIN user ON review.id_user=user.id_user " +
            "LEFT JOIN rating ON review.id_user=rating.id_user and review.id_movie=rating.id_movie " +
            "WHERE review.is_deleted=0 " +
            "ORDER BY review.publish_date DESC "+
            "LIMIT 3";
    @Override
    public List<Review> getAllReviewsOrderByDate(String language)
            throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Review> reviewList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(DAOUtil.localizeStatement(SQL_GET_ALL_REVIEWS_ORDER_BY_DATE,language));
            reviewList=setReviewInfo(resultSet);
            return reviewList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }
    }

    @Override
    public List<ReviewDTO> getAllFullInfoReviewsOrderByDate(String language)
            throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<ReviewDTO> reviewList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(DAOUtil.
                    localizeStatement(SQL_GET_ALL_FULL_INFO_REVIEWS_ORDER_BY_DATE,language));
            reviewList=setReviewDTOInfo(resultSet);
            return reviewList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }
    }

    @Override
    public List<ReviewDTO> getLimitedReviews(String language)
            throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<ReviewDTO> reviewList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(DAOUtil.
                    localizeStatement(SQL_GET_LIMITED_REVIEWS,language));
            reviewList=setReviewDTOInfo(resultSet);
            return reviewList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }
    }

    private List<Review> setReviewInfo(ResultSet resultSet) throws SQLException{
        List<Review> reviewList=new ArrayList<>();
        while (resultSet.next()){
            Review review=new Review();
            review.setIdMovie(resultSet.getInt(1));
            review.setIdUser(resultSet.getInt(2));
            review.setTitle(resultSet.getString(3));
            review.setReview(resultSet.getString(4));
            review.setPublishDate(resultSet.getDate(5));
            reviewList.add(review);
        }
        return reviewList;
    }
    private List<ReviewDTO> setReviewDTOInfo(ResultSet resultSet) throws SQLException{
        List<ReviewDTO> reviewList=new ArrayList<>();
        while (resultSet.next()){
            ReviewDTO review=new ReviewDTO();
            review.setIdMovie(resultSet.getInt(1));
            review.setIdUser(resultSet.getInt(2));
            review.setMovieTitle(resultSet.getString(3));
            review.setUserLogin(resultSet.getString(4));
            review.setRating(resultSet.getInt(5));
            review.setTitle(resultSet.getString(6));
            review.setReview(resultSet.getString(7));
            review.setPublishDate(resultSet.getDate(8));
            reviewList.add(review);
        }
        return reviewList;
    }
}
