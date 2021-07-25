package hu.otp.mobile.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.api.client.TicketClient;
import hu.otp.mobile.api.service.EventService;
import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;

@Service
public class EventServiceImpl implements EventService {

	private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

	@Autowired
	TicketClient ticketClient;

	@Override
	public EventSeating getEvent(Long eventId) {

		log.info("Sending event seating data query to ticket module");

		return ticketClient.getEvent(eventId);
	}

	@Override
	public List<Event> getEvents() {

		log.info("Sending events data query to ticket module");

		return ticketClient.getEvents();
	}

}
