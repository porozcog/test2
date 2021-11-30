package com.tmobile.test.tmobiletest.mapping;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.test.tmobiletest.entity.Book;
import com.tmobile.test.tmobiletest.model.BookType;
import com.tmobile.test.tmobiletest.model.CreateBookRequest;
import com.tmobile.test.tmobiletest.model.CreateBookResponse;
import com.tmobile.test.tmobiletest.model.GetBooksResponse;

public class LibraryMapping {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryMapping.class);
	
	public static Book requestToBook( CreateBookRequest bookRequest )
	{
		Book book = new Book();
		try {
			
			BeanUtilsBean bub = BeanUtilsBean.getInstance();
			bub.copyProperties(book, bookRequest);
			
			
		} catch (Exception e) {
			LOGGER.error("Error {}", e);
		}
		return book;
	}
	
	
	public static CreateBookResponse bookResponseMap( Book book)
	{
		CreateBookResponse  bookResponse = new CreateBookResponse();
		
		try 
		{
			BeanUtilsBean bub = BeanUtilsBean.getInstance();
			bub.copyProperties(bookResponse, book);
		} catch (Exception e) {
			LOGGER.error("Error {}", e);
		}
		
		return bookResponse;
	}
	
	
	public static GetBooksResponse mapResponse( List<Book > listOfbooks )
	{
		GetBooksResponse booksResponse = new GetBooksResponse();
		
		 List<BookType> books = new ArrayList<>();
		 
		 BeanUtilsBean bub = BeanUtilsBean.getInstance();
		 
		 books = listOfbooks.stream().map(bookItem -> {
				 BookType bookType = new BookType();
				 try {
					bub.copyProperties(bookType, bookItem);
				} catch (IllegalAccessException | InvocationTargetException e) {
					LOGGER.error("Error {}", e);
				}
				 return bookType;
			 }).collect(Collectors.toList());
		 
		 booksResponse.setBooks(books);
		 
		 return booksResponse;
	}
	
}
