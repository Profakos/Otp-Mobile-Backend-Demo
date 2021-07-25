package hu.otp.mobile.partner.service;

import opt.mobile.common.dto.ReservationSuccessDto;

public interface ReservationService {

	/**
	 * Attempts to reserve a seat for the specific event
	 * 
	 * @param eventId the event ID, not <code>null</code>
	 * @param seatId the seat's ID, not <code>null</code>
	 * @return the reservation result dto
	 */
	public ReservationSuccessDto reserve(Long eventId, Long seatId);
}
