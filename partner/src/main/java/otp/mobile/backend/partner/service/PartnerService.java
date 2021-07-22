package otp.mobile.backend.partner.service;

import java.util.List;

import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.ReservationResult;

public interface PartnerService {

	public EventSeating fetchEvent(int eventId);

	public List<Event> fetchEvents();

	public ReservationResult reserveSeat(int eventId, int seatId);
}
