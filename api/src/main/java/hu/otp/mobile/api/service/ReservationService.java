package hu.otp.mobile.api.service;

import opt.mobile.backend.common.dto.ReservationResult;

public interface ReservationService {

	public ReservationResult pay(int eventId, int seatId, int cardId);
}
