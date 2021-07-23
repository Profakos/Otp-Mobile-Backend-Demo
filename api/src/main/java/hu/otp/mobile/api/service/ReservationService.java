package hu.otp.mobile.api.service;

import otp.mobile.backend.common.domain.ReservationResult;

public interface ReservationService {

	public ReservationResult pay(int eventId, int seatId, int cardId);
}
