package com.bv.dao;

import java.util.List;

import com.bv.model.Reservation;

public interface ReservationDao {

	void addPassenger(Reservation reservation);
	void updatePassenger(Reservation reservation);
	List<Reservation> getAllPassengers();
	void removePassenger(int id);
	Reservation getPassengerById(int id);
	
}
