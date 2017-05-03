package by.epam.movierating.service.impl;

import by.epam.movierating.bean.Genre;
import by.epam.movierating.bean.dto.StaticticsDTO;
import by.epam.movierating.dao.GenreDAO;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.factory.DAOFactory;
import by.epam.movierating.service.GenreService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.util.Validator;

import java.util.List;

/**
 * Created by Даша on 22.02.2017.
 */
public class GenreServiceImpl implements GenreService {

    @Override
    public List<Genre> getAllGenres(String language) throws ServiceException {
        Validator.validateLanguage(language);
        List<Genre> genreList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GenreDAO genreDAO = daoFactory.getGenreDAO();
            genreList = genreDAO.getAllGenres(language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return genreList;
    }

    @Override
    public List<Genre> getAllActiveGenre(String language) throws ServiceException {
        Validator.validateLanguage(language);
        List<Genre> genreList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GenreDAO genreDAO = daoFactory.getGenreDAO();
            genreList = genreDAO.getAllActiveGenres(language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return genreList;
    }

    @Override
    public boolean deleteGenre(int idGenre) throws ServiceException {
        Validator.validateIntData(idGenre);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GenreDAO genreDAO = daoFactory.getGenreDAO();
            return genreDAO.deleteGenre(idGenre);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean editGenre(int idGenre, String name, String language)
            throws ServiceException {
        Validator.validateIntData(idGenre);
        Validator.validateStringData(name, language);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GenreDAO genreDAO = daoFactory.getGenreDAO();
            return genreDAO.editGenre(idGenre, name, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Genre> getGenresByIdMovie(int idMovie, String language)
            throws ServiceException {
        Validator.validateIntData(idMovie);
        Validator.validateLanguage(language);
        List<Genre> genreList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GenreDAO genreDAO = daoFactory.getGenreDAO();
            genreList = genreDAO.getGenresByIdMovie(idMovie, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return genreList;
    }

    @Override
    public List<Genre> getGenresNotInMovie(int idMovie, String language)
            throws ServiceException {
        Validator.validateIntData(idMovie);
        Validator.validateLanguage(language);
        List<Genre> genreList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GenreDAO genreDAO = daoFactory.getGenreDAO();
            genreList = genreDAO.getGenresNotInMovie(idMovie, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return genreList;
    }

    @Override
    public List<StaticticsDTO> getGenreStatistics(String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GenreDAO genreDAO = daoFactory.getGenreDAO();
            return genreDAO.getGenreStatistics(language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int addGenre(String nameRu, String nameEn)
            throws ServiceException {
        Validator.validateStringData(nameRu, nameEn);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GenreDAO genreDAO = daoFactory.getGenreDAO();
            return genreDAO.addGenre(nameRu, nameEn);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

