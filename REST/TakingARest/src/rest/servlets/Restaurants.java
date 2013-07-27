package rest.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;

import rest.models.*;

/**
 * Servlet implementation class DisplayMenu
 */
@WebServlet("/RestaurantOptions")
public class Restaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Restaurants() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance("rest.models");

			Marshaller marshaller = jaxbContext.createMarshaller();

			Restaurant res = new Restaurant();
			res.setName("Burger Restaurant");
			MenuItem mi = new MenuItem();
			mi.setName("Cheese Burger");
			mi.setPrice(2.00);
			res.getMenu().getMenuItems().add(mi);
			mi = new MenuItem();
			mi.setName("Chicken Burger");
			mi.setPrice(1.00);
			res.getMenu().getMenuItems().add(mi);
			mi = new MenuItem();
			mi.setName("BLT");
			mi.setPrice(3.00);
			res.getMenu().getMenuItems().add(mi);
			
			marshaller.marshal(res, resp.getWriter());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
