package org.echocat.kata.java.part1;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

	//@GetMapping("/books")
	@RequestMapping(path = "/books/{isbn}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
		//BookData books=new BookData();
		  //books.loadData();
		    System.out.println("# Books loaded="+MainApp.books.tuples.size());
		    Book res=MainApp.books.searchIsbn(isbn);
		    if(res==null) {
		  	  //return("There is no book with the entered isbn: "+isbn);	  
		    	return ResponseEntity.notFound().build();  
		    }else {
		    	 return ResponseEntity.ok(res);
		    }
		//return ("Besm ALLAH, Library, Books");
	}
	

}
