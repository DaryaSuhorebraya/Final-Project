package by.epam.movierating.service.impl;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.bean.Movie;
import by.epam.movierating.dao.ActorDAO;
import by.epam.movierating.dao.MovieDAO;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.factory.DAOFactory;
import by.epam.movierating.service.ActorService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.util.Validator;

import java.util.List;

/**
 * Created by Даша on 18.03.2017.
 */
public class ActorServiceImpl implements ActorService {
    @Override
    public List<Actor> getActorsByMovieId(int idMovie, String language)
            throws ServiceException {
        Validator.validateIntData(idMovie);
        Validator.validateLanguage(language);
        List<Actor> actorList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ActorDAO actorDAO = daoFactory.getActorDAO();
            actorList = actorDAO.getActorsByMovieId(idMovie, language);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
        return actorList;
    }

    @Override
    public List<Actor> getActorsNotInMovie(int idMovie, String language)
            throws ServiceException {
        Validator.validateIntData(idMovie);
        Validator.validateLanguage(language);
        List<Actor> actorList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ActorDAO actorDAO = daoFactory.getActorDAO();
            actorList = actorDAO.getActorsNotInMovie(idMovie, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return actorList;
    }

    @Override
    public List<Actor> getAllActorsForJson(String language) throws ServiceException {
        Validator.validateLanguage(language);
        List<Actor> actorList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ActorDAO actorDAO = daoFactory.getActorDAO();
            actorList = actorDAO.getAllActors(language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return actorList;
    }

    @Override
    public List<Actor> getAllActors(String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        List<Actor> actorList;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ActorDAO actorDAO = daoFactory.getActorDAO();
            MovieDAO movieDAO = daoFactory.getMovieDAO();
            actorList = actorDAO.getAllActors(language);
            for (Actor actor : actorList) {
                List<Movie> movieList = movieDAO.getMoviesByActorInitial(actor.getFirstName(),
                        actor.getLastName(), language);
                actor.setMovieList(movieList);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return actorList;
    }

    @Override
    public boolean deleteActor(int idActor)
            throws ServiceException {
        Validator.validateIntData(idActor);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ActorDAO actorDAO = daoFactory.getActorDAO();
            return actorDAO.deleteActor(idActor);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean editActor(int idActor, String firstName,
                             String lastName, String language)
            throws ServiceException {
        Validator.validateIntData(idActor);
        Validator.validateStringData(firstName, lastName);
        Validator.validateLanguage(language);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ActorDAO actorDAO = daoFactory.getActorDAO();
            return actorDAO.editActor(idActor, firstName, lastName, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean uploadActorImage(int idActor, String filePath)
            throws ServiceException {
        Validator.validateIntData(idActor);
        Validator.validateStringData(filePath);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ActorDAO actorDAO = daoFactory.getActorDAO();
            return actorDAO.uploadActorImage(idActor, filePath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int addActor(String firstNameEn, String lastNameEn,
                        String firstNameRu, String lastNameRu)
            throws ServiceException {
        Validator.validateStringData(firstNameEn,lastNameEn,firstNameRu,lastNameRu);
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ActorDAO actorDAO = daoFactory.getActorDAO();
            return actorDAO.addActor(firstNameEn,lastNameEn,firstNameRu,lastNameRu);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
