package hu.otp.mobile.partner.controller;

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

import hu.otp.mobile.partner.service.EventService;
import hu.otp.mobile.partner.service.ReservationService;
import opt.mobile.backend.common.dto.ReservationResult;
import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;

@RestController
@RequestMapping("/partner")
public class PartnerController {

	private final Logger log = LoggerFactory.getLogger(PartnerController.class);

	@Autowired
	EventService eventService;
	@Autowired
	ReservationService registrationService;

	@GetMapping(path = "/getEvent/{eventId}")
	ResponseEntity<EventSeating> getEvent(@PathVariable Long eventId) {

		log.info("Querying the seating data of an event, eventId={}", eventId);

		return ResponseEntity.ok(eventService.getEvent(eventId));
	}

	@GetMapping(path = "/getEvents")
	ResponseEntity<List<Event>> getEvents() {

		log.info("Querying the description of all events");

		return ResponseEntity.ok(eventService.getEvents());
	}

	@PostMapping(path = "/reserve")
	ResponseEntity<ReservationResult> reserve(@RequestParam(name = "eventId", required = true) Long eventId,
			@RequestParam(name = "seatId", required = true) Long seatId) {

		log.info("Attempting reservation at a specific event and seat, eventId={}, seatId={}", eventId, seatId);

		return ResponseEntity.ok(registrationService.reserve(eventId, seatId));
	}
}
