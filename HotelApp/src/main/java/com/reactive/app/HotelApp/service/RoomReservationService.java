package com.reactive.app.HotelApp.service;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.app.HotelApp.model.Room;
import com.reactive.app.HotelApp.repository.RoomRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class RoomReservationService {

	private Logger logger = LoggerFactory.getLogger(RoomReservationService.class);

	
	private final RoomRepository roomrepository;

	@Autowired
	public RoomReservationService(RoomRepository roomrepository) {
		this.roomrepository = roomrepository;
	}

	public Flux<Room> findAll() {		
		return roomrepository.findAll();
	}

	public Flux<Room> findByRoomType(String roomType) {
		return roomrepository.findByRoomType(roomType);
	}

	public Mono<Room> findById(int id) {
		return roomrepository.findById(id);
	}

	public Mono<Room> createRoom(Room room) {
		logger.info(room.toString());
		return roomrepository.save(room);
	}

	public Mono<Room> updateRoom(int id, Room room) {
		return roomrepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(optionalRoom -> {
					if (optionalRoom.isPresent()) {
						room.setId(id);
						return roomrepository.save(room);
					}

					return Mono.empty();
				});
	}

	public Mono<Void> deleteRoomById(int id) {

		return roomrepository.deleteById(id);
	}

	public Mono<Void> deleteAllRooms() {

		return roomrepository.deleteAll();
	}

	public Flux<Room> findByRoomAvailableStatus(boolean available) {
		return roomrepository.findByRoomAvailable(available);
	}

	public Mono<Room> updateByRoomAvailableStatus(int id, boolean status) {
		return roomrepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(optionalRoom -> {
					if (optionalRoom.isPresent()) {
						Room room=optionalRoom.get();
						room.setRoomAvailable(status);
						return roomrepository.save(room);
					}

					return Mono.empty();
				});
	}

	public Flux<Room> findByRoomAvailableGivenDates(String startDate, String endDate) {
		
		Flux<Room> list1=roomrepository.findByRoomBookingStartDate(startDate);
		Flux<Room> list2=roomrepository.findByRoomBookingEndDate(endDate);
		Flux<Room> combinedFlux = Flux.concat(list1, list2);
		return combinedFlux;
	}



}
