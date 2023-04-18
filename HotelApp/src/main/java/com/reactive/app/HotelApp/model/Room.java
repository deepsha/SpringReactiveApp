package com.reactive.app.HotelApp.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@EntityScan
@Table(name="Room_Reservation")
public class Room {

	@Id
	private int id;

	@Column(value="ROOM_TYPE")
	private String roomType;
	@Column(value="TOTAL_ROOM_NUMBERs")
	private Integer totalNoOfroom;
	@Column(value="ROOM_BOOKING_START_DATE")
	private String roomBookingStartDate;
	@Column(value="ROOM_BOOKING_END_DATE")
	private String roomBookingEndDate;
	@Column(value="ROOM_AVAILABLE")
	private boolean roomAvailable;
	@Column(value="PRICE_ROOM")
	private Integer pricePerRoom;
	public Room() {}
	
	public Room( String roomType, Integer totalNoOfroom, String roomBookingStartDate, String roomBookingEndDate,
			boolean roomAvailable, Integer pricePerRoom) {
		super();
		
		this.roomType = roomType;
		this.totalNoOfroom = totalNoOfroom;
		this.roomBookingStartDate = roomBookingStartDate;
		this.roomBookingEndDate = roomBookingEndDate;
		this.roomAvailable = roomAvailable;
		this.pricePerRoom = pricePerRoom;
	}

	

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomType=" + roomType + ", totalNoOfroom=" + totalNoOfroom
				+ ", roomBookingStartDate=" + roomBookingStartDate + ", roomBookingEndDate=" + roomBookingEndDate
				+ ", roomAvailable=" + roomAvailable + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public Integer getTotalNoOfroom() {
		return totalNoOfroom;
	}
	public void setTotalNoOfroom(Integer totalNoOfroom) {
		this.totalNoOfroom = totalNoOfroom;
	}
	public String getRoomBookingStartDate() {
		return roomBookingStartDate;
	}
	public void setRoomBookingStartDate(String roomBookingStartDate) {
		this.roomBookingStartDate = roomBookingStartDate;
	}
	public String getRoomBookingEndDate() {
		return roomBookingEndDate;
	}
	public void setRoomBookingEndDate(String roomBookingEndDate) {
		this.roomBookingEndDate = roomBookingEndDate;
	}
	public boolean isRoomAvailable() {
		return roomAvailable;
	}
	public void setRoomAvailable(boolean roomAvailable) {
		this.roomAvailable = roomAvailable;
	}

	public Integer getPricePerRoom() {
		return pricePerRoom;
	}

	public void setPricePerRoom(Integer pricePerRoom) {
		this.pricePerRoom = pricePerRoom;
	}

	



}
