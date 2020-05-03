package com.capg.Skylines.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {

	@Id
	@Column(name="FLIGHTID")
	private Integer flightId;
	
	@Column(name = "FROMCITY")
	private String fromCity;
	
	@Column(name = "TOCITY")
	private String toCity;
	
	@Column(name = "TIMED")
	private String timeD;
	
	@Column(name = "FARE")
	private Double fare;
	
	@Column(name = "TOTALFARE")
	private Double totalFare;
	
	@Column(name = "AVAILABLESEATS")
	private Integer availableSeats;
	
	
	public Flight() {
	}
	
	public Flight(Integer flightId,String from, String to, String timeD, Double fare) {
		this.flightId = flightId;
		this.fromCity = from;
		this.toCity = to;
		this.timeD = timeD;
		this.fare = fare;
		totalFare = fare * 1.18;
		setAvailableSeats(50);
	}
	
	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}


	public String getFrom() {
		return fromCity;
	}

	public void setFrom(String from) {
		this.fromCity = from;
	}

	public String getTo() {
		return toCity;
	}

	public void setTo(String to) {
		this.toCity = to;
	}

	public String getTimeD() {
		return timeD;
	}

	public void setTimeD(String timeD) {
		this.timeD = timeD;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public Double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	
	
	
	
}
