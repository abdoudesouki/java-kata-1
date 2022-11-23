package org.echocat.kata.java.part1;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainAppUnitTest {

//    @Test
//    public void testGetHelloWorldText() {
//        assertThat(MainApp.getHelloWorldText(), is("Hello world!"));
//    }
	
	public static String getBookData(String isbn) throws IOException{

		HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/books/" + isbn).openConnection();
		
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if(responseCode == 200){
			String response = "";
			Scanner scanner = new Scanner(connection.getInputStream());
			while(scanner.hasNextLine()){
				response += scanner.nextLine();
				response += "\n";
			}
			scanner.close();

			return response;
		}
		
		// an error happened
		return null;
	}
	
	@Test
	public void testBookApi() {
		try {
			System.out.println(getBookData("123"));
			assertThat(getBookData("123"),is(null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
