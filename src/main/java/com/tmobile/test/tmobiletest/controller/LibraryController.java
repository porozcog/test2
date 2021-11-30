package com.tmobile.test.tmobiletest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tmobile.test.tmobiletest.manager.LibraryManager;
import com.tmobile.test.tmobiletest.model.CreateBookRequest;
import com.tmobile.test.tmobiletest.model.CreateBookResponse;
import com.tmobile.test.tmobiletest.model.GetBooksResponse;

@RestController
public class LibraryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);
	
	private LibraryManager libraryManager;
	
	@Autowired
	public LibraryController(LibraryManager libraryManager)
	{
		this.libraryManager = libraryManager;
	}
	
    @GetMapping("/health")
    public void health() {
    	
    }
    
    @PutMapping( path = "/api/books", consumes = "application/json" , produces = "application/json" )
    public ResponseEntity<CreateBookResponse> createBook( @RequestBody CreateBookRequest createBook)
    {
    	if(LOGGER.isInfoEnabled())
			LOGGER.info("Request {}", createBook);
    	
    	return new ResponseEntity<>(libraryManager.createBook(createBook), HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/api/books" ,produces = "application/json" )
    public ResponseEntity<GetBooksResponse> getAllBooks()
    {
    	if(LOGGER.isInfoEnabled())
			LOGGER.info("getAllBooks execute");
    	
    	return new ResponseEntity<>(libraryManager.getAllBooks(), HttpStatus.OK);
    }
    
    
    @DeleteMapping(path = "/api/books" )
    public ResponseEntity<Void> deleteAll()
    {
    	if(LOGGER.isInfoEnabled())
			LOGGER.info("deleteAll execute");
    	
    	libraryManager.removeAll();
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
