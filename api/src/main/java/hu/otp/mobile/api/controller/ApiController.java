package hu.otp.mobile.api.controller;

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
@RequestMapping("/api")
public class ApiController {

	private final Logger log = LoggerFactory.getLogger(ApiController.class);

	@GetMapping(path = "/getEvent/{eventId}")
	ResponseEntity<EventSeating> getEvent(@PathVariable int eventId) {

		log.debug("Querying the details of an event, eventId={}", eventId);

		return ResponseEntity.ok(null);
	}

	@GetMapping(path = "/getEvents")
	ResponseEntity<List<Event>> getEvents() {

		log.debug("Querying the description of all events");

		return ResponseEntity.ok(null);
	}

	@PostMapping(path = "pay")
	ResponseEntity<ReservationResult> pay(@RequestParam(name = "eventId", required = true) int eventId,
			@RequestParam(name = "seatId", required = true) int seatId, @RequestParam(name = "cardId", required = true) int cardId) {

		log.debug("Attempting reservation at a specific event and seat with a specific card, eventId={}, seatId={}, cardId={}", eventId,
				seatId, cardId);

		return ResponseEntity.ok(null);
	}
}
