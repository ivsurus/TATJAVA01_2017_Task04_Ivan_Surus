package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Movie;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.constant.ServiceConstant;
import com.epam.catalog.service.util.ParameterValidator;
import java.util.Set;


public class MovieServiceImpl implements EntityService<Movie> {

    @Override
    public void addEntity(Movie movie) throws ServiceException {
        boolean parametersAreValid = ParameterValidator.validateEntityParameters(movie);
        if (parametersAreValid) {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            EntityDAO<Movie> movieDAO = daoObjectFactory.getMovieDAO();
            try {
                movieDAO.addEntity(movie);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException(ServiceConstant.INVALID_PARAMETERS);
        }
    }

    @Override
    public Set<Movie> findEntity(SearchRequest searchRequestObject) throws ServiceException {
        boolean parametersAreValid = ParameterValidator.validateSearchRequestParameters(searchRequestObject);
        Set<Movie> movieSet;
        if (parametersAreValid) {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            EntityDAO<Movie> movieDAO = daoObjectFactory.getMovieDAO();
            try {
                movieSet = movieDAO.findEntity(searchRequestObject);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException(ServiceConstant.INVALID_PARAMETERS);
        }
        return movieSet;
    }

}
