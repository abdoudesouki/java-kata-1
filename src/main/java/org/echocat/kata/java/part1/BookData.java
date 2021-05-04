package org.echocat.kata.java.part1;

// By Adesouki
// code edited from: https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.core.io.ClassPathResource;
//import java.io.ClassLoaderUtil;

/**
 * Simple Java program to read CSV file in Java. In this program we will read
 * list of tuples stored in CSV file as comma separated values.
 * 
 * @author WINDOWS 8
 *
 */
public class BookData {
	public static List<Book> tuples;
	
    public static void main(String... args) {

    	loadData();
        // let's print all the person read from CSV file
        for (Book b : tuples) {
            System.out.println(b);
        }
    }
   
    public static void loadData() {
    	
    	try {
			tuples = readBooksFromCSV("data/books.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

    }
    
    private static List<Book> readBooksFromCSV(String fileName) throws IOException {
        List<Book> tuples = new ArrayList<>();
        InputStream inputStream = BookData.class.getResourceAsStream(fileName);//must have same str
     //   ClassPathResource resource = new ClassPathResource(fileName);
    //    InputStream inputStream = resource.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        //BufferedReader reader = new BufferedReader(streamReader);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = new BufferedReader(streamReader)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(";");

                Book book = createBook(attributes);

                // adding book into ArrayList
                if(book!=null) tuples.add(book);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
        	System.out.println("Unable to load books from file.");
            ioe.printStackTrace();
        }

        return tuples;
    }

    private static Book createBook(String[] metadata) {
    	if(metadata.length<4) {
    		System.out.println("invalid record, will be skipped");
    		return null;
    	}
              // create and return book of this metadata
        return new Book(metadata[0], metadata[1], metadata[2],metadata[3]);
    }

    public Book searchIsbn(String isbn) {
    	//Book res=null;
    	for(Book x: tuples) {
    		if(x.getIsbn().equals(isbn)) {
    			return(x);
    		}
    	}
    	return(null);
    }
}


class Book {
    private String title;
    private String description;
    //private int price;
    private String[] authors;
    private String isbn;

    public Book(String title, String isbn, String authors,String descrition) {
        this.title = title;
        this.description = descrition;
        this.isbn = isbn;
        this.authors = authors.split(",");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return String.join(",",authors);
    }

    public void setAuthor(String[] authors) {
        this.authors = authors;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", isbn=" + isbn + ", author=" + authors
                +" decription="+description+ "]";
    }

}	