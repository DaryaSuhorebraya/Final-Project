package by.epam.movierating.command.impl.actor;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.JSPPageName;
import by.epam.movierating.command.constant.ParameterName;
import by.epam.movierating.command.security.RoleType;
import by.epam.movierating.command.security.SecurityManager;
import by.epam.movierating.service.ActorService;
import by.epam.movierating.service.exception.ServiceException;
import by.epam.movierating.service.factory.ServiceFactory;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Даша on 30.03.2017.
 */
public class AddActorCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddActorCommand.class);
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (SecurityManager.getInstance().checkRoles(request, response, RoleType.ADMIN)) {

            String firstNameEn = request.getParameter(ParameterName.FIRST_NAME_EN);
            String lastNameEn = request.getParameter(ParameterName.LAST_NAME_EN);
            String firstNameRu = request.getParameter(ParameterName.FIRST_NAME_RU);
            String lastNameRu = request.getParameter(ParameterName.LAST_NAME_RU);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ActorService actorService = serviceFactory.getActorService();
            try {
                int id = actorService.addActor(firstNameEn, lastNameEn, firstNameRu, lastNameRu);
                String json = new Gson().toJson(id);
                response.setContentType(CONTENT_TYPE);
                response.setCharacterEncoding(ENCODING);
                response.getWriter().print(json);
            } catch (ServiceException e) {
                logger.error(e);
                response.sendRedirect(JSPPageName.ERROR_500_PAGE);
            }
        }
    }
}
