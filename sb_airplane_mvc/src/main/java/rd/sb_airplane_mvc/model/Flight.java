package rd.sb_airplane_mvc.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name="flights")
public class Flight {

	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="departure_city")
	private String departureCity;
	
	@Column(name="departure_time")
	private LocalDateTime departureTime;
	
	@Column(name="arrival_city")
	private String arrivalCity;
	
	@Column(name="arrival_time")
	private LocalDateTime arrivalTime;
	
	@Column(name="flight_id")
	private String flightId;
	
	@Column(name="captain")
	private String captain;
	
	@Transient
	private long flightTime;

	
	
	public Flight() {
		this.flightTime = -1L;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getCaptain() {
		return captain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
	}
	
	public long getFlightTime() {
		return flightTime;
	}


	public void calculateFlightTime() {
		this.flightTime = this.departureTime.until(this.arrivalTime, ChronoUnit.MINUTES);
	}

	
	@Override
	public String toString() {
		return "Flight [id=" + id + ", departureCity=" + departureCity + ", departureTime=" + departureTime
				+ ", arrivalCity=" + arrivalCity + ", arrivalTime=" + arrivalTime + ", flightId=" + flightId
				+ ", captain=" + captain + ", flightTime=" + flightTime + "]";
	}
	


}
