package rd.sb_airplane_mvc.dao;

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

	
	
}
