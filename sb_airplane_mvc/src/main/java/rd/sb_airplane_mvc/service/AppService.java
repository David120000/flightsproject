package rd.sb_airplane_mvc.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rd.sb_airplane_mvc.dao.Database;
import rd.sb_airplane_mvc.model.Flight;
import rd.sb_airplane_mvc.service.converter.CaptainDTOConverter;
import rd.sb_airplane_mvc.service.converter.FlightRoutePlanner;
import rd.sb_airplane_mvc.service.dto.CaptainDTO;
import rd.sb_airplane_mvc.service.dto.PlannedRouteDTO;
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
	
	
	public ArrayList<LinkedList<Flight>> getAllPossibleRoutes() {
		
		List<Flight> allFlights = this.getAllFlights();
		
		FlightRoutePlanner routePlanner = new FlightRoutePlanner();
		ArrayList<LinkedList<Flight>> allPossibleRoutes = routePlanner.getAllFlightRoutes(allFlights);
		
		
		return allPossibleRoutes;
	}


	public List<String> getDepartureCities() {

		return db.getDepartureCities();
	}


	public List<String> getArrivalCities() {
		
		return db.getArrivalCities();
	}


	public ArrayList<PlannedRouteDTO> calculateRoute(String fromCity, String toCity) {
		
		ArrayList<PlannedRouteDTO> resultList = new ArrayList<>();
		
		
		ArrayList<LinkedList<Flight>> allPossibleRoutes = this.getAllPossibleRoutes();
		
		for(LinkedList<Flight> possibleRoute : allPossibleRoutes) {
			
			String startingLocation = possibleRoute.getFirst().getDepartureCity();
			String finalLocation = possibleRoute.getLast().getArrivalCity();
			
			if(fromCity.equals(startingLocation) && toCity.equals(finalLocation)) {
				
				long travelTimeMinutes = possibleRoute.getFirst().getDepartureTime().until(possibleRoute.getLast().getArrivalTime(), ChronoUnit.MINUTES);
				
				PlannedRouteDTO recommendedRoute = new PlannedRouteDTO(possibleRoute, travelTimeMinutes);
				resultList.add(recommendedRoute);
			}
		}
		
		
		return resultList;
	}

}
