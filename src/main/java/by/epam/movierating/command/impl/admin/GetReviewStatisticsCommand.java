package by.epam.movierating.command.impl.admin;

import by.epam.movierating.bean.dto.StaticticsDTO;
import by.epam.movierating.command.Command;
import by.epam.movierating.command.constant.AttributeName;
import by.epam.movierating.service.ReviewService;
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
 * Created by Даша on 05.04.2017.
 */
public class GetReviewStatisticsCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetReviewStatisticsCommand.class);
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(AttributeName.LANGUAGE);

        ServiceFactory serviceFactory = new ServiceFactory();
        try {
            ReviewService reviewService = serviceFactory.getReviewService();
            List<StaticticsDTO> staticticsDTOList = reviewService.getReviewStatistics(language);

            String json = new Gson().toJson(staticticsDTOList);
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(ENCODING);
            response.getWriter().print(json);
        } catch (ServiceException e) {
            logger.error(e);
        }

    }
}
