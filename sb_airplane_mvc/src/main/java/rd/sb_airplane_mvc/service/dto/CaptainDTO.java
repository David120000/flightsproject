package rd.sb_airplane_mvc.service.dto;


public class CaptainDTO {
	
	private final String captainName;
	private long flightTime;
	
	
	public CaptainDTO(String captainName, long flightTime) {
		super();
		this.captainName = captainName;
		this.flightTime = flightTime;
	}


	public String getCaptainName() {
		return captainName;
	}

	public long getFlightTime() {
		return flightTime;
	}
	
	
	public void addTime(long additionalFlightTime) {
		this.flightTime = this.flightTime + additionalFlightTime;
	}


	@Override
	public String toString() {
		return "CaptainDTO [captainName=" + captainName + ", flightTime=" + flightTime + "]";
	}
	

}
