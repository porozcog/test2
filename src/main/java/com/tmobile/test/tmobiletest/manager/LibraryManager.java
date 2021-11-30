package com.tmobile.test.tmobiletest.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmobile.test.tmobiletest.entity.Book;
import com.tmobile.test.tmobiletest.mapping.LibraryMapping;
import com.tmobile.test.tmobiletest.model.CreateBookRequest;
import com.tmobile.test.tmobiletest.model.CreateBookResponse;
import com.tmobile.test.tmobiletest.model.GetBooksResponse;
import com.tmobile.test.tmobiletest.repository.BookRepository;
import org.springframework.data.domain.Sort;

@Component
public class LibraryManager {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryManager.class);
	
	private BookRepository bookRepository;
	
	
	@Autowired
	public LibraryManager(  BookRepository bookRepository)
	{
		this.bookRepository = bookRepository;
	}
	
	
	  public CreateBookResponse createBook( CreateBookRequest createBook)
	  {
		  
		  CreateBookResponse bookResponse = null;
		  
		  try {
			  Book book = LibraryMapping.requestToBook(createBook);
			  
			  book = bookRepository.save(book);
			  
			  bookResponse = LibraryMapping.bookResponseMap(book);
			  
			} catch (Exception e) 
		  	{
				LOGGER.error("Error {}" , e );
			}
		  
		  return bookResponse;
	  }
	  
	  
	  
	  public GetBooksResponse getAllBooks()
	  {
		  GetBooksResponse booksResponse = null;
		  
		  
		  List<Book> books =  bookRepository.findAll(Sort.by(Sort.Direction.DESC, "title"));
		  booksResponse = LibraryMapping.mapResponse(books);
		  
		  return booksResponse;
	  }
	  
	  
	  public void removeAll()
	  {
		  bookRepository.deleteAll();
	  }
	
}
