package hu.otp.mobile.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.api.client.TicketClient;
import hu.otp.mobile.api.service.EventService;
import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	TicketClient ticketClient;

	@Override
	public EventSeating getEvent(Long eventId) {

		return ticketClient.getEvent(eventId);
	}

	@Override
	public List<Event> getEvents() {

		return ticketClient.getEvents();
	}

}
