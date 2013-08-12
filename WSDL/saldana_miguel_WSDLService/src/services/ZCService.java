package services;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

/**
 * Servlet implementation class ZCService
 */
@WebServlet("/services/*")
public class ZCService extends CXFNonSpringServlet {

	@Override
    protected void loadBus(ServletConfig sc)
    {
    	super.loadBus(sc);
    	
    	Bus bus = getBus();
    	BusFactory.setDefaultBus(bus);
    	Endpoint.publish("/ZipCodeService", new ZipCodeServiceImp());
    }
}
