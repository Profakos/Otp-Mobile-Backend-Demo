package hu.otp.mobile.partner.service;

import opt.mobile.backend.common.dto.ReservationResult;

public interface ReservationService {

	public ReservationResult reserve(int eventId, int seatId);
}
