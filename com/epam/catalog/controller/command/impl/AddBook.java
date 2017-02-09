package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.ControllerConstants;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;


public class AddBook implements Command {


    @Override
    public String execute(String request) {
        Book book = new Book();
        book = initParameters(book,request);
        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        EntityService<Book> bookService = serviceObjectFactory.getBookService();
        try{
            bookService.addEntity(book);
        } catch (ServiceException e){
            return ControllerConstants.UNSUCCESSFUL_OPERATION;
        }
        return ControllerConstants.SUCCESSFUL_OPERATION;
    }


    private Book initParameters (Book book, String request){
        String[] parameters = request.split(ControllerConstants.DELIMITER);
        book.setTitle(parameters[1]);
        book.setAuthor(parameters[2]);
        book.setYear(parameters[3]);
        return book;
    }
}
