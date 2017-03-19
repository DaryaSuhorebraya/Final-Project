package by.epam.movierating.dao.impl;

import by.epam.movierating.bean.Actor;
import by.epam.movierating.dao.ActorDAO;
import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.util.DAOUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 18.03.2017.
 */
public class ActorDAOImpl implements ActorDAO {
    private static final String SQL_GET_ACTORS_BY_ID_MOVIE="SELECT actor.id_actor, actor.first_name_, actor.last_name_ FROM actor " +
            "INNER JOIN movie_actor ON actor.id_actor=movie_actor.id_actor " +
            "WHERE actor.is_deleted=0 and movie_actor.id_movie=? "+
            "GROUP BY actor.id_actor";
    @Override
    public List<Actor> getActorsByMovieId(int idMovie, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_GET_ACTORS_BY_ID_MOVIE,language));
            preparedStatement.setInt(1,idMovie);
            resultSet=preparedStatement.executeQuery();
            return setActorInfo(resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement,resultSet);
        }
    }
    private List<Actor> setActorInfo(ResultSet resultSet) throws SQLException{
        List<Actor> actorList=new ArrayList<>();
        while (resultSet.next()){
            Actor actor=new Actor();
            actor.setId(resultSet.getInt(1));
            actor.setFirstName(resultSet.getString(2));
            actor.setLastName(resultSet.getString(3));
            actorList.add(actor);
        }
        return actorList;
    }
}
