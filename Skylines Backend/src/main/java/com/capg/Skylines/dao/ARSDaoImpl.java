package com.capg.Skylines.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capg.Skylines.entity.Booking;
import com.capg.Skylines.entity.Flight;
import com.capg.Skylines.entity.Users;
import com.capg.Skylines.exceptions.AccountValidationException;
import com.capg.Skylines.exceptions.FlightNotFoundException;
import com.capg.Skylines.exceptions.NoBookingsException;
import com.capg.Skylines.exceptions.UserNotFoundException;

@Repository
public class ARSDaoImpl implements ARSDao {

	@Autowired
	private EntityManager entityManager;

	public ARSDaoImpl() {
	}

	

	/*
	 * Method : getFlight Description : Used to fetch the flight details from
	 * database.
	 * 
	 * @param flightId : Identification of the flight .
	 * 
	 * @return Flight : It returns the data of the Flight
	 */
	@Override
	public Flight getFlight(Integer flightId) {
		Flight Flight = entityManager.find(Flight.class, flightId);
		return Flight;
	}

	
	
	/*
	 * Method : getFlight Description : Used to fetch the flight details from
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
	@Transactional
	@Override
	public Flight getFlight(String from, String to) throws FlightNotFoundException {
		try {
			String command = "select fd from Flight fd where fd.fromCity = :from and fd.toCity = :to";
			TypedQuery<Flight> query = entityManager.createQuery(command, Flight.class);
			query.setParameter("from", from);
			query.setParameter("to", to);
			Flight flight;
			flight = query.getResultList().get(0);
			return flight;
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
		}
		throw new FlightNotFoundException("Flight doesn't Exist");
	    
	}

	
	
	
	/*
	 * Method : addBooking
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
	public boolean addBooking(Booking bd) {
		
		Integer tempBD;
		String command = "select count(bd.bookingId) from Booking bd";
		TypedQuery<Long> query = entityManager.createQuery(command, Long.class);
		long count = query.getSingleResult();
		if (count > 0) {
			command = "select max(bd.bookingId) from Booking bd";
			TypedQuery<Integer> query2 = entityManager.createQuery(command, Integer.class);
			Integer bid = query2.getSingleResult();
			tempBD = bid + 1;
		} else {
			tempBD = 1254;
		}

		bd.setBookingId(tempBD);
		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(bd);
		return true;
	}

	
	
	/*
	 * Method : getBookingDetails
	 * 
	 * Description : Used to fetch the all booking details from
	 * database.
	 * 
	 * @param flightId : Identification of the Booking.
	 * 
	 * @return ArrayList<Booking> : It returns the ArrayList of bookings.
	 * 
	 * throws NoBookingsException : It is raised if there are no bookings in the database.
	 */
	@Override
	public Booking getBookingDetails(Integer bookindId) {
		return entityManager.find(Booking.class, bookindId);
	}

	
	
	
	/*
	 * Method : getBookingDetails
	 * 
	 * Description : Used to fetch the all booking details from
	 * database.
	 * 
	 * @param flightId : Identification of the User who made the reservations.
	 * 
	 * @return ArrayList<Booking> : It returns the ArrayList of bookings.
	 * 
	 * throws NoBookingsException : It is raised if there are no bookings in the database.
	 */
	@Override
	public List<Booking> getAllBooking(Integer userId) throws NoBookingsException {
		String command = "select bd from Booking bd where bd.userId = :uid order by bd.status asc , bd.bookingId desc";
		TypedQuery<Booking> query = entityManager.createQuery(command, Booking.class);
		query.setParameter("uid", userId);
		List<Booking> bookings;
		try {
		bookings =  query.getResultList();
		}
		catch (NoResultException e) {
			bookings = null;
		}
		if(bookings == null)
		{
			throw new NoBookingsException("No Bookings Yet");
		}
		return bookings;
		
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
	public boolean cancelReservation(Booking bd, Flight flight) {
		bd.setStatus('C');

		flight.setAvailableSeats((flight.getAvailableSeats() + 1));
		Session cs = entityManager.unwrap(Session.class);

		cs.saveOrUpdate(bd);
		cs.saveOrUpdate(flight);
		return true;
	}

	
	
	/*
	 * Method : createAccount 
	 * 
	 * Description : Used to register the details of user.
	 * 
	 * @param Users : Data of users.
	 * 
	 * @return boolean : returns true if the insertion is successful.
	 */
	@Override
	public boolean createAccount(Users ud) {

		Integer userId = 1;
		String command = "select count(ud.userId) from Users ud";
		TypedQuery<Long> query = entityManager.createQuery(command, Long.class);
		long count = query.getSingleResult();
		if (count > 0) {
			command = "select max(ud.userId) from Users ud";
			TypedQuery<Integer> query2 = entityManager.createQuery(command, Integer.class);
			Integer uid = query2.getSingleResult();
			userId = uid + 1;
		}
		ud.setUserId(userId);
		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(ud);
		
		return true;
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
	@Transactional
	@Override
	public Integer loginValidate(String uName, String passwd) throws AccountValidationException {

		Integer uid;
		try {
			String command = "select ud.userId from Users ud where ud.username = :uName and ud.password = :pass";
			TypedQuery<Integer> query = entityManager.createQuery(command, Integer.class);
			query.setParameter("uName", uName);
			query.setParameter("pass", passwd);
			uid = 0;
			uid = query.getSingleResult();

		} catch (NoResultException e) {
			uid = 0;
		}
		if(uid == 0)
		{
			throw new AccountValidationException("User Does not exist");
		}
		return uid;
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

		String command = "select ud from Users ud where ud.userId = :userId ";
		TypedQuery<Users> query = entityManager.createQuery(command, Users.class);
		query.setParameter("userId", userId);
		List<Users> users = new ArrayList<>();
		users = query.getResultList();
		if (users.size() > 0)
			return users.get(0);
		else
			throw new UserNotFoundException("User Not exists");

	}
	
	
	/*
	 * Method : addNewFlight 
	 * 
	 * Description : Used to add the flight details into
	 * database.
	 * 
	 * @param fd : Details of Flight to be inserted.
	 * 
	 */
	
	@Override
	public void addNewFlight(Flight fd) {

		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(fd);
	}
	
	
	
	/*
	 * Method : updateFlight 
	 * 
	 * Description : Used to update the flight details in
	 * database.
	 * 
	 * @param fd : Details of Flight to be inserted.
	 * 
	 */
	
	@Override
	public void updateFlight(Flight fd) {

		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(fd);

	}

}
