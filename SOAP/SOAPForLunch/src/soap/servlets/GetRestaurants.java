package soap.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import restaurants_response.*;

/**
 * Servlet implementation class GetRestaurants
 */
@WebServlet("/restaurants")
public class GetRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			restaurants_response.GetAllRestaurantsResponse res = new restaurants_response.GetAllRestaurantsResponse();
			res.getRestaurant();
			restaurants_response.Restaurant pizza = new restaurants_response.Restaurant();
			pizza.setName("Pizza Restaurant");
			restaurants_response.Restaurant burger = new restaurants_response.Restaurant();
			burger.setName("Burger Restaurant");
			res.addRestaurant(pizza);
			res.addRestaurant(burger);
			
			restaurants_response.Body bod = new restaurants_response.Body();
			bod.setGetAllRestaurantsResponse(res);
			restaurants_response.Envelope respEnv = new restaurants_response.Envelope();
			respEnv.setBody(bod);
			respEnv.setEncodingStyle("application/soap+xml");
			
			JAXBContext respjc = JAXBContext.newInstance("restaurants_response");
			Marshaller marshaller = respjc.createMarshaller();
			marshaller.marshal(respEnv, response.getOutputStream());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
