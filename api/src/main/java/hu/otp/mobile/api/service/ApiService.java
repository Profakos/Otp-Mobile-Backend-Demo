package hu.otp.mobile.api.service;

import java.util.List;

import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.ReservationResult;

public interface ApiService {
	public EventSeating getEvent(int eventId);

	public List<Event> getEvents();

	public ReservationResult pay(int eventId, int seatId, int cardId);
}
