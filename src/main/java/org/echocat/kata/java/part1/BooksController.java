package org.echocat.kata.java.part1;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BooksController {

	//@GetMapping("/books")
	@RequestMapping(path = "/books/{isbn}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
		    
		    Book res=MainApp.books.searchIsbn(isbn);
		    if(res==null) {
		  	  //return("There is no book with the entered isbn: "+isbn);	
		    	System.out.println("REST call: isbn not found:"+isbn);
		    	return ResponseEntity.notFound().build();  
		    }else {
		    	System.out.println("REST call: isbn found:isbn="+isbn+" "+res);
		    	 return ResponseEntity.ok(res);
		    }
		//return ("Besm ALLAH, Library, Books");
	}
	

}
