package com.capg.Skylines;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.Skylines.entity.Booking;
import com.capg.Skylines.entity.Flight;
import com.capg.Skylines.entity.Users;
import com.capg.Skylines.exceptions.AccountValidationException;
import com.capg.Skylines.exceptions.FlightNotFoundException;
import com.capg.Skylines.exceptions.UnableToBookException;
import com.capg.Skylines.exceptions.NoBookingsException;
import com.capg.Skylines.exceptions.UserNotFoundException;
import com.capg.Skylines.service.AirlineReserationSystem;

//(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
class ProjectController {

	@Autowired
	AirlineReserationSystem ars;

	Logger logger = LoggerFactory.getLogger(SkylinesApplication.class);

	
	
	/*
	 * Method : getFlight Description : Used to fetch the flight details from
	 * database.
	 * 
	 * @param flightId : Identification of the flight .
	 * 
	 * @return Flight : It returns the data of the Flight
	 */
	@GetMapping("/flight/{id}")
	public Flight getFlight(@PathVariable Integer flightId) {
		String msg = "Fetching Flight...";
		logger.info(msg);
		return ars.getFlight(flightId);
	}

	
	
	
	/*
	 * Method : getBookings Description : Used to fetch the all booking details from
	 * database.
	 * 
	 * @param flightId : Identification of the User who made the reservations.
	 * 
	 * @return ArrayList<Booking> : It returns the ArrayList of bookings.
	 * 
	 * throws NoBookingsException : It is raised if there are no bookings in the database.
	 */
	@GetMapping("/all/{id}")
	public ArrayList<Booking> getBookings(@PathVariable Integer userId) throws NoBookingsException {
		String msg = "Fetching Flight...";
		logger.info(msg);
		return ars.myBookings(userId);
	}

	
	
	
	/*
	 * Method : createUser 
	 * 
	 * Description : Used to register the details of user.
	 * 
	 * @param Users : Data of users.
	 * 
	 * @return boolean : returns true if the insertion is successful.
	 */
	@PostMapping("/addUser")
	public boolean createUser(@RequestBody Users users) {
		String msg = "Account Creation Request Processing";
		logger.info(msg);
		return ars.registerUser(users);
	}

	
	
	
	/*
	 * Method : login 
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
	@GetMapping("/login/{uName}/{pass}")
	public Integer login(@PathVariable String uName, @PathVariable String pass) throws AccountValidationException {
		String msg = "LogIn Request Processing..";
		logger.info(msg);
		return ars.loginValidate(uName, pass);
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
	@GetMapping("/getUser/{userId}")
	public Users getUser(@PathVariable Integer userId) throws UserNotFoundException {
		String msg = "Fetching User Details..";
		logger.info(msg);
		return ars.getUser(userId);
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
	@GetMapping("/searchFlight/{from}/{to}")
	public Flight searchFlight(@PathVariable String from, @PathVariable String to) throws FlightNotFoundException {
		String msg = "Fetching Flight Details..";
		logger.info(msg);
		return ars.searchFlight(from, to);
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
	@GetMapping("/bookFlight/{flightId}/{userId}/{name}/{age}/{gender}")
	public boolean bookFlight(@PathVariable Integer flightId, @PathVariable Integer userId, @PathVariable String name,
			@PathVariable String age, @PathVariable String gender) throws UnableToBookException {

		String msg = "Booking Flight..";
		logger.info(msg);

		return ars.bookFlight(flightId, userId, name, age, gender);

	}

	
	
	
	/*
	 * Method : cancelFlight 
	 * 
	 * Description : Used to cancel the flight Reservation.
	 * 
	 * @params bookingId : Identification of the booking.
	 * 
	 * @params fligthId : Flight identification
	 * 
	 * @return boolean : returns true if flight is cancelled.
	 */
	@GetMapping("/cancelFlight/{bookingId}/{flightId}")
	public boolean cancelFlight(@PathVariable Integer bookingId, @PathVariable Integer flightId) {
		String msg = "Cancelling Reservation..";
		logger.info(msg);
		return ars.cancelReservation(bookingId, flightId);
	}

}
