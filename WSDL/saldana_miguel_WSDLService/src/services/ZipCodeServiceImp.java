package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;


@WebService(endpointInterface = "services.ZipCodeService", serviceName="ZipCodeService")
public class ZipCodeServiceImp implements ZipCodeService{

	private static Map<Integer, State> STATES;
	
	public ZipCodeServiceImp()
	{
		STATES = new HashMap<>();
		STATES.put(84095, new State("Utah", "UT"));
	}
	
	@Override
	public State getState(int zipCode) {
		// TODO Auto-generated method stub
		return STATES.get(zipCode);
	}

	@Override
	public void AddZipCode(State state, List<Integer> zipCodes) {
		// TODO Auto-generated method stub
		for(Integer zipCode : zipCodes)
		{
			STATES.put(zipCode, state);
		}
	}
	
}
