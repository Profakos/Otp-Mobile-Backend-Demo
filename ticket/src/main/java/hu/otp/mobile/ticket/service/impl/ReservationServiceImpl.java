package hu.otp.mobile.ticket.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.ticket.client.CoreClient;
import hu.otp.mobile.ticket.client.PartnerClient;
import hu.otp.mobile.ticket.service.ReservationService;
import opt.mobile.backend.common.dto.ReservationResult;
import otp.mobile.backend.common.domain.Event;
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

		List<Event> events = partnerClient.getEvents();

		Optional<Event> eventOptional = events.stream().filter(e -> e.getEventId() == eventId).findFirst();

		if (!eventOptional.isPresent())
			return null;

		Event event = eventOptional.get();
		String startDate = event.getStartTimeStamp();

		Date eventStartTime = new Date(Long.parseLong(startDate) * 1000);
		if (eventStartTime.before(new Date())) {
			return null;
		}

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
