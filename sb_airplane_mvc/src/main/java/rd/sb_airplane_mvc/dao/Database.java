package rd.sb_airplane_mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rd.sb_airplane_mvc.model.Flight;


@Repository
public class Database {

	private final HibernateUtility hbUtil;
	
	
	@Autowired
	public Database(HibernateUtility hbUtil) {
		this.hbUtil = hbUtil;
	}
	
	
	
	public List<Flight> getAllFlights() {
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("SELECT f FROM Flight f ORDER BY f.departureTime ASC", Flight.class);
		
		List<Flight> flights = q.getResultList();
		
		tx.commit();
		session.close();
		
		
		return flights;
	}

	
	public List<String> getDepartureCities() {

		List<String> departureCities = new ArrayList<>();
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Query q = session.createNativeQuery("SELECT departure_city FROM flights", Object.class);
		List<Object> rows = q.getResultList();
		
		for(int i = 0; i < rows.size(); i++) {
			
			departureCities.add( rows.get(i).toString() );
		}
		
		tx.commit();
		session.close();
		
		
		return departureCities;
	}
	
	
	public List<String> getArrivalCities() {

		List<String> arrivalCities = new ArrayList<>();
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Query q = session.createNativeQuery("SELECT arrival_city FROM flights", Object.class);
		List<Object> rows = q.getResultList();
		
		for(int i = 0; i < rows.size(); i++) {
			
			arrivalCities.add( rows.get(i).toString() );
		}
		
		tx.commit();
		session.close();
		
		
		return arrivalCities;
	}

	
}
