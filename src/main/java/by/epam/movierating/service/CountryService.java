package by.epam.movierating.service;

import by.epam.movierating.bean.Country;
import by.epam.movierating.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Даша on 06.03.2017.
 */
public interface CountryService {
    List<Country> getAllCountries(String language) throws ServiceException;
    List<Country> getAllActiveCountries(String language) throws ServiceException;
    List<Country> getCountriesByMovieId(int idMovie, String language) throws ServiceException;
    List<Country> getCountriesNotInMovie(int idMovie, String language) throws ServiceException;
}
