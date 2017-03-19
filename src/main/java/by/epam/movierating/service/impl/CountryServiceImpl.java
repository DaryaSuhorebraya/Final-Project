package by.epam.movierating.service.impl;

import by.epam.movierating.bean.Country;
import by.epam.movierating.dao.CountryDAO;
import by.epam.movierating.dao.exception.DAOException;
import by.epam.movierating.dao.factory.DAOFactory;
import by.epam.movierating.service.CountryService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.util.Validator;

import java.util.List;

/**
 * Created by Даша on 06.03.2017.
 */
public class CountryServiceImpl implements CountryService {
    @Override
    public List<Country> getAllCountries(String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        List<Country> countryList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            CountryDAO countryDAO=daoFactory.getCountryDAO();
            countryList=countryDAO.getAllCountries(language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return countryList;
    }

    @Override
    public List<Country> getAllActiveCountries(String language)
            throws ServiceException {
        Validator.validateLanguage(language);
        List<Country> countryList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            CountryDAO countryDAO=daoFactory.getCountryDAO();
            countryList=countryDAO.getAllActiveCountries(language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return countryList;
    }

    @Override
    public List<Country> getCountriesByMovieId(int idMovie, String language)
            throws ServiceException {
        Validator.validateIntData(idMovie);
        Validator.validateLanguage(language);
        List<Country> countryList;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            CountryDAO countryDAO=daoFactory.getCountryDAO();
            countryList=countryDAO.getCountriesByMovieId(idMovie,language);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return countryList;
    }
}
