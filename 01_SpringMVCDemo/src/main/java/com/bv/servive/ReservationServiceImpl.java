package com.bv.servive;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bv.dao.ReservationDao;
import com.bv.model.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {

	ReservationDao reservationDao;
	

	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	@Transactional
	public void addPassenger(Reservation reservation) {
	reservationDao.addPassenger(reservation);

	}
	@Transactional
	public void updatePassenger(Reservation reservation) {
		reservationDao.updatePassenger(reservation);
	}
	@Transactional
	public List<Reservation> getAllPassengers() {
	
		return reservationDao.getAllPassengers();
	}
	@Transactional
	public void removePassenger(int id) {
		reservationDao.removePassenger(id);

	}
	@Transactional
	public Reservation getPassengerById(int id) {
		
		return reservationDao.getPassengerById(id);
	}

}
