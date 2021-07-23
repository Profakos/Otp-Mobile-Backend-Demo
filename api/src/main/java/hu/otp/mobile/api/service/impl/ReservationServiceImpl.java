package hu.otp.mobile.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.api.client.TicketClient;
import hu.otp.mobile.api.service.ReservationService;
import opt.mobile.backend.common.dto.ReservationResult;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	TicketClient ticketClient;

	@Override
	public ReservationResult pay(Long eventId, Long seatId, Long cardId, String userToken) {
		return ticketClient.reserve(userToken, eventId, seatId, cardId);
	}

}
