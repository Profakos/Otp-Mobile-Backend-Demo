package hu.otp.mobile.api.service;

import java.util.List;

import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;

public interface EventService {
	public EventSeating getEvent(int eventId);

	public List<Event> getEvents();
}
