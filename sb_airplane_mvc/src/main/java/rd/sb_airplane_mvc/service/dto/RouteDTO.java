package rd.sb_airplane_mvc.service.dto;

import java.util.ArrayList;
import java.util.List;

import rd.sb_airplane_mvc.model.Flight;


public class RouteDTO {
	
	private final String captainName;
	private List<Flight> route;
	private RouteTypes routeType;
	
	
	public RouteDTO(String captainName, Flight initialFlight) {
		
		this.captainName = captainName;
		this.route = new ArrayList<>();
		this.route.add(initialFlight);
		this.routeType = RouteTypes.NOT_CONNECTED;
	}


	
	public String getCaptainName() {
		return captainName;
	}
		
	public List<Flight> getRoute() {
		return route;
	}	
	
	public void setRoute(List<Flight> route) {
		this.route = route;
	}

	public RouteTypes getRouteType() {
		return routeType;
	}

	public void setRouteType(RouteTypes routeType) {
		this.routeType = routeType;
	}	


	public int getRouteListSize() {
		return (this.route.size());
	}




	
	
}
