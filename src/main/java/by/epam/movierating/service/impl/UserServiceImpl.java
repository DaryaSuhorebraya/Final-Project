package by.epam.movierating.service.impl;

import by.epam.movierating.bean.User;
import by.epam.movierating.bean.dto.StaticticsDTO;
import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.dao.UserDAO;
import by.epam.movierating.dao.UserInfoDAO;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.factory.DAOFactory;
import by.epam.movierating.service.UserService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.exception.ServiceWrongDataException;
import by.epam.movierating.service.util.ServiceUtil;
import by.epam.movierating.service.util.Validator;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Даша on 14.02.2017.
 */
public class UserServiceImpl implements UserService {

    @Override
    public User login(String login, byte[] password) throws ServiceException {
        Validator.validateLogin(login);
        Validator.validatePassword(password);
        User user;
        String encodePassword = ServiceUtil.encodePassword(password);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            user = userDAO.getUserByLogin(login);
            System.out.println(DigestUtils.md5Hex(user.getPassword()));
            if (user == null) {
                throw new ServiceWrongDataException("Wrong login");
            }
            if (!user.getPassword().equals(encodePassword)) {
                throw new ServiceWrongDataException("Wrong password");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User register(String login, byte[] password, byte[] confirmPassword, String email, String firstName, String lastName)
            throws ServiceException {
        Validator.validateLogin(login);
        Validator.validatePassword(password, confirmPassword);
        Validator.validateEmail(email);
        Validator.validateStringData(firstName, lastName);
        String encodePassword = ServiceUtil.encodePassword(password);
        User user;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            // UserInfoDAO userInfoDAO=daoFactory.getUserInfoDAO();
            user = userDAO.register(login, encodePassword, new Date(), email, firstName, lastName);
           /* if (user == null){
                throw new ServiceException("Error during registration");
            }*/
            // userInfoDAO.register(user.getId(),firstName,lastName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> userList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userList = userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userList;
    }

    @Override
    public User getUserById(int idUser) throws ServiceException {
        Validator.validateIntData(idUser);
        User user;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            user = userDAO.getUserById(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void editUser(int idUser, String login, String firstName,
                         String lastName, String email, String dateRegister,
                         String status, String isAdminString, String isBannedString)
            throws ServiceException {
        Validator.validateIntData(idUser);
        Validator.validateEmail(email);
        Validator.validateLogin(login);
        Validator.validateStringData(firstName, lastName, status);
        Date date = Validator.validateDate(dateRegister);
        boolean isAdmin = Validator.validateAdminString(isAdminString);
        boolean isBanned = Validator.validateBannedString(isBannedString);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.editUser(idUser, login, firstName, lastName,
                    email, date, status, isAdmin, isBanned);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        //return user;
    }

    @Override
    public boolean deleteUser(int idUser) throws ServiceException {
        Validator.validateIntData(idUser);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            return userDAO.deleteUser(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeBanStatus(int idUser, String status)
            throws ServiceException {
        Validator.validateIntData(idUser);
        Validator.validateBanStatus(status);
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            if (status.equals(ParameterName.BAN_EN)
                    || status.equals(ParameterName.BAN_RU)) {
                return userDAO.banUser(idUser);
            } else {
                return userDAO.unbanUser(idUser);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<StaticticsDTO> getMonthUserCount() throws ServiceException {
        List<StaticticsDTO> staticticsDTOList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            staticticsDTOList = userDAO.getMonthUserCount();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return staticticsDTOList;
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        Validator.validateLogin(login);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            return userDAO.getUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
