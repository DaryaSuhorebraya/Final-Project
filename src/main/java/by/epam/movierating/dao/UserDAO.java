package by.epam.movierating.dao;

import by.epam.movierating.bean.User;
import by.epam.movierating.dao.exception.DAOException;

import java.util.Date;
import java.util.List;

/**
 * Created by Даша on 27.01.2017.
 */
public interface UserDAO {
    User getUserByLogin(String login) throws DAOException;
    User register(String login, String password, Date date, String email,
                  String firstName,String lastName) throws DAOException;
    List<User> getAllUsers() throws DAOException;
    User getUserById(int idUser) throws DAOException;
    void editUser(int idUser, String login, String firstName, String lastName,
                  String email, Date dateRegister, String status, boolean isAdmin,
                  boolean isBanned) throws DAOException;
    boolean deleteUser(int idUser) throws DAOException;
    boolean banUser(int idUser) throws DAOException;
    boolean unbanUser(int idUser) throws DAOException;
}
