package by.epam.movierating.dao.impl;

import by.epam.movierating.bean.Review;
import by.epam.movierating.bean.dto.ReviewDTO;
import by.epam.movierating.bean.dto.StaticticsDTO;
import by.epam.movierating.dao.ReviewDAO;
import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.util.DAOUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 25.03.2017.
 */
public class ReviewDAOImpl implements ReviewDAO {
    private static final String SQL_GET_ALL_REVIEWS_ORDER_BY_DATE = "SELECT id_movie, id_user, title, " +
            "review , publish_date FROM review WHERE is_deleted=0 " +
            "ORDER BY publish_date DESC";
    private static final String SQL_GET_ALL_FULL_INFO_REVIEWS_ORDER_BY_DATE = "SELECT review.id_movie, review.id_user, " +
            "movie.title_, user.login, rating.mark , review.title, " +
            "review.review , review.publish_date " +
            "FROM review " +
            "LEFT JOIN movie ON review.id_movie=movie.id_movie " +
            "LEFT JOIN user ON review.id_user=user.id_user " +
            "LEFT JOIN rating ON review.id_user=rating.id_user and review.id_movie=rating.id_movie " +
            "WHERE review.is_deleted=0 " +
            "ORDER BY review.publish_date DESC";
    private static final String SQL_GET_LIMITED_REVIEWS = "SELECT review.id_movie, review.id_user, " +
            "movie.title_, user.login, rating.mark , review.title, " +
            "review.review , review.publish_date " +
            "FROM review " +
            "LEFT JOIN movie ON review.id_movie=movie.id_movie " +
            "LEFT JOIN user ON review.id_user=user.id_user " +
            "LEFT JOIN rating ON review.id_user=rating.id_user and review.id_movie=rating.id_movie " +
            "WHERE review.is_deleted=0 " +
            "ORDER BY review.publish_date DESC " +
            "LIMIT 3";
    private static final String SQL_CHECK_REVIEW_OPPORTUNITY = "SELECT * FROM review WHERE id_movie=? and id_user=?";
    private static final String SQL_GET_REVIEWS_BY_MOVIE_ID = "SELECT review.id_movie, review.id_user, " +
            "movie.title_, user.login, rating.mark , review.title, " +
            "review.review , review.publish_date " +
            "FROM review " +
            "LEFT JOIN movie ON review.id_movie=movie.id_movie " +
            "LEFT JOIN user ON review.id_user=user.id_user " +
            "LEFT JOIN rating ON review.id_user=rating.id_user and review.id_movie=rating.id_movie " +
            "WHERE review.is_deleted=0 AND movie.id_movie=? " +
            "ORDER BY review.publish_date DESC";
    private static final String SQL_REVIEW_MOVIE = "INSERT INTO review (id_movie, id_user, title, review, publish_date) " +
            "VALUES(?,?,?,?,?)";
    private static final String SQL_GET_REVIEW_STATISTICS="SELECT count(review.id_movie), movie.title_  " +
            "FROM review " +
            "INNER JOIN movie ON review.id_movie=movie.id_movie " +
            "GROUP BY movie.id_movie ";
    private static final String SQL_DELETE_REVIEW="UPDATE review SET is_deleted=1 WHERE id_movie=? AND id_user=?";
    private static final String SQL_GET_REVIEWS_BY_USER_ID="SELECT review.id_movie, review.id_user, " +
            "movie.title_, user.login, rating.mark , review.title, " +
            "review.review , review.publish_date " +
            "FROM review " +
            "LEFT JOIN movie ON review.id_movie=movie.id_movie " +
            "LEFT JOIN user ON review.id_user=user.id_user " +
            "LEFT JOIN rating ON review.id_user=rating.id_user and review.id_movie=rating.id_movie " +
            "WHERE review.is_deleted=0 and review.id_user=? " +
            "ORDER BY review.publish_date DESC";

    @Override
    public List<Review> getAllReviewsOrderByDate()
            throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Review> reviewList;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_GET_ALL_REVIEWS_ORDER_BY_DATE);
            reviewList = setReviewInfo(resultSet);
            return reviewList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<ReviewDTO> getAllFullInfoReviewsOrderByDate(String language)
            throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<ReviewDTO> reviewList;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DAOUtil.
                    localizeStatement(SQL_GET_ALL_FULL_INFO_REVIEWS_ORDER_BY_DATE, language));
            reviewList = setReviewDTOInfo(resultSet);
            return reviewList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<ReviewDTO> getLimitedReviews(String language)
            throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<ReviewDTO> reviewList;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DAOUtil.
                    localizeStatement(SQL_GET_LIMITED_REVIEWS, language));
            reviewList = setReviewDTOInfo(resultSet);
            return reviewList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean checkReviewOpportunity(int idMovie, int idUser)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_CHECK_REVIEW_OPPORTUNITY);
            statement.setInt(1, idMovie);
            statement.setInt(2, idUser);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<ReviewDTO> getReviewsByIdMovie(int idMovie, String language)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ReviewDTO> reviewList;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DAOUtil.
                    localizeStatement(SQL_GET_REVIEWS_BY_MOVIE_ID, language));
            statement.setInt(1, idMovie);
            resultSet = statement.executeQuery();
            reviewList = setReviewDTOInfo(resultSet);
            return reviewList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean reviewMovie(int idMovie, int idUser, String title, String review)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_REVIEW_MOVIE);
            statement.setInt(1, idMovie);
            statement.setInt(2, idUser);
            statement.setString(3, title);
            statement.setString(4, review);
            statement.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement);
        }
    }

    @Override
    public List<StaticticsDTO> getReviewStatistics(String language)
            throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<StaticticsDTO> staticticsDTOList = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DAOUtil.
                    localizeStatement(SQL_GET_REVIEW_STATISTICS, language));
            while (resultSet.next()) {
                StaticticsDTO staticticsDTO = new StaticticsDTO();
                staticticsDTO.setValue(resultSet.getInt(1));
                staticticsDTO.setLabel(resultSet.getString(2));
                staticticsDTOList.add(staticticsDTO);
            }
            return staticticsDTOList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean deleteReview(int idMovie, int idUser)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_REVIEW);
            statement.setInt(1, idMovie);
            statement.setInt(2, idUser);
            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement);
        }
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(int idUser, String language)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ReviewDTO> reviewList;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DAOUtil.
                    localizeStatement(SQL_GET_REVIEWS_BY_USER_ID, language));
            statement.setInt(1, idUser);
            resultSet = statement.executeQuery();
            reviewList = setReviewDTOInfo(resultSet);
            return reviewList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement, resultSet);
        }
    }

    private List<Review> setReviewInfo(ResultSet resultSet)
            throws SQLException {
        List<Review> reviewList = new ArrayList<>();
        while (resultSet.next()) {
            Review review = new Review();
            review.setIdMovie(resultSet.getInt(1));
            review.setIdUser(resultSet.getInt(2));
            review.setTitle(resultSet.getString(3));
            review.setReview(resultSet.getString(4));
            review.setPublishDate(resultSet.getDate(5));
            reviewList.add(review);
        }
        return reviewList;
    }

    private List<ReviewDTO> setReviewDTOInfo(ResultSet resultSet) throws SQLException {
        List<ReviewDTO> reviewList = new ArrayList<>();
        while (resultSet.next()) {
            ReviewDTO review = new ReviewDTO();
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
