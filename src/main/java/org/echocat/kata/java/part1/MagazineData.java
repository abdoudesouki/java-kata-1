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
//import java.io.ClassLoaderUtil;

/**
 * Simple Java program to read CSV file in Java. In this program we will read
 * list of tuples stored in CSV file as comma separated values.
 * 
 * @author WINDOWS 8
 *
 */
public class MagazineData extends TableData{
	//public static List<Magazine> tuples;
	
    public static void main(String... args) {
    	
    	MagazineData magazines=new MagazineData();
    	magazines.loadData();
        // let's print all the person read from CSV file
        for (DbRecord r : magazines.tuples) {
        	if (r instanceof Magazine) {
        		Magazine b = (Magazine) r;
        	    System.out.println(b);
        	}
            
        }
    }
   
    public  void loadData() {
    	//tuples = readBooksFromCSV("data/magazines.csv");
    	try {
			readFromCSV("data/magazines.csv",Magazine.class,2);//2 skip header line
		} catch (IOException e) {
			e.printStackTrace();
			
		}

    }
    
        

    public Magazine searchIsbn(String isbn) {
    	for (DbRecord r : tuples) {
        	if (r instanceof Magazine) {
        		Magazine b = (Magazine) r;
    		if( b.getIsbn().equals(isbn)) {
    			return(b);
    		}
        	}
    	}
    	return(null);
    }
}


class Magazine extends DbRecord{
    private String title;
    private String publishedat;
    //private int price;
    private String[] authors;
    private String isbn;

    public Magazine(String title, String isbn, String authors,String publishedat) {
        this.title = title;
        this.publishedat = publishedat;
        this.isbn = isbn;
        this.authors = authors.split(",");
    }
    public Magazine() {
    	this.title ="undifined Magazine";
    }
     @Override
      public DbRecord createRecord(String[] metadata) {
    	if(metadata.length<4) {
    		System.out.println("invalid record, will be skipped");
    		return null;
    	}
              // create and return book of this metadata
    	this.title = metadata[0];
        this.isbn = metadata[1];
        this.authors = metadata[2].split(",");
        this.publishedat = metadata[3];
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

    /*public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }*/

    @Override
    public String toString() {
        return "Magazine [title=" + title + ", isbn=" + isbn + ", author=" + String.join(",",authors)
                +" publishedat="+publishedat+ "]";
    }

}	