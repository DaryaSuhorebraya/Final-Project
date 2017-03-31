package by.epam.movierating.controller;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.CommandName;
import by.epam.movierating.command.actor.*;
import by.epam.movierating.command.admin.*;
import by.epam.movierating.command.country.ViewAllCountriesCommand;
import by.epam.movierating.command.country.ViewCountriesNotInMovieCommand;
import by.epam.movierating.command.general.RedirectCommand;
import by.epam.movierating.command.general.WelcomePageCommand;
import by.epam.movierating.command.genre.ViewAllGenresCommand;
import by.epam.movierating.command.genre.ViewGenresNotInMovieCommand;
import by.epam.movierating.command.movie.*;
import by.epam.movierating.command.review.ViewAllReviewsOrderByDate;
import by.epam.movierating.command.upload.UploadActorImageCommand;
import by.epam.movierating.command.upload.UploadMoviePosterCommand;
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

    private CommandProvider(){
        repository.put(CommandName.WELCOME_PAGE,new WelcomePageCommand());
        repository.put(CommandName.LOGIN,new LoginCommand());
        repository.put(CommandName.LOGOUT,new LogoutCommand());
        repository.put(CommandName.VIEW_ALL_MOVIES,new ViewMoviesCommand());
        repository.put(CommandName.VIEW_MOVIES_BY_GENRE,new ViewMoviesByGenreCommand());
        //5

        repository.put(CommandName.DELETE_MOVIE,new DeleteMovieCommand());
        repository.put(CommandName.CHANGE_LANGUAGE,new ChangeLanguageCommand());
        repository.put(CommandName.REGISTRATION,new RegistrationCommand());
        repository.put(CommandName.REDIRECT,new RedirectCommand());
        repository.put(CommandName.VIEW_ALL_USERS,new ViewUsersCommand());
        //10

        repository.put(CommandName.VIEW_USER,new ViewUserCommand());
        repository.put(CommandName.VIEW_MOVIES_BY_COUNTRY,new ViewMoviesByCountryCommand());
        repository.put(CommandName.EDIT_USER,new EditUserCommand());
        repository.put(CommandName.CHANGE_BAN_STATUS,new ChangeBanStatusCommand());
        repository.put(CommandName.DELETE_USER, new DeleteUserCommand());
        //15

        repository.put(CommandName.VIEW_MOVIE,new ViewMovieCommand());
        repository.put(CommandName.EDIT_MOVIE, new EditMovieCommand());
        repository.put(CommandName.DELETE_GENRE_FOR_MOVIE, new DeleteGenreForMovieCommand());
        repository.put(CommandName.VIEW_GENRES_NOT_IN_MOVIE, new ViewGenresNotInMovieCommand());
        repository.put(CommandName.ADD_GENRE_FOR_MOVIE, new AddGenreForMovieCommand());
        //20

        repository.put(CommandName.VIEW_MOVIES_BY_GENRE_NAME, new ViewMoviesByGenreNameCommand());
        repository.put(CommandName.VIEW_MOVIES_BY_COUNTRY_NAME, new ViewMoviesByCountryNameCommand());
        repository.put(CommandName.VIEW_MOVIES_BY_ACTOR_INITIAL, new ViewMoviesByActorInitialCommand());
        repository.put(CommandName.DELETE_COUNTRY_FOR_MOVIE, new DeleteCountryForMovieCommand());
        repository.put(CommandName.VIEW_COUNTRIES_NOT_IN_MOVIE, new ViewCountriesNotInMovieCommand());
        //25

        repository.put(CommandName.ADD_COUNTRY_FOR_MOVIE, new AddCountryForMovieCommand());
        repository.put(CommandName.DELETE_ACTOR_FOR_MOVIE, new DeleteActorForMovieCommand());
        repository.put(CommandName.ADD_ACTOR_FOR_MOVIE, new AddActorForMovieCommand());
        repository.put(CommandName.VIEW_ACTORS_NOT_IN_MOVIE, new ViewActorsNotInMovieCommand());
        repository.put(CommandName.ADD_MOVIE, new AddMovieCommand());
        //30

        repository.put(CommandName.VIEW_ALL_GENRES, new ViewAllGenresCommand());
        repository.put(CommandName.VIEW_ALL_COUNTRIES, new ViewAllCountriesCommand());
        repository.put(CommandName.VIEW_ALL_ACTORS_JSON, new ViewAllActorsJsonCommand());
        repository.put(CommandName.VIEW_ALL_REVIEWS_ORDER_BY_DATE, new ViewAllReviewsOrderByDate());
        repository.put(CommandName.VIEW_ALL_ACTORS, new ViewAllActorsCommand());
        //35

        repository.put(CommandName.VIEW_TOP_MOVIES, new ViewTopMoviesCommand());
        repository.put(CommandName.DELETE_ACTOR, new DeleteActorCommand());
        repository.put(CommandName.EDIT_ACTOR, new EditActorCommand());
        repository.put(CommandName.ADD_ACTOR, new AddActorCommand());
        repository.put(CommandName.VIEW_NEWEST_MOVIES, new ViewNewestMoviesCommand());
        //40

        repository.put(CommandName.UPLOAD_ACTOR_IMAGE, new UploadActorImageCommand());
        repository.put(CommandName.UPLOAD_MOVIE_POSTER, new UploadMoviePosterCommand());

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
