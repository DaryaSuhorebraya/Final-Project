package by.epam.movierating.dao.impl;

import by.epam.movierating.dao.UserInfoDAO;
import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Даша on 27.02.2017.
 */
public class UserInfoImpl implements UserInfoDAO {
    private final static String SQL_REGISTER="INSERT INTO user_info(id_user, first_name, last_name) VALUES(?,?,?)";
    @Override
    public void register(int idUser, String firstName, String lastName)
            throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(SQL_REGISTER);
            preparedStatement.setInt(1,idUser);
            preparedStatement.setString(2,firstName);
            preparedStatement.setString(3,lastName);
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement);
        }
    }
}
