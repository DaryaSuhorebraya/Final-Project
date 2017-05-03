package by.epam.movierating.dao;



import by.epam.movierating.bean.Country;
import by.epam.movierating.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 30.01.2017.
 */
public interface CountryDAO {
    void updateCountry(String countryCode, String name, String language) throws DAOException;
    List<Country> getAllCountries(String language) throws DAOException;
    List<Country> getAllActiveCountries(String language) throws DAOException;
    Country getCountryByCode(String countryCode, String language) throws DAOException;
    List<Country> getCountriesByMovieId(int movieId, String language) throws DAOException;
    List<Country> getCountriesNotInMovie(int idMovie, String language) throws DAOException;


}
