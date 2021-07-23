package hu.otp.mobile.partner.service;

import opt.mobile.backend.common.dto.ReservationResult;

public interface ReservationService {

	/**
	 * Attempts to reserve a seat for the specific event
	 * 
	 * @param eventId the event ID, not <code>null</code>
	 * @param seatId the seat's ID, not <code>null</code>
	 * @return the reservation result dto
	 */
	public ReservationResult reserve(Long eventId, Long seatId);
}
