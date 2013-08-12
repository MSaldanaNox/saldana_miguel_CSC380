package soap.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Servlet implementation class GetMenu
 */
@WebServlet("/menu")
public class GetMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			menu_response.MenuItem burger = new menu_response.MenuItem();
			burger.setName("Burger");
			menu_response.GetMenuItemsResponse menu = new menu_response.GetMenuItemsResponse();
			menu.setMenuItem(burger);
			
			menu_response.Body bod = new menu_response.Body();
			bod.setGetMenuItemsResponse(menu);
			menu_response.Envelope respEnv = new menu_response.Envelope();
			respEnv.setBody(bod);
			respEnv.setEncodingStyle("application/soap+xml");
			
			JAXBContext respjc = JAXBContext.newInstance("menu_response");
			Marshaller marshaller = respjc.createMarshaller();
			marshaller.marshal(respEnv, response.getOutputStream());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
