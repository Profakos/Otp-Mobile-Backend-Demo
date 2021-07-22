package otp.mobile.backend.partner.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.ReservationResult;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {

	private final Logger log = LoggerFactory.getLogger(PartnerController.class);

	@GetMapping(path = "/getEvent/{eventId}")
	ResponseEntity<EventSeating> fetchEvent(@PathVariable int eventId) {

		log.debug("Querying the seating data of an event, eventId={}", eventId);

		return ResponseEntity.ok(null);
	}

	@GetMapping(path = "/getEvents")
	ResponseEntity<List<Event>> fetchEvents() {

		log.debug("Querying the description of all events");

		return ResponseEntity.ok(null);
	}

	@PostMapping(path = "reserve")
	ResponseEntity<ReservationResult> reserveEvent(@RequestParam(name = "eventId", required = true) int eventId,
			@RequestParam(name = "seatId", required = true) int seatId) {

		log.debug("Attempting reservation at a specific event and seat, eventId={}, seatId={}", eventId, seatId);

		return ResponseEntity.ok(null);
	}
}