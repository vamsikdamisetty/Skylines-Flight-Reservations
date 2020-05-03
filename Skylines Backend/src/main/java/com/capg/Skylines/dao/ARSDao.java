package com.capg.Skylines.dao;

import java.util.List;

import com.capg.Skylines.entity.Booking;
import com.capg.Skylines.entity.Flight;
import com.capg.Skylines.entity.Users;
import com.capg.Skylines.exceptions.AccountValidationException;
import com.capg.Skylines.exceptions.FlightNotFoundException;
import com.capg.Skylines.exceptions.NoBookingsException;
import com.capg.Skylines.exceptions.UserNotFoundException;


public interface ARSDao {

	boolean createAccount(Users ud);

	Integer loginValidate(String username, String password) throws AccountValidationException;

	Users getUser(Integer userId) throws UserNotFoundException;

	void addNewFlight(Flight fd);

	void updateFlight(Flight fd);

	Flight getFlight(Integer flightId);

	Flight getFlight(String from, String to) throws FlightNotFoundException;

	boolean addBooking(Booking bd);

	Booking getBookingDetails(Integer bookindId);

	List<Booking> getAllBooking(Integer userId) throws NoBookingsException;

	boolean cancelReservation(Booking bd, Flight flight);

}
