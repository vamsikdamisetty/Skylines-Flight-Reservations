package com.capg.Skylines.service;

import java.util.ArrayList;

import com.capg.Skylines.entity.Booking;
import com.capg.Skylines.entity.Flight;
import com.capg.Skylines.entity.Users;
import com.capg.Skylines.exceptions.AccountValidationException;
import com.capg.Skylines.exceptions.FlightNotFoundException;
import com.capg.Skylines.exceptions.NoBookingsException;
import com.capg.Skylines.exceptions.UserNotFoundException;

public interface ARSService {

	// For Registering new user
	boolean registerUser(Users Users);

	// For logging in user
	Integer loginValidate(String uname, String pass) throws AccountValidationException;

	// For details of user
	Users getUser(Integer userId) throws UserNotFoundException;

	// Searching for a flight
	Flight searchFlight(String from, String to) throws FlightNotFoundException;

	// To Book Flight
	boolean bookFlight(Integer flightId, Integer userId, String name, String age, String gender);

	// Show all the bookings for paticular user
	ArrayList<Booking> myBookings(Integer userId) throws NoBookingsException;

	// Cancelling any reservation
	boolean cancelReservation(Integer bookingId, Integer flightId);

}
