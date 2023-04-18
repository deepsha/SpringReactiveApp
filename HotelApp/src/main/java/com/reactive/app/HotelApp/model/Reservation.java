package com.reactive.app.HotelApp.model;

import org.springframework.data.annotation.Id;

public class Reservation {
	
	@Id
	private Integer reservationId;
	
	private String checkIn;
	
	private String checkOut;
	
	private Double price;
	


}
