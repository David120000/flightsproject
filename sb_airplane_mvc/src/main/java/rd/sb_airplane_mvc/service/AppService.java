package rd.sb_airplane_mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rd.sb_airplane_mvc.dao.Database;
import rd.sb_airplane_mvc.model.Flight;


@Service
public class AppService {

	private final Database db;
	
	
	@Autowired
	public AppService(Database db) {
		this.db = db;
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

}
