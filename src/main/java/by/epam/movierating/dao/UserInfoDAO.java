package by.epam.movierating.dao;

import by.epam.movierating.dao.exception.DAOException;

/**
 * Created by Даша on 27.02.2017.
 */
public interface UserInfoDAO {
    void register(int idUser, String firstName,String lastName)
            throws DAOException;
}
