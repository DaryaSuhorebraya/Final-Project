package by.epam.movierating.service.impl;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.dao.ActorDAO;
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
            DAOFactory daoFactory=DAOFactory.getInstance();
            ActorDAO actorDAO=daoFactory.getActorDAO();
            actorList=actorDAO.getActorsByMovieId(idMovie,language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return actorList;
    }
}
