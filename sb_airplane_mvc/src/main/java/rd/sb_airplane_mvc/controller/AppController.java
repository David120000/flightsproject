package rd.sb_airplane_mvc.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rd.sb_airplane_mvc.service.AppService;
import rd.sb_airplane_mvc.service.dto.PlannedRouteDTO;


@Controller
public class AppController {

	
	private final AppService service;
	
	@Autowired
	public AppController(AppService service) {
		this.service = service;
	}
	
	
	@GetMapping("/flight/all")
	public String showAllFlights(Model model) {
		
		model.addAttribute("flights", service.getAllFlights());

		return "flights.html";
	}
	
	
	@GetMapping("/flight/all/withtimes")
	public String showAllFlightsWithFlightTimes(Model model) {
		
		model.addAttribute("flights", service.getAllFlightsWithFlightTimes());
		model.addAttribute("flightTimePresent", true);
		
		return "flights.html";
	}
	
	
	@GetMapping("/captain/flighttimes")
	public String showCaptainFlightTimes(Model model) {
		
		model.addAttribute("captains", service.getFlightTimesByCaptains());
		
		return "captains.html";
	}
	
	
	@GetMapping("/captain/routes")
	public String showRoutesByCaptains(Model model) {
		
		model.addAttribute("routesbycaptains", service.getRoutesByCaptains(0));
		
		return "captainroutes.html";
	}
	
	@PostMapping("/captain/routes")
	public String showRoutesByCaptains(
			Model model,
			@RequestParam(name="minDestCount") int minimumDestinationCount) {
		
		
		model.addAttribute("routesbycaptains", service.getRoutesByCaptains(minimumDestinationCount));
		model.addAttribute("previousRequirement", minimumDestinationCount);
		
		
		return "captainroutes.html";
		
	}
	
	
	@GetMapping("/route/all")
	public String showAllRoutes(Model model) {
		
		model.addAttribute("routes", service.getAllPossibleRoutes());
		
		return "allroutes.html";
	}
	
	
	@GetMapping("/route/plan")
	public String showRoutePlanner(Model model) {
		
		model.addAttribute("depcities", service.getDepartureCities());
		model.addAttribute("arrivcities", service.getArrivalCities());
		
		return "planner.html";
	}
	
	@PostMapping("/route/plan")
	public String calculateRoute(
			Model model,
			@RequestParam(name="from") String fromCity,
			@RequestParam(name="to") String toCity) {
		
		
		ArrayList<PlannedRouteDTO> recommendedRoutes = service.calculateRoute(fromCity, toCity);
		model.addAttribute("routes", recommendedRoutes);
		
		model.addAttribute("depcities", service.getDepartureCities());
		model.addAttribute("arrivcities", service.getArrivalCities());
		model.addAttribute("fromwhere", fromCity);
		model.addAttribute("towhere", toCity);
		
		
		return "planner.html";
	}
	
}
