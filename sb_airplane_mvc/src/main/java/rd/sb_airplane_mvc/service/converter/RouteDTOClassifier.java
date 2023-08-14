package rd.sb_airplane_mvc.service.converter;

import java.util.List;

import org.springframework.stereotype.Service;

import rd.sb_airplane_mvc.service.dto.RouteDTO;
import rd.sb_airplane_mvc.service.dto.RouteTypes;


@Service
public class RouteDTOClassifier {

	
	public void setRouteTypes(List<RouteDTO> routeData) {

		for(RouteDTO route : routeData) {
			
			boolean connectedDestinations = true;
			
			for(int i = 0; i < (route.getRouteListSize() - 1); i++ ) {
				
				String arrivalCity = route.getRoute().get(i).getArrivalCity();
				String nextDepratureFrom = route.getRoute().get(i+1).getDepartureCity();
				
				if(arrivalCity.equals(nextDepratureFrom) == false) {
					connectedDestinations = false;
				}
			}
			
			
			String startingCity = route.getRoute().get(0).getDepartureCity();
			String finalCity = route.getRoute().get( route.getRouteListSize()-1 ).getArrivalCity();
			
			if(connectedDestinations == true && startingCity.equals(finalCity)) {
				
				if(route.getRouteListSize() > 2) {
					route.setRouteType(RouteTypes.RETURNED_INDIRECTLY);
				}
				else {
					route.setRouteType(RouteTypes.RETURNED_DIRECTLY);
				}
				
			}
		}
		
	}

}
