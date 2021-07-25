package hu.otp.mobile.ticket.service.impl;

import java.util.Date;
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
import opt.mobile.common.dto.ValidationDto;
import opt.mobile.common.exceptions.EventException;
import opt.mobile.common.exceptions.MobileErrorMessage;
import opt.mobile.common.exceptions.ReservationException;
import opt.mobile.common.exceptions.ValidationException;
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

		log.info("Sending event seating data request to partner module");

		List<Event> events = partnerClient.getEvents();

		Optional<Event> eventOptional = events.stream().filter(e -> e.getEventId() == eventId).findFirst();

		if (!eventOptional.isPresent()) {

			log.warn("Event does not exist");
			throw new EventException(MobileErrorMessage.TICKET_EVENT_DOESNT_EXIST);
		}

		Event event = eventOptional.get();
		String startDate = event.getStartTimeStamp();
		Date eventStartTime = new Date(Long.parseLong(startDate) * 1000);

		if (eventStartTime.before(new Date())) {
			log.warn("Event has already started");
			throw new ReservationException(MobileErrorMessage.TICKET_EVENT_ALREADY_BEGUN);
		}

		EventSeating eventSeating = partnerClient.getEvent(eventId);
		String formattedSeatId = "S" + seatId;
		Optional<Seat> seatOpt = eventSeating.getSeats().stream().filter(e -> e.getId().equals(formattedSeatId)).findFirst();

		if (!seatOpt.isPresent()) {
			log.warn("Seat does not exist");
			throw new EventException(MobileErrorMessage.TICKET_SEAT_DOESNT_EXIST);
		}

		int price = seatOpt.get().getPrice();

		log.info("Sending event seating data request to core module, userToken={}, cardId={}, price={}", userToken, cardId, price);

		ValidationDto cardDto = coreClient.validateCardPayment(userToken, cardId, price);
		if (!cardDto.isSuccess()) {
			log.warn("Failed to validate cards");
			throw new ValidationException(MobileErrorMessage.TICKET_CARD_VALIDATION_FAILED);

		}

		log.info("Sending seat reservation query to partner module");
		return partnerClient.reserve(eventId, seatId);

	}

}
