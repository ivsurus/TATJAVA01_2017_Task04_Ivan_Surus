package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Movie;
import com.epam.catalog.controller.ControllerConstants;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public class AddMovie implements Command {


    @Override
    public String execute(String request) {
        Movie movie = new Movie();
        movie = initParameters(movie, request);
        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        EntityService<Movie> movieService = serviceObjectFactory.getMovieService();
        try{
            movieService.addEntity(movie);
        } catch (ServiceException e){
            return ControllerConstants.UNSUCCESSFUL_OPERATION;
        }
        return ControllerConstants.SUCCESSFUL_OPERATION;
    }

    private Movie initParameters (Movie movie, String request){
        String[] parameters = request.split(ControllerConstants.DELIMITER);
        movie.setTitle(parameters[1]);
        movie.setAuthor(parameters[2]);
        movie.setYear(parameters[3]);
        return movie;
    }

}
