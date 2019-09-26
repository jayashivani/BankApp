package com.bv.servive;

import java.util.List;

import com.bv.dao.ReservationDao;
import com.bv.model.Reservation;

public interface ReservationService {

	public  void setReservationDao(ReservationDao reservationDao);

	void addPassenger(Reservation reservation);
	void updatePassenger(Reservation reservation);
	List<Reservation> getAllPassengers();
	void removePassenger(int id);
	Reservation getPassengerById(int id);
}
