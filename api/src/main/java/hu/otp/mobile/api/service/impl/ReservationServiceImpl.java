package hu.otp.mobile.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.api.client.TicketClient;
import hu.otp.mobile.api.service.ReservationService;
import opt.mobile.common.dto.ReservationResult;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	TicketClient ticketClient;

	@Override
	public ReservationResult pay(Long eventId, Long seatId, Long cardId, String userToken) {

		log.info("Sending reservation query to ticket module");

		return ticketClient.reserve(userToken, eventId, seatId, cardId);
	}

}
