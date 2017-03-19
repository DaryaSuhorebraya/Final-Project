package by.epam.movierating.controller.listener;

import by.epam.movierating.command.constant.AttributeName;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Даша on 02.03.2017.
 */
public class RoleListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute(AttributeName.ROLE,AttributeName.GUEST);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
