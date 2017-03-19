package by.epam.movierating.dao.exception;

/**
 * Created by Даша on 30.01.2017.
 */
public class DAOException extends Exception {
    private static long serialVersionUID = 1L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }

    public DAOException(Exception e) {
        super(e);
    }
}
