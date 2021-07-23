package hu.otp.mobile.ticket.service;

import opt.mobile.backend.common.dto.ReservationResult;

public interface ReservationService {

	/**
	 * Attempts to reserve a seat for the specific event, using the specific card and user
	 * 
	 * @param eventId the event ID, not <code>null</code>
	 * @param seatId the seat's ID, not <code>null</code>
	 * @param cardId the card's ID, not <code>null</code>
	 * @param userToken, the encoded user token, not <code>null</code>
	 * @return the reservation result dto
	 */
	public ReservationResult reserve(Long eventId, Long seatId, Long cardId, String userToken);
}
