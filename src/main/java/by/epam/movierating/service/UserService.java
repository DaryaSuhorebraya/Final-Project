package by.epam.movierating.service;

import by.epam.movierating.bean.User;
import by.epam.movierating.bean.dto.StaticticsDTO;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.exception.ServiceWrongDataException;

import java.util.List;

/**
 * Created by Даша on 14.02.2017.
 */
public interface UserService {
    User login(String login, byte[] password) throws ServiceException,ServiceWrongDataException;
    User register(String login, byte[] password,byte[] confirmPassword, String email, String firstName, String lastName) throws ServiceWrongDataException,ServiceException;
    List<User> getAllUsers() throws ServiceException;
    User getUserById(int idUser) throws ServiceException;
    void editUser(int idUser,String login, String firstName, String lastName, String email,String dateRegister, String status,
                  String isAdmin, String isBanned) throws ServiceException;
    boolean deleteUser(int idUser) throws ServiceException;
    boolean changeBanStatus(int idUser, String status) throws ServiceException;
    List<StaticticsDTO> getMonthUserCount() throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;
}
