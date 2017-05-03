package by.epam.movierating.service.util;

import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.service.exception.ServiceWrongDataException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Даша on 27.02.2017.
 */
public class Validator {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4}$";
    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 25;
    private static final int MAX_LOGIN_LENGTH = 25;

    private static final String LANGUAGE_EN="en_EN";
    private static final String LANGUAGE_RU="ru_RU";
    private static final String BAN_EN="Ban";
    private static final String UNBAN_EN="Unban";
    private static final String BAN_RU="Поставить бан";
    private static final String UNBAN_RU="Отменить бан";

    public static void validateEmail(String email) throws ServiceWrongDataException {
        if(email == null || email.isEmpty() || !email.matches(EMAIL_PATTERN)){
            throw new ServiceWrongDataException("Invalid email");
        }
    }
    public static void validateLanguage(String language) throws ServiceWrongDataException{
        if (!language.equals(LANGUAGE_EN)&&!language.equals(LANGUAGE_RU)){
            throw new ServiceWrongDataException("No such allowed language");
        }
    }
    public static void validatePassword(byte[] password) throws ServiceWrongDataException {
        if(password == null || password.length>MAX_PASSWORD_LENGTH || password.length<MIN_PASSWORD_LENGTH){
            System.out.println(password);
            throw new ServiceWrongDataException("Invalid password");
        }
    }
    public static void validatePassword(byte[] password, byte[] confirmPassword) throws ServiceWrongDataException {
        validatePassword(password);
        if (!Arrays.equals(password,confirmPassword)){
            throw new ServiceWrongDataException("Confirmed password does not equal to input password");
        }
    }
    public static void validateLogin(String login) throws ServiceWrongDataException {
        if(login == null || login.isEmpty() || login.length() > MAX_LOGIN_LENGTH){
            throw new ServiceWrongDataException("Invalid login");
        }
    }
    public static void validateStringData(String... stringData) throws ServiceWrongDataException {
        for (String data: stringData
             ) {
            if(data == null || data.isEmpty()){
                throw new ServiceWrongDataException("Invalid data"+data);
            }
        }
    }
    public static void validateIntData(int... intData) throws ServiceWrongDataException{
        for (int data: intData
             ) {
            if (data==0 ||data<0 ){
                throw new ServiceWrongDataException("Incorrect data"+data);
            }
        }
    }
    public static Date validateDate(String date) throws ServiceWrongDataException{
        Date dateRegister;
        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
            dateRegister=dateFormat.parse(date);
        } catch (ParseException e) {
            throw new ServiceWrongDataException("Incorrect date format"+date);
        }
        return dateRegister;
    }
    public static boolean validateAdminString(String isAdminString)
            throws ServiceWrongDataException{
        switch (isAdminString){
            case "Yes ": {
                return true;
            }
            case "Да ": {
                return true;
            }
            case "No ": {
                return false;
            }
            case "Нет ": {
                return false;
            }
            default: {
                throw new ServiceWrongDataException("Invalid value for admin string"+isAdminString);
            }
        }
    }
    public static boolean validateBannedString(String isBannedString)
            throws ServiceWrongDataException{
        switch (isBannedString){
            case "Yes ": {
                return true;
            }
            case "Да ": {
                return true;
            }
            case "No ": {
                return false;
            }
            case "Нет ": {
                return false;
            }
            default: {
                throw new ServiceWrongDataException("Invalid value for banned string: "+isBannedString);
            }
        }
    }
    public static void validateBanStatus(String status)
            throws ServiceWrongDataException{
        if(!status.equals(BAN_EN) && !status.equals(UNBAN_EN)
                && !status.equals(BAN_RU)&& !status.equals(UNBAN_RU)){
            throw new ServiceWrongDataException("Invalid value of a ban status: "+status);
        }
    }

}
