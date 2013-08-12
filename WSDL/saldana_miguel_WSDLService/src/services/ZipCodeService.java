package services;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.WebMethod;

@WebService(name = "ZipCodeService", targetNamespace="http://localhost/ZipCodeService")
public interface ZipCodeService {

	@WebMethod(operationName = "getState")
	public @WebResult(name="State") State getState(@WebParam(name="zipCode")int zipCode);
	
	@WebMethod(operationName = "addZipCode")
	public void AddZipCode(@WebParam(name="state")State state, @WebParam(name="zipCodes")List<Integer> zipCodes);
	
}
