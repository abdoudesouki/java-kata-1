package org.echocat.kata.java.part1;

import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
//import org.springframework.core.io.ClassPathResource;
//import java.io.ClassLoaderUtil;

/**
 * Simple Java program to read CSV file in Java. In this program we will read
 * list of tuples stored in CSV file as comma separated values.
 * 
 * @author WINDOWS 8
 *
 */
public class BookData extends TableData{
	//public static List<Book> tuples;
	
    public static void main(String... args) {
    	BookData books=new BookData();
    	books.loadData();
        // let's print all the person read from CSV file
        for (DbRecord r : books.tuples) {
        	if (r instanceof Book) {
        	    Book b = (Book) r;
        	    System.out.println(b);
        	}
            
        }
    }
   
    public void loadData() {
    	
    	try {
			readFromCSV("data/books.csv",Book.class,2);//2 skip header line
		} catch (IOException e) {
			e.printStackTrace();
			
		}

    }
    
    /*private static List<DbRecord> readBooksFromCSV(String fileName,Class<? extends DbRecord> rectype) throws IOException {
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

                DbRecord book = rectype.createRecord(attributes);

                // adding book into ArrayList
                if(book!=null) tuples.add(book);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
        	System.out.println("Unable to load from file:"+fileName);
            ioe.printStackTrace();
        }

        return tuples;
    }*/

    //@Override
    

    public Book searchIsbn(String isbn) {
    	//Book res=null;
    	for (DbRecord r : tuples) {
        	if (r instanceof Book) {
        		Book b = (Book) r;
    		if( b.getIsbn().equals(isbn)) {
    			return(b);
    		}
        	}
    	}
    	return(null);
    }
}


class Book extends DbRecord{
    private String title;
    private String description;
    private String[] authors;
    private String isbn;

    public Book(String title, String isbn, String authors,String descrition) {
        this.title = title;
        this.description = descrition;
        this.isbn = isbn;
        this.authors = authors.split(",");
    }
    
    public Book() {
    	this.title ="undifined book";
    }
     @Override
      public DbRecord createRecord(String[] metadata) {
    	if(metadata.length<4) {
    		System.out.println("invalid record, will be skipped");
    		return null;
    	}
              // create and return book of this metadata
        //return new Book(metadata[0], metadata[1], metadata[2],metadata[3]);
    	this.title = metadata[0];
        this.isbn = metadata[1];
        this.authors = metadata[2].split(",");
        this.description = metadata[3];
        return this;
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
        return "Book [title=" + title + ", isbn=" + isbn + ", author=" + String.join(",", authors)
                +" decription="+description+ "]";
    }

}	
