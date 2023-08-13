package rd.sb_airplane_mvc.service.converter;

import java.util.ArrayList;
import java.util.List;

import rd.sb_airplane_mvc.model.Flight;
import rd.sb_airplane_mvc.service.dto.CaptainDTO;

public class CaptainDTOConverter {

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

}
