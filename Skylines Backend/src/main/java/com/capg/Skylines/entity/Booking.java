package com.capg.Skylines.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Booking")
public class Booking {

	@Id
	@Column(name = "BOOKINGID")
	private Integer bookingId;
	
	@ManyToOne
	@JoinColumn(name = "FLIGHTID")
	private Flight flightId;
	
	@Column(name = "USERID")
	private Integer userId;

	@Column(name = "status")
	private Character status;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "gender")
	private Character gender;
	
	public Booking() {
	}
	
	public Booking(Integer bookingId, Flight flightData, Integer userId,Character status) {
		super();
		this.bookingId = bookingId;
		this.flightId = flightData;
		this.userId = userId;
		this.status = status;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	public Flight getFlightData() {
		return flightId;
	}

	public void setFlightData(Flight flightData) {
		this.flightId = flightData;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	
}
