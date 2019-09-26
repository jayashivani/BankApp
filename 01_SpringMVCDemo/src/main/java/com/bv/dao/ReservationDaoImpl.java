package com.bv.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bv.model.Reservation;
@Repository
public class ReservationDaoImpl implements ReservationDao {
public static final Logger LOGGER = LoggerFactory.getLogger(ReservationDaoImpl.class);
	
private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

	public void addPassenger(Reservation reservation) {
	Session session =	sessionFactory.getCurrentSession();
	session.persist(reservation);
	LOGGER.info("Passenger added successfully: "+reservation);
	}

	public void updatePassenger(Reservation reservation) {
		Session session =	sessionFactory.getCurrentSession();
		session.update(reservation);
		LOGGER.info("Passenger updated successfully: "+reservation);

	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getAllPassengers() {
		Session session =	sessionFactory.getCurrentSession();
	List<Reservation> passangerList = 	session.createQuery("from Reservation").list();
		for(Reservation reserve: passangerList)
		{
			LOGGER.info("Passengers List: "+reserve);
		}
	
		return passangerList;
	}

	public void removePassenger(int id) {
		Session session =	sessionFactory.getCurrentSession();
		Reservation reservation = (Reservation) session.load(Reservation.class, new Integer(id));
		if(reservation != null)
		{
			session.delete(reservation);
		}
		LOGGER.info("Passenger deleted successfully: "+reservation);
	}

	public Reservation getPassengerById(int id) {
		Session session =	sessionFactory.getCurrentSession();
		Reservation reservation = (Reservation) session.load(Reservation.class, new Integer(id));
		LOGGER.info("Passenger loaded successfully: "+reservation);
		return reservation;
	}

}
