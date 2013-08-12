package rest.clients;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;

import rest.models.MenuItem;
import rest.models.Restaurant;

public class Client {

	public Client()
	{
		
	}
	
	public static void main(String[]args) throws MalformedURLException, IOException
	{
		try {
			HttpURLConnection connection = (HttpURLConnection) (new URL("http://localhost:8080/TakingARest/RestaurantOptions")).openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");

			JAXBContext jaxbContext = JAXBContext.newInstance("rest.models");
			Restaurant res = new Restaurant();	
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			res = (Restaurant) unmarshaller.unmarshal(new InputStreamReader(connection.getInputStream()));
			System.out.println("You're ordering from a " + res.getName() + ".");
			
			System.out.println("These are your options:");
			int orderitem = 0;
			for(MenuItem mi : res.getMenu().getMenuItems())
			{
				System.out.println("\t"+orderitem+") "+mi.getName()+" for " +mi.getPrice());
				orderitem++;
			}
			Scanner scan = new Scanner(System.in);
			System.out.println("What would you like to order?(Type in number)");
			int choice = Integer.parseInt(scan.nextLine());
			
			System.out.println("Enjoy your "+res.getMenu().getMenuItems().get(choice).getName()+" :)");
			
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void end()
	{
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("edu.neumont.jaxb");
			Marshaller marshaller = jaxbContext.createMarshaller();
//			Library library = new Library();
//			Book book1 = new Book();
//			Author author1 = new Author();
//			author1.setName("Orson Scott Card");
//			author1.setPhone(12345);
//			book1.setAuthor(author1);
//			book1.setTitle("Ender's Game");
//
//			Book book2 = new Book();
//			Author author2 = new Author();
//			author2.setName("Mark Twain");
//			author2.setPhone(6789);
//			book2.setAuthor(author2);
//			book2.setTitle("Huck Finn");
//
//			library.getBooks().add(book1);
//			library.getBooks().add(book2);

			File xml = new File("restaurant.xml");
//			marshaller.marshal(library, xml);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//			library = (Library) unmarshaller.unmarshal(xml);
//			System.out.println(library.getBooks().get(0).getTitle());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
