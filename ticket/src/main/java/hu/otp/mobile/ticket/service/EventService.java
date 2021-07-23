package hu.otp.mobile.ticket.service;

import java.util.List;

import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;

public interface EventService {

	/**
	 * Queries the event details
	 * 
	 * @param eventId the event ID, not <code>null</code>
	 * @return returns the event's seating details
	 */
	public EventSeating getEvent(Long eventId);

	/**
	 * Queries the list of events
	 * 
	 * @return an array of less verbose event data
	 */
	public List<Event> getEvents();
}
