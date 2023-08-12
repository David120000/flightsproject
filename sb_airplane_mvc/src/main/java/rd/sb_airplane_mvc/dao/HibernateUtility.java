package rd.sb_airplane_mvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;


@Component
public class HibernateUtility {
	
	private final SessionFactory sessionFactory;
	
	
	public HibernateUtility() {
		Configuration cfg = new Configuration();
		cfg.configure();
		
		this.sessionFactory = cfg.buildSessionFactory();	
	}
	
	
	public Session getSession() {
		return sessionFactory.openSession();
	}
	
}
