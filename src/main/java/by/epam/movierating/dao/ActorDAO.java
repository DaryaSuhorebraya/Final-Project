package by.epam.movierating.dao;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 18.03.2017.
 */
public interface ActorDAO {
    List<Actor> getActorsByMovieId(int idMovie, String language) throws DAOException;
}
