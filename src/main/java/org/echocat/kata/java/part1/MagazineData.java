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
public class MagazineData {
	public static List<Magazine> tuples;
	
    public static void main(String... args) {
    	//tuples = readBooksFromCSV("data/tuples.csv");

    	loadData();
        // let's print all the person read from CSV file
        for (Magazine b : tuples) {
            System.out.println(b);
        }
    }
   
    public static void loadData() {
    	tuples = readBooksFromCSV("data/magazines.csv");

    }
    
    private static List<Magazine> readBooksFromCSV(String fileName) {
        List<Magazine> tuples = new ArrayList<>();
        //Path pathToFile = Paths.get(fileName);
        InputStream inputStream = MagazineData.class.getResourceAsStream(fileName);
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

                Magazine Magazine = createBook(attributes);

                // adding Magazine into ArrayList
                if(Magazine!=null) tuples.add(Magazine);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return tuples;
    }

    private static Magazine createBook(String[] metadata) {
    	if(metadata.length<4) {
    		System.out.println("invalid record, will be skipped");
    		return null;
    	}
              // create and return Magazine of this metadata
        return new Magazine(metadata[0], metadata[1], metadata[2],metadata[3]);
    }

    public Magazine searchIsbn(String isbn) {
    	//Magazine res=null;
    	for(Magazine x: tuples) {
    		if(x.getIsbn().equals(isbn)) {
    			return(x);
    		}
    	}
    	return(null);
    }
}


class Magazine {
    private String title;
    private String publishedat;
    //private int price;
    private String[] authors;
    private String isbn;

    public Magazine(String title, String isbn, String authors,String descrition) {
        this.title = title;
        this.publishedat = descrition;
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

    /*public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }*/

    @Override
    public String toString() {
        return "Magazine [title=" + title + ", isbn=" + isbn + ", author=" + authors
                +" publishedat="+publishedat+ "]";
    }

}	