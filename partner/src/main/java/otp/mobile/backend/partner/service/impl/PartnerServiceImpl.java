package otp.mobile.backend.partner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.ReservationResult;
import otp.mobile.backend.partner.service.PartnerService;
import otp.mobile.backend.partner.util.EventJsonParserUtil;

@Service
public class PartnerServiceImpl implements PartnerService {

	@Override
	public EventSeating fetchEvent(int eventId) {

		return EventJsonParserUtil.readEventSeating(eventId);
	}

	@Override
	public List<Event> fetchEvents() {

		List<Event> events = new ArrayList<>();

		Event event = new Event();
		events.add(event);

		return events;
	}

	@Override
	public ReservationResult reserveSeat(int eventId, int seatId) {

		ReservationResult result = new ReservationResult();

		return result;
	}

}
