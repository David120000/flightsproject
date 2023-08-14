package rd.sb_airplane_mvc.service.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import rd.sb_airplane_mvc.model.Flight;

public class FlightRoutePlanner {
	
	
	public ArrayList<LinkedList<Flight>> getAllFlightRoutes(List<Flight> allFlights) {
		
		ArrayList<LinkedList<Flight>> flightRoutesAL = new ArrayList<>();
		
		for(Flight flight : allFlights) {
			
			LinkedList<Flight> newRoute = new LinkedList<>();
			newRoute.add(flight);
			flightRoutesAL.add(newRoute);
		}
		
		
		
		for(int arrayIndex = 0; arrayIndex < flightRoutesAL.size(); arrayIndex++) {
			
			LinkedList<Flight> flightRoute = flightRoutesAL.get(arrayIndex);
//			System.err.println("ArrayList current size: " + flightRoutesAL.size());
			
			String flightArrivCity = flightRoute.getLast().getArrivalCity();
			LocalDateTime flightArrivTime = flightRoute.getLast().getArrivalTime();
			
			
			for(int flightSearchIndex = 0; flightSearchIndex < allFlights.size(); flightSearchIndex++) {
				
				Flight flight = allFlights.get(flightSearchIndex);
				
				if(flight.getDepartureCity().equals(flightArrivCity) && flight.getDepartureTime().isAfter(flightArrivTime) ) {

					LinkedList<Flight> newLinkedList = new LinkedList<>();
					newLinkedList.addAll(flightRoute);
					newLinkedList.addLast(flight);
					
//					System.err.println(newLinkedList);
					
					flightRoutesAL.add(newLinkedList);
				}
				
			}
		}
		
		
		return flightRoutesAL;
	}
	

}
