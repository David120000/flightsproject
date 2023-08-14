package rd.sb_airplane_mvc.service.dto;

import java.util.LinkedList;

import rd.sb_airplane_mvc.model.Flight;


public class PlannedRouteDTO {

	private final LinkedList<Flight> route;
	private final long travelTime;
	
	
	public PlannedRouteDTO(LinkedList<Flight> route, long travelTime) {
		super();
		this.route = route;
		this.travelTime = travelTime;
	}


	
	public LinkedList<Flight> getRoute() {
		return route;
	}
	
	public long getTravelTime() {
		return travelTime;
	}


	@Override
	public String toString() {
		return "PlannedRouteDTO [route=" + route + ", travelTime=" + travelTime + "]";
	}
	

}
