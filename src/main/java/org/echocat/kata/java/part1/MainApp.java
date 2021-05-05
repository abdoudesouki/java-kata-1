package org.echocat.kata.java.part1;

import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * ToDo 5/5/21 : parent class for table data that includes the readCSV[,tuples,RecordType]
 */

@SpringBootApplication
public class MainApp {
	// Todo: better one class for Database
	public static BookData books=new BookData();
	public static MagazineData magazines=new MagazineData();
	public static AuthorsData authors=new AuthorsData();
	
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);

  
  books.loadData();
    System.out.println("# Books loaded="+books.tuples.size());
    
    magazines.loadData();
      System.out.println("# magazines loaded="+magazines.tuples.size());
      
      authors.loadData();
      System.out.println("# authors loaded="+magazines.tuples.size());

//  ++++++++++
  Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
  System.out.print("Enter isbn to find: ");
  String isbn= sc.nextLine();
  Book res=books.searchIsbn(isbn);
  if(res==null) {
	  System.out.println("There is no book with the entered isbn: "+isbn);	  
  }else {
	  System.out.println("isbn found, Book deails:"+res.toString());
  }
	}

}
