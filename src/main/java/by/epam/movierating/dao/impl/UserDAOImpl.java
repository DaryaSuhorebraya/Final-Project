package by.epam.movierating.dao.impl;

import by.epam.movierating.bean.User;
import by.epam.movierating.dao.UserDAO;
import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.util.DAOUtil;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Даша on 14.02.2017.
 */
public class UserDAOImpl implements UserDAO{
    private static final  String SQL_GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login=? and is_deleted=0";
    private static final String SQL_REGISTER="INSERT INTO user(first_name,last_name,login, password, date_register, email) VALUES(?,?,?,?,?,?)";
    private static final String SQL_GET_ALL_USERS="SELECT * FROM user WHERE is_deleted=0";
    private static final String SQL_LAST_INSERT_ID="SELECT LAST_INSERT_ID()";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM user WHERE id_user=?";
    private static final String SQL_UPDATE_USER="UPDATE user SET first_name=?, last_name=?, login=?, " +
            "date_register=?, email=?, status=?, is_admin=?, is_banned=? " +
            "WHERE id_user=?";
    private static final String SQL_DELETE_USER="UPDATE user SET is_deleted=1 WHERE id_user=?";
    private static final String SQL_BAN_USER="UPDATE user SET is_banned=1 WHERE id_user=?";
    private static final String SQL_UNBAN_USER="UPDATE user SET is_banned=0 WHERE id_user=?";
    @Override
    public User getUserByLogin(String login) throws DAOException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        User user=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(SQL_GET_USER_BY_LOGIN);
            preparedStatement.setString(1,login);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                user=new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setDateRegister(resultSet.getDate(6));
                user.setEmail(resultSet.getString(7));
                user.setStatus(resultSet.getString(8));
                user.setAdmin(resultSet.getBoolean(9));
                user.setBanned(resultSet.getBoolean(10));
            }
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public User register(String login, String password, java.util.Date date, String email, String firstName,String lastName)
            throws DAOException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(SQL_REGISTER);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,login);
            preparedStatement.setString(4,password);
            preparedStatement.setDate(5,new java.sql.Date(date.getTime()));
            preparedStatement.setString(6,email);
            int updatedRows=preparedStatement.executeUpdate();
            if (updatedRows>0){
                return getUserByLogin(login);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<User> userList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(SQL_GET_ALL_USERS);
            userList=setUserInfo(resultSet);
            return userList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }
    }

    @Override
    public User getUserById(int idUser) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        User user=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(SQL_GET_USER_BY_ID);
            preparedStatement.setInt(1,idUser);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                user=new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setDateRegister(resultSet.getDate(6));
                user.setEmail(resultSet.getString(7));
                user.setStatus(resultSet.getString(8));
                user.setAdmin(resultSet.getBoolean(9));
                user.setBanned(resultSet.getBoolean(10));
            }
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public void editUser(int idUser, String login, String firstName,
                         String lastName, String email, java.util.Date dateRegister,
                         String status, boolean isAdmin, boolean isBanned)
            throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        User user=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,login);
            preparedStatement.setDate(4,new Date(dateRegister.getTime()));
            preparedStatement.setString(5,email);
            preparedStatement.setString(6,status);
            preparedStatement.setBoolean(7,isAdmin);
            preparedStatement.setBoolean(8,isBanned);
            preparedStatement.setInt(9,idUser);
            preparedStatement.executeUpdate();
            /*if (resultSet.next()){
                user=new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setDateRegister(resultSet.getDate(6));
                user.setEmail(resultSet.getString(7));
                user.setStatus(resultSet.getString(8));
                user.setAdmin(resultSet.getBoolean(9));
                user.setBanned(resultSet.getBoolean(10));
            }*/
            //return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public boolean deleteUser(int idUser) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1,idUser);
            int updatedRows=preparedStatement.executeUpdate();
            return updatedRows == 1;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement);
        }
    }

    @Override
    public boolean banUser(int idUser) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(SQL_BAN_USER);
            preparedStatement.setInt(1,idUser);
            int updatedRows=preparedStatement.executeUpdate();
            return updatedRows == 1;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement);
        }
    }

    @Override
    public boolean unbanUser(int idUser) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(SQL_UNBAN_USER);
            preparedStatement.setInt(1,idUser);
            int updatedRows=preparedStatement.executeUpdate();
            return updatedRows == 1;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement);
        }
    }

    private List<User> setUserInfo(ResultSet resultSet) throws SQLException{
        List<User> userList=new ArrayList<>();
        while (resultSet.next()){
            User user=new User();
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setLogin(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setDateRegister(resultSet.getDate(6));
            user.setEmail(resultSet.getString(7));
            user.setStatus(resultSet.getString(8));
            user.setAdmin(resultSet.getBoolean(9));
            user.setBanned(resultSet.getBoolean(10));
            userList.add(user);
        }
        return userList;
    }


}
