package by.epam.movierating.command.user;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.util.PagePathUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Даша on 16.02.2017.
 */
public class ChangeLanguageCommand implements Command {
    private static final Logger logger=Logger.getLogger(ChangeLanguageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String language=(String) session.getAttribute(AttributeName.LANGUAGE);
        if (language.equals(AttributeName.RUSSIAN)) {
            session.setAttribute(AttributeName.LANGUAGE,AttributeName.ENGLISH);
        } else {
            session.setAttribute(AttributeName.LANGUAGE, AttributeName.RUSSIAN);
        }
        //reloadData(session);
        String pagePath= PagePathUtil.getPagePath(session);
        response.sendRedirect(pagePath);
    }
   /* private void reloadData(HttpSession session){
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        GenreService genreService=serviceFactory.getGenreService();
        CountryService countryService=serviceFactory.getCountryService();
        List<Genre> genreList= null;
        List<Country> countryList=null;
        try {
            String language=(String)session.getAttribute(AttributeName.LANGUAGE);
            genreList = genreService.getAllActiveGenre(language);
            countryList=countryService.getAllActiveCountries(language);
        } catch (ServiceException e) {
            logger.error(e);
        ServletContext servletContext=session.getServletContext();
        //session.setAttribute(AttributeName.GENRES,genreList);
        //session.setAttribute(AttributeName.COUNTRIES,countryList);
        servletContext.setAttribute(AttributeName.GENRES, genreList);
        servletContext.setAttribute(AttributeName.COUNTRIES, countryList);
    }*/
}
