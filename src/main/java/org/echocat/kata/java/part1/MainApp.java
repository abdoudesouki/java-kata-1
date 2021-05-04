package org.echocat.kata.java.part1;

import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);

  BookData books=new BookData();
  books.loadData();
    System.out.println("# Books loaded="+books.tuples.size());
    MagazineData magazines=new MagazineData();
    magazines.loadData();
      System.out.println("# magazines loaded="+magazines.tuples.size());
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
