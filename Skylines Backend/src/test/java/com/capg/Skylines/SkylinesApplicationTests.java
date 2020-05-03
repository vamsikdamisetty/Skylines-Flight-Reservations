package com.capg.Skylines;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.capg.Skylines.entity.Booking;
import com.capg.Skylines.entity.Flight;
import com.capg.Skylines.entity.Users;
import com.capg.Skylines.exceptions.AccountValidationException;
import com.capg.Skylines.exceptions.FlightNotFoundException;
import com.capg.Skylines.exceptions.NoBookingsException;
import com.capg.Skylines.exceptions.UserNotFoundException;
import com.capg.Skylines.service.AirlineReserationSystem;

@SpringBootTest
class SkylinesApplicationTests {

	@Autowired
	AirlineReserationSystem arsObject;
	
	
	/*
	 * Test : getFlight
	 * description : Testing the fetching of flight.
	 */
	@Test
	void getFlightTest() {
		Flight flight = arsObject.getFlight(1);
		assertEquals("PUNE", flight.getFrom());
	}

	
	
	/*
	 * Test : userRegister
	 * description : Testing the registration of user.
	 */
	@Test
	@Rollback
	@Transactional
	void userRegisterTest() throws UserNotFoundException {
		Users user = new Users("Vamsi","vamsi@gmail.com","Krish@999","Krish@123",9056373247L);
		arsObject.registerUser(user);
		Integer userId = user.getUserId();
		Users user2 = arsObject.getUser(userId);
		assertEquals(user.getName(), user2.getName());
		
	}
	
	
	
	/*
	 * Test : searchFlight
	 * description : Testing the search of flight.
	 */
	@Test
	void searchFlight() throws FlightNotFoundException {
		String from = "PUNE";
		String to = "DELHI";
		Flight flight = arsObject.searchFlight(from, to);
		assertEquals(from, flight.getFrom());
	}
	
	
	/*
	 * Test : login
	 * description : Testing the login Valiadation.
	 */
	@Test
	void loginTest() throws AccountValidationException {
		String uname = "Vamsi@999";
		String pass = "Vamsi@123";
		int actual = arsObject.loginValidate(uname, pass);
		assertEquals(1, actual);
	}
	
	
	
	/*
	 * Test : Book
	 * description : Testing the Booking of flight.
	 */
	@Test
	@Rollback
	@Transactional
	void bookTest() {
		
		boolean result = arsObject.bookFlight(5, 1, "Test", "20", "M");
		assertEquals(true, result);
		
	}
	
	
	/*
	 * Test : Bookings
	 * description : Testing the Bookings of flight.
	 */
	void bookingsTest() throws NoBookingsException {
		List<Booking> bookings = arsObject.myBookings(1);
		assertEquals(true, (bookings.size() > 0)?true:false);
	}
	
	
	
	/*
	 * Test : cancelReservation
	 * description : Testing the Cancellation of reservation.
	 */
	@Test
	@Rollback
	@Transactional
	void cancelReservationTest() throws NoBookingsException {
		List<Booking> bookings = arsObject.myBookings(1);
		Booking booking = bookings.get(0);
		arsObject.cancelReservation(booking.getBookingId(), 1);
		bookings = arsObject.myBookings(1);
		booking = bookings.get(0);
		assertEquals("C",""+ booking.getStatus());
	}
	
}
