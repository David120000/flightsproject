package rd.sb_airplane_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rd.sb_airplane_mvc.service.AppService;


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
	
}
