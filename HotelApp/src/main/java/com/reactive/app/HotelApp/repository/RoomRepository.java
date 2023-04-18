package com.reactive.app.HotelApp.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.reactive.app.HotelApp.model.Room;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface RoomRepository extends R2dbcRepository<Room,Integer>{

	Flux<Room> findByRoomType(String roomType);

	Flux<Room> findByRoomAvailable(boolean roomAvailable);

	//@Query('select * from Room  where roomBookingStartDate:=startDate and roomBookingEndDate:=endDate')
	Flux<Room> findByRoomBookingStartDate(String startDate);
	Flux<Room> findByRoomBookingEndDate(String endDate);

}
