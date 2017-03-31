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
            "INNER JOIN rating ON movie.id_movie=rating.id_movie " +
            "WHERE movie.is_deleted=0 " +
            "GROUP BY movie.id_movie";
    private static final String SQL_MOVIE_BY_ID= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie\n" +
            "INNER JOIN rating ON movie.id_movie=rating.id_movie " +
            "WHERE movie.id_movie=?";
    private static final String SQL_GET_MOVIE_BY_TITLE_EN= "SELECT movie.id_movie, movie.title_en, " +
            "movie.release_year, movie.description_en, avg(rating.mark) as avg_mark, movie.poster_path FROM movie\n" +
            "INNER JOIN rating ON movie.id_movie=rating.id_movie " +
            "WHERE movie.title_en=?";
    private static final String SQL_GET_MOVIES_BY_GENRE= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie\n" +
            "LEFT JOIN rating ON movie.id_movie=rating.id_movie " +
            "INNER JOIN movie_genre ON movie.id_movie=movie_genre.id_movie "+
            "WHERE movie_genre.id_genre=? AND movie.is_deleted=0 "+
            " GROUP BY movie.id_movie";
    private static final String SQL_GET_MOVIES_BY_COUNTRY= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie\n" +
            "LEFT JOIN rating ON movie.id_movie=rating.id_movie " +
            "INNER JOIN movie_country ON movie.id_movie=movie_country.id_movie "+
            "WHERE movie_country.country_code=? AND movie.is_deleted=0 "+
            " GROUP BY movie.id_movie";
    private static final String SQL_DELETE_MOVIE= "UPDATE movie SET is_deleted=1 WHERE id_movie=?";
    private static final String SQL_UPDATE_MOVIE_FIELD= "UPDATE movie SET @=? WHERE id_movie=?";
    private static final String RELEASE_YEAR="release_year";
    private static final String SQL_DELETE_GENRE_FOR_MOVIE="DELETE movie_genre FROM movie_genre " +
            "INNER JOIN genre ON movie_genre.id_genre=genre.id_genre " +
            "WHERE movie_genre.id_movie=? AND genre.name_ =?";
    private static final String SQL_ADD_GENRE_FOR_MOVIE="INSERT INTO movie_genre (id_movie, id_genre) " +
            "SELECT ? ,genre.id_genre FROM genre " +
            "WHERE genre.name_=?";
    private static final String SQL_GET_MOVIES_BY_GENRE_NAME= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie " +
            "LEFT JOIN rating ON movie.id_movie=rating.id_movie " +
            "INNER JOIN movie_genre ON movie.id_movie=movie_genre.id_movie "+
            "INNER JOIN genre ON movie_genre.id_genre=genre.id_genre "+
            "WHERE genre.name_ =? AND movie.is_deleted=0 "+
            " GROUP BY movie.id_movie";
    private static final String SQL_GET_MOVIES_BY_COUNTRY_NAME= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie " +
            "LEFT JOIN rating ON movie.id_movie=rating.id_movie " +
            "INNER JOIN movie_country ON movie.id_movie=movie_country.id_movie "+
            "INNER JOIN country ON movie_country.country_code=country.code "+
            "WHERE country.name_ =? AND movie.is_deleted=0 "+
            " GROUP BY movie.id_movie";
    private static final String SQL_GET_MOVIES_BY_ACTOR_INITIAL="SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie " +
            "LEFT JOIN rating ON movie.id_movie=rating.id_movie " +
            "INNER JOIN movie_actor ON movie.id_movie=movie_actor.id_movie "+
            "INNER JOIN actor ON movie_actor.id_actor=actor.id_actor "+
            "WHERE actor.first_name_ =? AND actor.last_name_ =? AND movie.is_deleted=0 "+
            " GROUP BY movie.id_movie";
    private static final String SQL_DELETE_COUNTRY_FOR_MOVIE="DELETE movie_country FROM movie_country " +
            "INNER JOIN country ON movie_country.country_code=country.code " +
            "WHERE movie_country.id_movie=? AND country.name_ =? ";
    private static final String SQL_ADD_COUNTRY_FOR_MOVIE="INSERT INTO movie_country (id_movie, country_code) " +
            "SELECT ? ,country.code FROM country " +
            "WHERE country.name_=?";
    private static final String SQL_DELETE_ACTOR_FOR_MOVIE="DELETE movie_actor FROM movie_actor " +
            "INNER JOIN actor ON movie_actor.id_actor=actor.id_actor " +
            "WHERE movie_actor.id_movie=? AND actor.first_name_ =? AND actor.last_name_ =?";
    private static final String SQL_ADD_ACTOR_FOR_MOVIE="INSERT INTO movie_actor (id_movie, id_actor) " +
            "SELECT ? ,id_actor FROM actor " +
            "WHERE actor.first_name_=? AND actor.last_name_=?";
    private static final String SQL_ADD_MOVIE="INSERT INTO movie (title_ru, title_en, release_year, description_ru, " +
            "description_en) VALUES (?,?,?,?,?)";
    private static final String SQL_GET_TOP_MOVIES= "SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_, avg(rating.mark) as avg_mark, movie.poster_path FROM movie " +
            "INNER JOIN rating ON movie.id_movie=rating.id_movie " +
            "WHERE movie.is_deleted=0 " +
            "GROUP BY movie.id_movie "+
            "ORDER BY avg_mark DESC";
    private static final String SQL_GET_NEWEST_MOVIES="SELECT movie.id_movie, movie.title_, " +
            "movie.release_year, movie.description_ , avg(rating.mark) as avg_mark, movie.poster_path FROM movie " +
            "INNER JOIN rating ON movie.id_movie=rating.id_movie " +
            "WHERE movie.is_deleted=0 " +
            "GROUP BY movie.id_movie " +
            "ORDER BY movie.release_year DESC";
    private static final String SQL_UPDATE_MOVIE_POSTER="UPDATE movie SET poster_path =? WHERE id_movie=?";
    @Override
    public List<Movie> getAllMovies(String language)
            throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Movie> movieList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(DAOUtil.
                    localizeStatement(SQL_GET_ALL_MOVIES,language));
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
    public List<Movie> getTopMovies(String language)
            throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Movie> movieList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(DAOUtil.
                    localizeStatement(SQL_GET_TOP_MOVIES,language));
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
                movie=createMovie(resultSet);
               /* movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setReleaseYear(resultSet.getInt(3));
                movie.setDescription(resultSet.getString(4));
                movie.setRating(resultSet.getDouble(5));
                movie.setPosterPath(resultSet.getString(6));*/
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

    @Override
    public boolean deleteGenreForMovie(String genreName, String language, int idMovie)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_DELETE_GENRE_FOR_MOVIE,language));
            statement.setInt(1,idMovie);
            statement.setString(2, genreName);
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
    public boolean addGenreForMovie(int idMovie, String genreName, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_ADD_GENRE_FOR_MOVIE,language));
            statement.setInt(1,idMovie);
            statement.setString(2, genreName);
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
    public List<Movie> getMoviesByGenreName(String genreName, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Movie> movieList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_GET_MOVIES_BY_GENRE_NAME,language));
            statement.setString(1,genreName);
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
    public List<Movie> getMoviesByCountryName(String countryName, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Movie> movieList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_GET_MOVIES_BY_COUNTRY_NAME,language));
            statement.setString(1,countryName);
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
    public List<Movie> getMoviesByActorInitial(String firstName,
                                               String lastName, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Movie> movieList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.localizeStatement(SQL_GET_MOVIES_BY_ACTOR_INITIAL,language));
            statement.setString(1,firstName);
            statement.setString(2, lastName);
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
    public boolean deleteCountryForMovie(String countryName, String language, int movieId)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.
                    localizeStatement(SQL_DELETE_COUNTRY_FOR_MOVIE,language));
            statement.setInt(1,movieId);
            statement.setString(2, countryName);
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
    public boolean addCountryForMovie(int idMovie, String countryName, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.
                    localizeStatement(SQL_ADD_COUNTRY_FOR_MOVIE,language));
            statement.setInt(1,idMovie);
            statement.setString(2, countryName);
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
    public boolean deleteActorForMovie(String firstName, String lastName,
                                       String language, int movieId)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.
                    localizeStatement(SQL_DELETE_ACTOR_FOR_MOVIE,language));
            statement.setInt(1,movieId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
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
    public boolean addActorForMovie(int idMovie, String firstName,
                                    String lastName, String language)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(DAOUtil.
                    localizeStatement(SQL_ADD_ACTOR_FOR_MOVIE,language));
            statement.setInt(1,idMovie);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
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
    public int addMovie(String titleEn, String titleRu,
                          int releaseYear, String descrEn,
                          String descrRu)
            throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(SQL_ADD_MOVIE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,titleRu);
            statement.setString(2, titleEn);
            statement.setInt(3, releaseYear);
            statement.setString(4, descrRu);
            statement.setString(5, descrEn);
            int updatedRows=statement.executeUpdate();
            if (updatedRows>0){
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return (int)generatedKeys.getLong(1);
                }
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection,statement);
        }
        return 0;
    }

    @Override
    public Movie getMovieByTitleEn(String titleEn) throws DAOException {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Movie movie=null;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.prepareStatement(SQL_GET_MOVIE_BY_TITLE_EN);
            statement.setString(1,titleEn);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                movie=createMovie(resultSet);
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
    public boolean uploadMoviePoster(int idMovie, String imgPath)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_MOVIE_POSTER);
            preparedStatement.setString(1, imgPath);
            preparedStatement.setInt(2, idMovie);
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.close(connection, preparedStatement);
        }
    }

    @Override
    public List<Movie> getNewestMovies(String language) throws DAOException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Movie> movieList;
        try {
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            connection=connectionPool.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(DAOUtil.
                    localizeStatement(SQL_GET_NEWEST_MOVIES,language));
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

    private List<Movie> setMovieInfo(ResultSet resultSet) throws SQLException{
        List<Movie> movieList=new ArrayList<>();
        while (resultSet.next()){
           /* Movie movie=new Movie();
            movie.setId(resultSet.getInt(1));
            movie.setTitle(resultSet.getString(2));
            movie.setReleaseYear(resultSet.getInt(3));
            movie.setDescription(resultSet.getString(4));
            movie.setRating(resultSet.getDouble(5));
            movie.setPosterPath(resultSet.getString(6));*/
            Movie movie=createMovie(resultSet);
            movieList.add(movie);
        }
        return movieList;
    }
    private Movie createMovie(ResultSet resultSet) throws SQLException{
        Movie movie=new Movie();
        movie.setId(resultSet.getInt(1));
        movie.setTitle(resultSet.getString(2));
        movie.setReleaseYear(resultSet.getInt(3));
        movie.setDescription(resultSet.getString(4));
        movie.setRating(resultSet.getDouble(5));
        movie.setPosterPath(resultSet.getString(6));
        return movie;
    }

}
