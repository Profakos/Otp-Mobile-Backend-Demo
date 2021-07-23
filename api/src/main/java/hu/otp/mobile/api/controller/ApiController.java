package hu.otp.mobile.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.otp.mobile.api.service.EventService;
import hu.otp.mobile.api.service.ReservationService;
import hu.otp.mobile.api.service.TokenService;
import opt.mobile.backend.common.dto.ReservationResult;
import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;

@RestController
@RequestMapping("/api")
public class ApiController {

	private final Logger log = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	EventService eventService;
	@Autowired
	ReservationService registrationService;
	@Autowired
	TokenService tokenService;

	@GetMapping(path = "/getEvent/{eventId}")
	ResponseEntity<EventSeating> getEvent(@RequestHeader("User-Token") String userToken, @PathVariable Long eventId) {

		log.info("Querying the details of an event, eventId={}", eventId);

		if (!tokenService.validateUser(userToken)) {

			log.warn("User authentication failed.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

		return ResponseEntity.ok(eventService.getEvent(eventId));
	}

	@GetMapping(path = "/getEvents")
	ResponseEntity<List<Event>> getEvents(@RequestHeader("User-Token") String userToken) {

		log.info("Querying the description of all events");

		if (!tokenService.validateUser(userToken)) {

			log.warn("User authentication failed.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

		return ResponseEntity.ok(eventService.getEvents());
	}

	@PostMapping(path = "pay")
	ResponseEntity<ReservationResult> pay(@RequestHeader("User-Token") String userToken,
			@RequestParam(name = "eventId", required = true) Long eventId, @RequestParam(name = "seatId", required = true) Long seatId,
			@RequestParam(name = "cardId", required = true) Long cardId) {

		log.info("Attempting reservation at a specific event and seat with a specific card, eventId={}, seatId={}, cardId={}", eventId,
				seatId, cardId);

		if (!tokenService.validateUser(userToken)) {

			log.warn("User authentication failed.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

		return ResponseEntity.ok(registrationService.pay(eventId, seatId, cardId, userToken));
	}
}
