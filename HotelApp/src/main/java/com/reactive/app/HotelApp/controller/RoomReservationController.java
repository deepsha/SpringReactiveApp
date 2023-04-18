package com.reactive.app.HotelApp.controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.reactive.app.HotelApp.model.Room;
import com.reactive.app.HotelApp.service.RoomReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RoomReservationController {
	
	private Logger logger = LoggerFactory.getLogger(RoomReservationController.class);

	private final RoomReservationService roomReservationService;
	@Autowired
	public RoomReservationController(RoomReservationService roomReservationService) {
		this.roomReservationService = roomReservationService;
	}

	@GetMapping("/rooms")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<Flux<Room>> getAllRooms()
	{
		Flux<Room> e = roomReservationService.findAll();
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);

	}

	@GetMapping("/rooms/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<Room>> getRoomsById(@PathVariable("id") int id)
	{

		Mono<Room> e = roomReservationService.findById(id);
		HttpStatus status = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, status);
	}
	@PostMapping("/rooms")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Room> createRoom(@RequestBody Room room) {
		 return roomReservationService.createRoom(new Room(room.getRoomType(), room.getTotalNoOfroom(), room.getRoomBookingStartDate(),room.getRoomBookingEndDate(),room.isRoomAvailable(),room.getPricePerRoom()));
	}

	@PutMapping("/rooms/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Room> updateRoom(@PathVariable("id") int id, @RequestBody Room room) {
		logger.info("updateRoom:"+id);
		return roomReservationService.updateRoom(id, room);
	}

	@DeleteMapping("/rooms/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteRoom(@PathVariable("id") int id) {
		return roomReservationService.deleteRoomById(id);
	}

	@DeleteMapping("/rooms")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteAllRooms() {
		return roomReservationService.deleteAllRooms();
	}

	@GetMapping("/rooms/available")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Room> findByRoomAvailableStatus() {
		return roomReservationService.findByRoomAvailableStatus(true);
	}

	@PutMapping("/rooms/dates")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<Flux<Room>>  findByRoomAvailableGivenDates(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {

		Flux<Room> e =  roomReservationService.findByRoomAvailableGivenDates(startDate,endDate);
		HttpStatus response = (e != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(e, response);
	}

}
