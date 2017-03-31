package by.epam.movierating.command.genre;

import by.epam.movierating.bean.Genre;
import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.service.GenreService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.factory.ServiceFactory;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Даша on 24.03.2017.
 */
public class ViewAllGenresCommand implements Command {
    private static final Logger logger=Logger.getLogger(ViewAllGenresCommand.class);
    private static final String CONTENT_TYPE="application/json";
    private static final String ENCODING="UTF-8";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String language = (String) session.getAttribute(AttributeName.LANGUAGE);
        ServiceFactory serviceFactory=new ServiceFactory();
        try {
            GenreService genreService=serviceFactory.getGenreService();
            List<Genre> genreList=genreService.getAllGenres(language);

            String json = new Gson().toJson(genreList);
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(ENCODING);
            response.getWriter().print(json);
        } catch (ServiceException e) {
            logger.error(e);
        }

    }
}
