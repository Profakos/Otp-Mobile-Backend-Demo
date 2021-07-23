package hu.otp.mobile.partner.service;

import java.util.List;

import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.ReservationResult;

public interface PartnerService {

	public EventSeating getEvent(int eventId);

	public List<Event> getEvents();

	public ReservationResult reserve(int eventId, int seatId);
}
