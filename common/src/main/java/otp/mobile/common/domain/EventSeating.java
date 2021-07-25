package otp.mobile.common.domain;

import java.util.List;

public class EventSeating {

	private String eventId;
	private List<Seat> seats;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
}
