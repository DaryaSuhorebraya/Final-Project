package by.epam.movierating.dao.impl;

import by.epam.movierating.bean.Rating;
import by.epam.movierating.dao.RatingDAO;
import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.util.DAOUtil;

import java.sql.*;

/**
 * Created by Даша on 01.04.2017.
 */
public class RatingDAOImpl implements RatingDAO {
    private static final String SQL_RATE_MOVIE = "INSERT INTO rating (id_movie, id_user, mark) VALUES(?,?,?)";
    private static final String SQL_CHECK_RATE_OPPORTUNITY = "SELECT * FROM rating WHERE id_movie=? and id_user=?";
    private static final String SQL_DELETE_RATING="DELETE FROM rating WHERE id_movie=? and id_user=?";

    @Override
    public boolean rateMovie(int idMovie, int idUser, int mark)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_RATE_MOVIE);
            statement.setInt(1, idMovie);
            statement.setInt(2, idUser);
            statement.setInt(3, mark);
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
    public boolean checkRateOpportunity(int idMovie, int idUser)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_CHECK_RATE_OPPORTUNITY);
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
    public Rating getRatingOnMovieByUserId(int idMovie, int idUser)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Rating rating = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_CHECK_RATE_OPPORTUNITY);
            statement.setInt(1, idMovie);
            statement.setInt(2, idUser);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rating = new Rating();
                rating.setMark(resultSet.getInt("mark"));
            }
            return rating;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean deleteRating(int idMovie, int idUser)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_RATING);
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
}
