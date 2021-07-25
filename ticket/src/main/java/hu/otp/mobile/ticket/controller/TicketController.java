package hu.otp.mobile.ticket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.otp.mobile.ticket.service.EventService;
import hu.otp.mobile.ticket.service.ReservationService;
import opt.mobile.common.dto.ReservationSuccessDto;
import otp.mobile.common.domain.Event;
import otp.mobile.common.domain.EventSeating;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	EventService eventService;
	@Autowired
	ReservationService reservationService;

	private final Logger log = LoggerFactory.getLogger(TicketController.class);

	@GetMapping(path = "/getEvent/{eventId}")
	ResponseEntity<EventSeating> getEvent(@PathVariable Long eventId) {

		log.info("Received event detail request, eventId={}", eventId);

		return ResponseEntity.ok(eventService.getEvent(eventId));
	}

	@GetMapping(path = "/getEvents")
	ResponseEntity<List<Event>> getEvents() {

		log.info("Received event list request");

		return ResponseEntity.ok(eventService.getEvents());
	}

	@PostMapping(path = "/reserve")
	ResponseEntity<ReservationSuccessDto> reserve(@RequestParam("User-Token") String userToken,
			@RequestParam(name = "eventId", required = true) Long eventId, @RequestParam(name = "seatId", required = true) Long seatId,
			@RequestParam(name = "cardId", required = true) Long cardId) {

		log.info("Received event seat purchase request, userToken={}, eventId={}, seatId={}, cardId={}", userToken, eventId, seatId,
				cardId);

		return ResponseEntity.ok(reservationService.reserve(eventId, seatId, cardId, userToken));
	}
}
