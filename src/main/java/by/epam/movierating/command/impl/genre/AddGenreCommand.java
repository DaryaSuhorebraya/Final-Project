package by.epam.movierating.command.impl.genre;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.service.GenreService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Даша on 06.04.2017.
 */
public class AddGenreCommand implements Command{
    private static final Logger logger = Logger.getLogger(AddGenreCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameRu = request.getParameter(ParameterName.NAME_RU);
        String nameEn = request.getParameter(ParameterName.NAME_EN);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        GenreService genreService = serviceFactory.getGenreService();
        try {
            boolean result = genreService.addGenre(nameRu, nameEn);
            response.getWriter().print(result);
        } catch (ServiceException e) {
            logger.error(e);
            response.getWriter().print(false);
        }
    }
}
