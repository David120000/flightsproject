package rd.sb_airplane_mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rd.sb_airplane_mvc.dao.Database;
import rd.sb_airplane_mvc.model.Flight;
import rd.sb_airplane_mvc.service.converter.CaptainDTOConverter;
import rd.sb_airplane_mvc.service.dto.CaptainDTO;
import rd.sb_airplane_mvc.service.dto.RouteDTO;


@Service
public class AppService {

	private final Database db;
	private final CaptainDTOConverter converter;
	
	
	@Autowired
	public AppService(Database db, CaptainDTOConverter converter) {
		this.db = db;
		this.converter = converter;
	}
	
	
	
	public List<Flight> getAllFlights() {		

		return db.getAllFlights();
	}


	public List<Flight> getAllFlightsWithFlightTimes() {
		
		List<Flight> flights = db.getAllFlights();
		
		for(Flight flight : flights) {
			
			flight.calculateFlightTime();
		}
		
		
		return flights;
	}


	public List<CaptainDTO> getFlightTimesByCaptains() {
		
		List<Flight> flightData = this.getAllFlightsWithFlightTimes();
		
		List<CaptainDTO> captainData = converter.createCaptainDTOs(flightData);
		
		
		return captainData;
	}

	
	public List<RouteDTO> getRoutesByCaptains(int minimumDestinationCount) {
	
		List<Flight> flightData = this.getAllFlights();
		
		List<RouteDTO> routes = converter.createRouteDataByCaptains(flightData);
		
		if(minimumDestinationCount > 0) {
			this.clearRoutesBelowTheRequirement(routes, minimumDestinationCount);
		}
		
		return routes;
	}
	
	
	private void clearRoutesBelowTheRequirement(List<RouteDTO> routes, int minimumDestinationCount) {
		
		for(int i = 0; i < routes.size(); i++) {
			
			RouteDTO route = routes.get(i);
			
			if(route.getRouteListSize() < minimumDestinationCount) {
				
				routes.remove(i);
				i--;
			}
		}
	}

}
