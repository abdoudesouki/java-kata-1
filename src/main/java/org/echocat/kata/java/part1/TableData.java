package org.echocat.kata.java.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//parent of all data tables
public abstract class TableData {
	public  List<DbRecord> tuples=new ArrayList<DbRecord>();

	public void readFromCSV(String fileName,Class<? extends DbRecord> rectype,Integer... frstRow) throws IOException {
		//frstRow parameter indicating first line of file to be used, start counting of 1,
		// to skip header send 2
        InputStream inputStream = BookData.class.getResourceAsStream(fileName);//must have same str
     //   ClassPathResource resource = new ClassPathResource(fileName);
    //    InputStream inputStream = resource.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        //BufferedReader reader = new BufferedReader(streamReader);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        int lineno=1;
        int skiplines=frstRow.length>0?(int)frstRow[0]:0;//to be able to skip header line
        try (BufferedReader br = new BufferedReader(streamReader)) {

            // read the first line from the text file
            String line = br.readLine();
            while (line != null && lineno < skiplines) {
	       		 lineno++;
	       		 line = br.readLine();       		 
       	     }
            
            // loop until all lines are read
            while (line != null) {            	 
            		 lineno++;
                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(";");

               // @SuppressWarnings("deprecation")
                DbRecord rcrd = rectype.getDeclaredConstructor().newInstance();
                rcrd.createRecord(attributes);

                // adding book into ArrayList
                if(rcrd!=null) tuples.add(rcrd);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
        	System.out.println("Unable to load from file:"+fileName);
            ioe.printStackTrace();
        } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //return tuples;
    }
	
	//protected static <Re> createRecord(String[] metadata) {
    	
  //  }
}

abstract class  DbRecord{
	public abstract DbRecord createRecord(String[] metadata);
}
