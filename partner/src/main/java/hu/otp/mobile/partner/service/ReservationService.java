package hu.otp.mobile.partner.service;

import otp.mobile.backend.common.domain.ReservationResult;

public interface ReservationService {

	public ReservationResult reserve(int eventId, int seatId);
}
