package hu.otp.mobile.ticket.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.ticket.client.CoreClient;
import hu.otp.mobile.ticket.client.PartnerClient;
import hu.otp.mobile.ticket.service.ReservationService;
import opt.mobile.backend.common.dto.ReservationResult;
import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.Seat;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	CoreClient coreClient;
	@Autowired
	PartnerClient partnerClient;

	@Override
	public ReservationResult reserve(Long eventId, Long seatId, Long cardId, String userToken) {

		EventSeating eventSeating = partnerClient.getEvent(eventId);
		String formattedSeatId = "S" + seatId;
		Optional<Seat> seatOpt = eventSeating.getSeats().stream().filter(e -> e.getId().equals(formattedSeatId)).findFirst();

		if (seatOpt.isPresent()) {
			int price = seatOpt.get().getPrice();
			if (!coreClient.validateCard(userToken, cardId, price))
				return null;

			return partnerClient.reserve(eventId, seatId);
		}

		return null;
	}

}
