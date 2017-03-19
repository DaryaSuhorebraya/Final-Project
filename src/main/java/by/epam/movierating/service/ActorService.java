package by.epam.movierating.service;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Даша on 18.03.2017.
 */
public interface ActorService {
    List<Actor> getActorsByMovieId(int idMovie, String language) throws ServiceException;
}
