package com.reactive.app.HotelApp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.reactive.app.HotelApp.model.Room;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomRservationControllerTest {
	
	private final String URL="http://localhost:8080/api/rooms";
	@Autowired
	private ApplicationContext context;
	private Room room;
	private WebTestClient webTestClient;
	@Before
	public void setUp() throws Exception{
		webTestClient=WebTestClient.bindToApplicationContext(this.context).build();
		room=new Room("Standard",2,"2022-09-11","2022-09-17",false,160);
	}
	
	@Test
	public void getAllRoomsTest()
	{
		webTestClient.get().uri(URL).exchange().expectStatus().isOk().expectBodyList(Room.class);
		
	}

	@Test
	public void createRoomTest() {
		webTestClient.post().uri(URL).body(Mono.just(room),Room.class).exchange().expectStatus().isOk().expectHeader().
		contentType(MediaType.APPLICATION_JSON).expectBody(Room.class);
	}
}
