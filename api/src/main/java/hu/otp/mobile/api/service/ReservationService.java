package hu.otp.mobile.api.service;

import opt.mobile.common.dto.ReservationSuccessDto;

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
	public ReservationSuccessDto pay(Long eventId, Long seatId, Long cardId, String userToken);
}
