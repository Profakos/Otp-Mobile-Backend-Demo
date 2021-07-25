package hu.otp.mobile.ticket.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.ticket.client.CoreClient;
import hu.otp.mobile.ticket.client.PartnerClient;
import hu.otp.mobile.ticket.service.ReservationService;
import opt.mobile.common.dto.ReservationSuccessDto;
import otp.mobile.common.domain.Event;
import otp.mobile.common.domain.EventSeating;
import otp.mobile.common.domain.Seat;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	CoreClient coreClient;
	@Autowired
	PartnerClient partnerClient;

	@Override
	public ReservationSuccessDto reserve(Long eventId, Long seatId, Long cardId, String userToken) {

		log.info("Sending event seating data query to partner module");

		List<Event> events = partnerClient.getEvents();

		Optional<Event> eventOptional = events.stream().filter(e -> e.getEventId() == eventId).findFirst();

		if (!eventOptional.isPresent()) {

			log.warn("Event does not exist");
			return null;
		}

		Event event = eventOptional.get();
		String startDate = event.getStartTimeStamp();

		// Date eventStartTime = new Date(Long.parseLong(startDate) * 1000);
		// if (eventStartTime.before(new Date())) {
		// log.warn("Event has already started");
		// return null;
		// }

		EventSeating eventSeating = partnerClient.getEvent(eventId);
		String formattedSeatId = "S" + seatId;
		Optional<Seat> seatOpt = eventSeating.getSeats().stream().filter(e -> e.getId().equals(formattedSeatId)).findFirst();

		if (!seatOpt.isPresent()) {
			log.warn("Seat does not exist");
			return null;
		}
		int price = seatOpt.get().getPrice();
		if (!coreClient.validateCard(userToken, cardId, price)) {
			log.warn("Failed to validate cards");
			return null;

		}

		log.info("Sending seat reservation query to partner module");
		return partnerClient.reserve(eventId, seatId);

	}

}
