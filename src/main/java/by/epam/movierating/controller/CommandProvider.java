package by.epam.movierating.controller;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.CommandName;
import by.epam.movierating.command.admin.*;
import by.epam.movierating.command.general.RedirectCommand;
import by.epam.movierating.command.general.WelcomePageCommand;
import by.epam.movierating.command.movie.*;
import by.epam.movierating.command.user.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Даша on 14.02.2017.
 */
public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new HashMap<>();

    public static CommandProvider getInstance() {
        return instance;
    }

    CommandProvider(){
        repository.put(CommandName.WELCOME_PAGE,new WelcomePageCommand());
        repository.put(CommandName.LOGIN,new LoginCommand());
        repository.put(CommandName.LOGOUT,new LogoutCommand());
        repository.put(CommandName.VIEW_ALL_MOVIES,new ViewMoviesCommand());
        repository.put(CommandName.VIEW_MOVIES_BY_GENRE,new ViewMoviesByGenreCommand());

        repository.put(CommandName.DELETE_MOVIE,new DeleteMovieCommand());
        repository.put(CommandName.CHANGE_LANGUAGE,new ChangeLanguageCommand());
        repository.put(CommandName.REGISTRATION,new RegistrationCommand());
        repository.put(CommandName.REDIRECT,new RedirectCommand());
        repository.put(CommandName.VIEW_ALL_USERS,new ViewUsersCommand());

        repository.put(CommandName.VIEW_USER,new ViewUserCommand());
        repository.put(CommandName.VIEW_MOVIES_BY_COUNTRY,new ViewMoviesByCountryCommand());
        repository.put(CommandName.EDIT_USER,new EditUserCommand());
        repository.put(CommandName.CHANGE_BAN_STATUS,new ChangeBanStatusCommand());
        repository.put(CommandName.DELETE_USER, new DeleteUserCommand());

        repository.put(CommandName.VIEW_MOVIE,new ViewMovieCommand());
        repository.put(CommandName.EDIT_MOVIE, new EditMovieCommand());
    }

    Command getCommand(String name){
        CommandName commandName;
        Command command=null;
        try{
            if (name!=null) {
                commandName = (CommandName.valueOf(name.toUpperCase().replace("-", "_")));
                System.out.println(commandName);
                command = repository.get(commandName);
            } else {
                command=repository.get(CommandName.WELCOME_PAGE);
            }
        } catch(IllegalArgumentException | NullPointerException e){
           // command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
