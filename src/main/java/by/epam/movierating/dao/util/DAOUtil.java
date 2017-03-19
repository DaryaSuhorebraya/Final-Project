package by.epam.movierating.dao.util;

import by.epam.movierating.dao.connectionpool.ConnectionPool;
import by.epam.movierating.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Даша on 14.02.2017.
 */
public class DAOUtil {
    private static final Logger logger=Logger.getLogger(DAOUtil.class);

    public static String localizeStatement(String sql, String language){
        StringBuilder stringBuilder =new StringBuilder(sql);
        String localizedFieldRegEx="([a-zA-Z]*_(,|\\s|=))";
        Pattern localizedFieldPattern= Pattern.compile(localizedFieldRegEx);
        Matcher localizedFieldMatcher=localizedFieldPattern.matcher(sql);
        int position;
        int fieldAmount=0;
        while (localizedFieldMatcher.find()) {
            position=localizedFieldMatcher.end();
            stringBuilder.insert(position-1+fieldAmount*2,language.substring(0,2));
            fieldAmount++;
        }
        return stringBuilder.toString();
    }

    public static String prepareMovieEditStatement(String sql, String field){
        return sql.replaceAll("[@]", field);
    }
    public static void close(Connection connection,                                                                                          Statement statement,
                             ResultSet resultSet){
        closeConnection(connection);
        closeStatement(statement);
        closeResultSet(resultSet);
    }
    public static void close(Connection connection,
                             ResultSet resultSet){
        closeConnection(connection);
        closeResultSet(resultSet);
    }
    public static void close(Connection connection,
                             Statement statement){
        closeConnection(connection);
        closeStatement(statement);
    }
    private static void closeConnection(Connection connection){
        if (connection!= null){
            ConnectionPool connectionPool=ConnectionPool.getInstance();
            try {
                connectionPool.freeConnection(connection);
            } catch (ConnectionPoolException e) {
                logger.error("Can not free connection",e);
            }
        }
    }
    private static void closeStatement(Statement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Can not close statement",e);
            }
        }
    }
    private static void closeResultSet(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Can not close resultSet",e);
            }
        }
    }


}
