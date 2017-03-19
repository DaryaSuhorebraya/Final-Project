package by.epam.movierating.dao.exception;

/**
 * Created by Даша on 27.01.2017.
 */
public class ConnectionPoolException extends Exception {
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException() {
        super();
    }
    public ConnectionPoolException(String message){
        super(message);
    }
    public ConnectionPoolException(Exception e){
        super(e);
    }
    public ConnectionPoolException(String message, Exception e){
        super(message,e);
    }
}
