package by.epam.movierating.dao;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 18.03.2017.
 */
public interface ActorDAO {
    List<Actor> getActorsByMovieId(int idMovie, String language) throws DAOException;
    List<Actor> getActorsNotInMovie(int idMovie, String language) throws DAOException;
    List<Actor> getAllActors(String language) throws DAOException;
    boolean deleteActor(int idActor) throws DAOException;
    boolean editActor(int idActor, String firstName, String lastName, String language) throws DAOException;
    boolean uploadActorImage(int idActor, String filePath) throws DAOException;
    int addActor(String firstNameEn, String lastNameEn, String firstNameRu, String lastNameRu) throws DAOException;
}
