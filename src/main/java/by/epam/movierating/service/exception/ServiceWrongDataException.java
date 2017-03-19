package by.epam.movierating.service.exception;

/**
 * Created by Даша on 14.02.2017.
 */
public class ServiceWrongDataException extends ServiceException {
    public ServiceWrongDataException(){
        super();
    }
    public ServiceWrongDataException(String message){
        super(message);
    }
    public ServiceWrongDataException(Exception e){
        super(e);
    }
    public ServiceWrongDataException(String message, Exception e){
        super(message,e);
    }
}
