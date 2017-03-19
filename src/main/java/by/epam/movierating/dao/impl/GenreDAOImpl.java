package by.epam.movierating.dao.impl;

import by.epam.movierating.bean.Genre;
import by.epam.movierating.dao.GenreDAO;
import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.util.DAOUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 22.02.2017.
 */
public class GenreDAOImpl implements GenreDAO {
    private static final String SQL_GET_ALL_GENRES = "SELECT id_genre,name_ FROM genre WHERE is_deleted=0";
    private static final String SQL_GET_ALL_ACTIVE_GENRES="SELECT genre.id_genre, genre.name_ FROM genre " +
            "INNER JOIN movie_genre ON genre.id_genre=movie_genre.id_genre " +
            "WHERE genre.is_deleted=0 "+
            "GROUP BY genre.id_genre";
    private static final String SQL_GET_GENRES_BY_ID_MOVIE="SELECT genre.id_genre, genre.name_ FROM genre " +
            "INNER JOIN movie_genre ON genre.id_genre=movie_genre.id_genre " +
            "WHERE genre.is_deleted=0 and movie_genre.id_movie=? "+
            "GROUP BY genre.id_genre";
    private static final String SQL_DELETE_GENRE= "UPDATE genre SET is_deleted=1 WHERE id_genre=?";
    private static final String SQL_UPDATE_GENRE= "UPDATE genre SET name_=? WHERE id_genre=?";
    @Override
    public List<Genre> getAllGenres(String language) throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Genre> genreList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(DAOUtil.localizeStatement(SQL_GET_ALL_GENRES,language));
            genreList=setGenreInfo(resultSet);
            return genreList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection ",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }
    }

    @Override
    public List<Genre> getAllActiveGenres(String language) throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Genre> genreList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(DAOUtil.localizeStatement(SQL_GET_ALL_ACTIVE_GENRES,language));
            genreList=setGenreInfo(resultSet);
            return genreList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }
    }

    @Override
    public void deleteGenre(int idGenre) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(SQL_DELETE_GENRE);
            preparedStatement.setInt(1,idGenre);
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement);
        }
    }

    @Override
    public void updateGenre(int idGenre, String name, String language) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_UPDATE_GENRE,language));
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,idGenre);
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement);
        }
    }

    @Override
    public List<Genre> getGenresByIdMovie(int idMovie, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_GET_GENRES_BY_ID_MOVIE,language));
            preparedStatement.setInt(1,idMovie);
            resultSet=preparedStatement.executeQuery();
            return setGenreInfo(resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,preparedStatement,resultSet);
        }
    }

    private List<Genre> setGenreInfo(ResultSet resultSet) throws SQLException{
        List<Genre> genreList=new ArrayList<>();
        while (resultSet.next()){
            Genre genre=new Genre();
            genre.setId(resultSet.getInt(1));
            genre.setName(resultSet.getString(2));
            genreList.add(genre);
        }
        return genreList;
    }

}
