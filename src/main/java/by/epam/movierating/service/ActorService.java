package by.epam.movierating.service;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.factory.ServiceFactory;

import java.util.List;

/**
 * Created by Даша on 18.03.2017.
 */
public interface ActorService {
    List<Actor> getAllActors(String language) throws ServiceException;
    List<Actor> getAllActorsForJson(String language) throws ServiceException;
    List<Actor> getActorsByMovieId(int idMovie, String language) throws ServiceException;
    List<Actor> getActorsNotInMovie(int idMovie, String language) throws ServiceException;
    boolean deleteActor(int idActor) throws ServiceException;
    boolean editActor(int idActor, String firstName, String lastName, String language) throws ServiceException;
    boolean uploadActorImage(int idActor, String filePath) throws ServiceException;
    int addActor(String firstNameEn, String lastNameEn, String firstNameRu, String lastNameRu) throws ServiceException;
    List<Actor> getAllLimitedActors(String language,int currentPageNumber) throws ServiceException;
}
