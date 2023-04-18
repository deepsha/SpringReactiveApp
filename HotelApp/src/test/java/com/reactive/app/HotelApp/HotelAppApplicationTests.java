package com.reactive.app.HotelApp;

import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.reactive.app.HotelApp.controller.RoomReservationController;
import com.reactive.app.HotelApp.model.Room;
import com.reactive.app.HotelApp.repository.RoomRepository;
import com.reactive.app.HotelApp.service.RoomReservationService;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Mono;
/*
 * WebTestClient is similar to MockMvc. The only difference between those test web clients is that WebTestClient is aimed at testing WebFlux endpoints.
 * We are using @ExtendWith( SpringExtension.class ) to support testing in Junit 5. In Junit 4, we need to use @RunWith(SpringRunner.class).
 */
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RoomReservationController.class)
@Import(RoomReservationService.class)
public class HotelAppApplicationTests {

	private final String URL="http://localhost:8080/api/rooms";
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(HotelAppApplicationTests.class);

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	RoomRepository repository;

	@Test
	public void contextLoads() {
		Assertions.assertNotNull(repository);
		Assertions.assertNotNull(webTestClient);
	}

	@Test
	public void createRoomTest() {

		Room room=new Room();
		//	room.setId(1);
		room.setRoomType("Standard");
		room.setTotalNoOfroom(3);
		room.setRoomBookingStartDate("2022-09-11");
		room.setRoomBookingEndDate("2022-09-20");
		room.setRoomAvailable(false);
		Mockito.when(repository.save(room)).thenReturn(Mono.just(room));
		webTestClient.post().uri(URL)
		.contentType(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromObject(room))
		.exchange()
		.expectStatus().isCreated();

		//  Mockito.verify(repository, times(1)).save(room);
	}

	@Test
	public void getRoomByIdTest() {

		Room room=new Room();
		room.setId(1);
		room.setRoomType("Standard");
		room.setTotalNoOfroom(3);
		room.setRoomBookingStartDate("2022-09-11");
		room.setRoomBookingEndDate("2022-09-20");
		room.setRoomAvailable(false);
		room.setPricePerRoom(100);
		Mockito.when(repository.findById(1)).thenReturn(Mono.just(room));
		String url=URL+"/{id}";

		webTestClient.get().uri(url, 1)
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$.roomType").isNotEmpty()
		.jsonPath("$.id").isEqualTo(1)
		.jsonPath("$.roomType").isEqualTo("Standard")
		.jsonPath("$.totalNoOfroom").isEqualTo(3)
		.jsonPath("$.pricePerRoom").isEqualTo(100);

		Mockito.verify(repository, times(1)).findById(1);
	}


	@Test
	public void deleteRoomByIdTest() {

		Room room=new Room();
		room.setId(1);
		room.setRoomType("Deluxe");
		room.setTotalNoOfroom(3);
		room.setRoomBookingStartDate("2022-09-11");
		room.setRoomBookingEndDate("2022-09-20");
		room.setRoomAvailable(false);
		room.setPricePerRoom(100);
		Mockito.when(repository.findById(1)).thenReturn(Mono.just(room));
		String url=URL+"/{id}";

		webTestClient.delete().uri(url, 1)
		.exchange()
		.expectStatus().isNoContent();



	}
	@Test
	public void updateRoomByIdTest() {

		Room room=new Room();
		room.setId(1);
		room.setRoomType("Deluxe");
		room.setTotalNoOfroom(3);
		room.setRoomBookingStartDate("2022-09-11");
		room.setRoomBookingEndDate("2022-09-20");
		room.setRoomAvailable(false);
		room.setPricePerRoom(150);
		Mockito.when(repository.findById(1)).thenReturn(Mono.just(room));
		String url=URL+"/{id}";

		webTestClient.put().uri(url, 1)
		.contentType(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromObject(room))
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$.roomType").isNotEmpty()
		.jsonPath("$.id").isEqualTo(1)
		.jsonPath("$.roomType").isEqualTo("Deluxe")
		.jsonPath("$.totalNoOfroom").isEqualTo(3)
		.jsonPath("$.pricePerRoom").isEqualTo(150);



	}

}