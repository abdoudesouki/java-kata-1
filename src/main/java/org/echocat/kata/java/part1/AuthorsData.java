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
public class AuthorsData extends TableData{
	//public static List<Author> tuples;
	
    public static void main(String... args) {

    	AuthorsData authors=new AuthorsData();
    	authors.loadData();
        // let's print all the person read from CSV file
        for (DbRecord r : authors.tuples) {
        	if (r instanceof Author) {
        		Author b = (Author) r;
        	    System.out.println(b);
        	}
            
        }
    }
   
    public void loadData() {
    	
    	try {
			readFromCSV("data/authors.csv",Author.class,2);//2 skip header line
		} catch (IOException e) {
			e.printStackTrace();
			
		}
    }
    
    


    public Author searchEmailaddr(String emailaddr) {
    	//Author res=null;
    	for(DbRecord x: tuples) {
    		if(x instanceof Author) {
    			Author A=(Author) x;
    			if(A.getEmailaddr().equals(emailaddr)) 		return(A);
    		}
    	}
    	return(null);
    }
}


class Author extends DbRecord{
    private String firstname;
    private String lastname;
    private String emailaddr;

    public Author( String emailaddr,String firstname,String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddr = emailaddr;
        //this.authors = authors.split(",");
    }
    public Author() {
    	this.emailaddr ="undifined Author";
    }
     @Override
      public DbRecord createRecord(String[] metadata) {
    	if(metadata.length<3) {
    		System.out.println("invalid record, will be skipped");
    		return null;
    	}
              // create and return book of this metadata
    	this.firstname = metadata[1];
        this.emailaddr = metadata[0];
        this.lastname = metadata[2];
        return this;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmailaddr() {
        return emailaddr;
    }

    public void setEmailaddr(String emailaddr) {
        this.emailaddr = emailaddr;
    }

    
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Author [firstname=" + firstname + ", emailaddr=" + emailaddr  + " lastname="+lastname+ "]";
    }

}	