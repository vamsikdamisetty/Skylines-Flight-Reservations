package com.capg.Skylines.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.Skylines.dao.ARSDaoImpl;
import com.capg.Skylines.entity.Booking;
import com.capg.Skylines.entity.Flight;
import com.capg.Skylines.entity.Users;
import com.capg.Skylines.exceptions.AccountValidationException;
import com.capg.Skylines.exceptions.FlightNotFoundException;
import com.capg.Skylines.exceptions.NoBookingsException;
import com.capg.Skylines.exceptions.UserNotFoundException;

@Service
@Transactional
public class AirlineReserationSystem implements ARSService {

	@Autowired
	private ARSDaoImpl daoObject;
	


	public AirlineReserationSystem() {

	}

	/*
	 * Method : getFlight Description : Used to fetch the flight details from
	 * database.
	 * 
	 * @param flightId : Identification of the flight .
	 * 
	 * @return Flight : It returns the data of the Flight
	 */
	public Flight getFlight(Integer id) {
		return daoObject.getFlight(id);
	}

	
	
	/*
	 * Method : registerUser 
	 * 
	 * Description : Used to register the details of user.
	 * 
	 * @param Users : Data of users.
	 * 
	 * @return boolean : returns true if the insertion is successful.
	 */
	@Transactional
	@Override
	public boolean registerUser(Users Users) {

		return daoObject.createAccount(Users);
	}

	
	
	/*
	 * Method : loginValidate 
	 * 
	 * Description : Used to Validate the details of the user.
	 * 
	 * @param uName : username entered by user.
	 * 
	 * @param password : password entered by user.
	 * 
	 * @return Integer : returns 1 if the validation is successful. 
	 * 
	 * throws AccountValidationException : It is raised when no user exists with those
	 * credentials.
	 */
	@Override
	public Integer loginValidate(String uname, String pass) throws AccountValidationException {

		return daoObject.loginValidate(uname, pass);
	}

	
	/*
	 * Method : searchFlight Description : Used to fetch the flight details from
	 * database.
	 * 
	 * @param from : City of source.
	 * 
	 * @param to : City of destination.
	 * 
	 * @return Flight : It returns the data of the Flight. 
	 * 
	 * throws FlightNotFoundException : Raised when flight does not exist.
	 */
	@Override
	public Flight searchFlight(String from, String to) throws FlightNotFoundException {
		return daoObject.getFlight(from, to);
	}

	
	
	
	/*
	 * Method : bookFlight
	 * 
	 * Description : Used to book the flight .
	 * 
	 * @params : all the details for bookings.
	 * 
	 * @return boolean : returns true if flight is books. 
	 * 
	 * throws UnableToBookException : Raised when flight could not be booked.
	 * 
	 */
	
	@Transactional
	@Override
	public boolean bookFlight(Integer flightId, Integer userId, String name, String age, String gender) {

		Booking bd = new Booking();
		Flight fd = daoObject.getFlight(flightId);
		fd.setAvailableSeats(fd.getAvailableSeats() - 1);
		daoObject.updateFlight(fd);


		bd.setUserId(userId);
		bd.setStatus('B');
		bd.setName(name);
		bd.setAge(age);
		bd.setGender(gender.charAt(0));
		bd.setFlightData(fd);

		return daoObject.addBooking(bd);

	}

	/*
	 * Method : myBookings Description : Used to fetch the all booking details from
	 * database.
	 * 
	 * @param flightId : Identification of the User who made the reservations.
	 * 
	 * @return ArrayList<Booking> : It returns the ArrayList of bookings.
	 * 
	 * throws NoBookingsException : It is raised if there are no bookings in the database.
	 */
	@Override
	public ArrayList<Booking> myBookings(Integer flightId) throws NoBookingsException {

		ArrayList<Booking> mybookings = (ArrayList<Booking>) daoObject.getAllBooking(flightId);

		return mybookings;
	}

	
	

	/*
	 * Method : cancelReservation 
	 * 
	 * Description : Used to cancel the flight Reservation.
	 * 
	 * @params bookingId : Identification of the booking.
	 * 
	 * @params fligthId : Flight identification
	 * 
	 * @return boolean : returns true if flight is cancelled.
	 */
	@Override
	public boolean cancelReservation(Integer bookingId, Integer flightId) {

		Booking bd = daoObject.getBookingDetails(bookingId);
		Flight flight = daoObject.getFlight(flightId);

		if (bd != null) {
			daoObject.cancelReservation(bd, flight);
			return true;
		} else {
			return false;
		}

	}

	
	
	/*
	 * Method : getUser 
	 * 
	 * Description : Used to fetch the details of the user.
	 * 
	 * @param userId : user identification number.
	 * 
	 * @return Users : data of user. 
	 * 
	 * throws UserNotFoundException : It is raised when no user exists with that identification.
	 */
	@Override
	public Users getUser(Integer userId) throws UserNotFoundException {

		return daoObject.getUser(userId);
	}

}
