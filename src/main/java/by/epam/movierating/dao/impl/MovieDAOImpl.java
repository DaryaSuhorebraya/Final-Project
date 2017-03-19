package by.epam.movierating.dao.impl;

import by.epam.movierating.bean.Movie;
import by.epam.movierating.dao.MovieDAO;
import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.util.DAOUtil;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 14.02.2017.
 */
public class MovieDAOImpl implements MovieDAO {
    private static final String SQL_GET_ALL_MOVIES= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie\n" +
            "INNER JOIN rating ON movie.id_movie=rating.id_movie GROUP BY movie.id_movie";
    private static final String SQL_MOVIE_BY_ID= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie\n" +
            "INNER JOIN rating ON movie.id_movie=rating.id_movie WHERE movie.id_movie=?";
    private static final String SQL_GET_MOVIES_BY_GENRE= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie\n" +
            "INNER JOIN rating ON movie.id_movie=rating.id_movie " +
            "INNER JOIN movie_genre ON movie.id_movie=movie_genre.id_movie "+
            "WHERE movie_genre.id_genre=? "+
            " GROUP BY movie.id_movie";
    private static final String SQL_GET_MOVIES_BY_COUNTRY= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie\n" +
            "INNER JOIN rating ON movie.id_movie=rating.id_movie " +
            "INNER JOIN movie_country ON movie.id_movie=movie_country.id_movie "+
            "WHERE movie_country.country_code=? "+
            " GROUP BY movie.id_movie";
    private static final String SQL_DELETE_MOVIE= "UPDATE movie SET is_deleted=1 WHERE id_movie=?";
    private static final String SQL_UPDATE_MOVIE_FIELD= "UPDATE movie SET @=? WHERE id_movie=?";
    private static final String RELEASE_YEAR="release_year";
    @Override
    public List<Movie> getAllMovies(String language) throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Movie> movieList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(DAOUtil.localizeStatement(SQL_GET_ALL_MOVIES,language));
            movieList=setMovieInfo(resultSet);
            return movieList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }
    }

    @Override
    public List<Movie> getMoviesByGenre(int idGenre, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Movie> movieList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_GET_MOVIES_BY_GENRE,language));
            statement.setInt(1,idGenre);
            resultSet=statement.executeQuery();
            movieList=setMovieInfo(resultSet);
            return movieList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }
    }

    @Override
    public List<Movie> getMoviesByCountry(String countryCode, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Movie> movieList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_GET_MOVIES_BY_COUNTRY,language));
            statement.setString(1,countryCode);
            resultSet=statement.executeQuery();
            movieList=setMovieInfo(resultSet);
            return movieList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }
    }

    @Override
    public boolean deleteMovie(int idMovie) throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(SQL_DELETE_MOVIE);
            statement.setInt(1,idMovie);
            int updatedRow=statement.executeUpdate();
            return updatedRow == 1;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement);
        }
    }

    @Override
    public Movie getMovieById(int idMovie, String language) throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Movie movie=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_MOVIE_BY_ID,language));
            statement.setInt(1,idMovie);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                movie=new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setReleaseYear(resultSet.getInt(3));
                movie.setDescription(resultSet.getString(4));
                movie.setRating(resultSet.getDouble(5));
                movie.setPosterPath(resultSet.getString(6));
            }
            return movie;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement,resultSet);
        }

    }

    @Override
    public boolean editMovieField(int idMovie, String field, String value, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            String preparedSqlMovieEditStatement=
                    DAOUtil.prepareMovieEditStatement(SQL_UPDATE_MOVIE_FIELD,field);
            statement= connection.prepareStatement(DAOUtil.localizeStatement(preparedSqlMovieEditStatement,language));
            statement.setInt(2,idMovie);
            if (field.equals(RELEASE_YEAR)){
                int year=Integer.parseInt(value);
                statement.setInt(1,year);
            } else {
                statement.setString(1,value);
            }
            int updatedRows=statement.executeUpdate();
            return updatedRows>0;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement);
        }
    }

    private List<Movie> setMovieInfo(ResultSet resultSet) throws SQLException{
        List<Movie> movieList=new ArrayList<>();
        while (resultSet.next()){
            Movie movie=new Movie();
            movie.setId(resultSet.getInt(1));
            movie.setTitle(resultSet.getString(2));
            movie.setReleaseYear(resultSet.getInt(3));
            movie.setDescription(resultSet.getString(4));
            movie.setRating(resultSet.getDouble(5));
            movie.setPosterPath(resultSet.getString(6));
            movieList.add(movie);
        }
        return movieList;
    }

}
