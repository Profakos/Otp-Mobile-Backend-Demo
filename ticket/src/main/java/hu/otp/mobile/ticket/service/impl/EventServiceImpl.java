package hu.otp.mobile.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.ticket.client.PartnerClient;
import hu.otp.mobile.ticket.service.EventService;
import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	PartnerClient partnerClient;

	@Override
	public EventSeating getEvent(Long eventId) {

		return partnerClient.getEvent(eventId);
	}

	@Override
	public List<Event> getEvents() {

		return partnerClient.getEvents();
	}

}
