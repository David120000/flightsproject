package rd.sb_airplane_mvc.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rd.sb_airplane_mvc.model.Flight;
import rd.sb_airplane_mvc.service.dto.CaptainDTO;
import rd.sb_airplane_mvc.service.dto.RouteDTO;


@Service
public class CaptainDTOConverter {

	private final RouteDTOClassifier routeClassifier;
	
	
	@Autowired
	public CaptainDTOConverter(RouteDTOClassifier routeClassifier) {
		this.routeClassifier = routeClassifier;
	}
	
	
	
	public List<CaptainDTO> createCaptainDTOs(List<Flight> flightData) {
		
		List<CaptainDTO> captainData = new ArrayList<>();
		
		for(Flight flight : flightData) {
			
			String captain = flight.getCaptain();
			boolean capAlreadyInList = false;
			
			for(CaptainDTO capDTO : captainData) {
				
				if(capDTO.getCaptainName().equals(captain) == true) {
					
					capDTO.addTime(flight.getFlightTime());
					
					capAlreadyInList = true;
					break;
				}
			}
					
			if(capAlreadyInList == false) {
				
				CaptainDTO newCapDTO = new CaptainDTO(captain, flight.getFlightTime());
				captainData.add(newCapDTO);
			}
		}
		
		
		return captainData;
	}

	
	public List<RouteDTO> createRouteDataByCaptains(List<Flight> flightData) {
		
		List<RouteDTO> routeData = new ArrayList<>();
		
		for(Flight flight : flightData) {
			
			String captain = flight.getCaptain();
			boolean capAlreadyInList = false;
			
			for(RouteDTO route : routeData) {
				
				if(route.getCaptainName().equals(captain) == true) {
					
					this.addNewFlightToCaptainsRouteList(route, flight);
					
					capAlreadyInList = true;
					break;
				}
			}
			
			if(capAlreadyInList == false) {
				
				RouteDTO newRoute = new RouteDTO(captain, flight);
				routeData.add(newRoute);
			}
			
		}

		routeClassifier.setRouteTypes(routeData);
		
		
		return routeData;
	}

	
	private void addNewFlightToCaptainsRouteList(RouteDTO route, Flight flightToAdd) {
		
		List<Flight> captainsRoute = route.getRoute();
		
		int setNewFlightToIndex = -1;
		
		for(int i = 0; i < captainsRoute.size(); i++) {
			
			Flight flightFromList = captainsRoute.get(i);
			
			if(flightFromList.getDepartureTime().isAfter(flightToAdd.getArrivalTime()) == true) {
				
				setNewFlightToIndex = i;
			}
			else if(flightFromList.getArrivalTime().isBefore(flightToAdd.getDepartureTime()) == true) {
				
				setNewFlightToIndex = i+1;
			}			
		}
		
		captainsRoute.add(setNewFlightToIndex, flightToAdd);
	}
}
