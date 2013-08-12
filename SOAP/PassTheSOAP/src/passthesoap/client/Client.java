package passthesoap.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import menu_request.GetMenuItems;

import restaurants_request.GetAllRestaurants;
import restaurants_response.Restaurant;

public class Client {
	
	private static String restarauntC = "http://localhost:8000/GetRestaurant";
	private static String menuC = "http://localhost:8000/Menu";
	private static String orderC = "http://localhost:8000/CreateOrder";
	
	public static void main(String[]args)
	{
		try {
			getRestaurants();
			getMenus();
			createOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void createOrder() throws Exception {
		// TODO Auto-generated method stub
		HttpURLConnection connection = establishConnection(restarauntC);
		JAXBContext reqContext = JAXBContext.newInstance("orderRequest");
		Marshaller marshaller = reqContext.createMarshaller();
		marshaller.marshal(new order_request.MenuItem(), connection.getOutputStream());
		connection.getOutputStream().flush();
		connection.getOutputStream().close();
		
		System.out.println(connection.getResponseCode());
	}


	private static void getMenus() throws Exception {
		// TODO Auto-generated method stub
		HttpURLConnection connection = establishConnection(restarauntC);
		JAXBContext reqContext = JAXBContext.newInstance("soapRequest");
		Marshaller marshaller = reqContext.createMarshaller();
		marshaller.marshal(new GetAllRestaurants(), connection.getOutputStream());
		connection.getOutputStream().flush();
		connection.getOutputStream().close();
		System.out.println(connection.getResponseCode());
		
		JAXBContext resCon = JAXBContext.newInstance("restaurants_response");
		restaurants_response.Envelope envelope = (restaurants_response.Envelope) resCon.createUnmarshaller().unmarshal(connection.getInputStream());
		restaurants_response.Body body = envelope.getBody();
		List<Restaurant> restaurant = (List<Restaurant>) body.getGetAllRestaurantsResponse().getRestaurant();
		
		System.out.println("Restaurant is " + restaurant.get(0).getName());
		connection.disconnect();
	}


	public static void getRestaurants() throws Exception
	{
		HttpURLConnection connection = establishConnection(menuC);
		JAXBContext reqContext = JAXBContext.newInstance("menuRequest");
		Marshaller marshaller = reqContext.createMarshaller();
		marshaller.marshal(new GetMenuItems(), connection.getOutputStream());
		connection.getOutputStream().flush();
		connection.getOutputStream().close();
		System.out.println(connection.getResponseCode());
		
		JAXBContext resCon = JAXBContext.newInstance("menuRequest");
		menu_response.Envelope envelope = (menu_response.Envelope) resCon.createUnmarshaller().unmarshal(connection.getInputStream());
		menu_response.Body body = envelope.getBody();
		String menuItem = body.getGetMenuItemsResponse().getMenuItem().getName();
		
		System.out.println("Menu items is " + menuItem);
		connection.disconnect();
	}
	
	public static HttpURLConnection establishConnection(String url) throws Exception
	{
		HttpURLConnection c = (HttpURLConnection)(new URL(url)).openConnection();
		c.setDoInput(true);
		c.setDoOutput(true);
		c.setRequestMethod("POST");
		c.setRequestProperty("Conneciton", "Keep-Alive");
		c.setRequestProperty("Content-Type", "text/xml");
		return c;
	}
}
