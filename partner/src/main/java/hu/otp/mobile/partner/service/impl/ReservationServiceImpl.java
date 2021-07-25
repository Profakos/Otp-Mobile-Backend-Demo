package hu.otp.mobile.partner.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.otp.mobile.partner.service.ReservationService;
import hu.otp.mobile.partner.util.EventJsonParserUtil;
import opt.mobile.common.dto.ReservationSuccessDto;
import opt.mobile.common.exceptions.MobileErrorMessage;
import opt.mobile.common.exceptions.ReservationException;
import otp.mobile.common.domain.Event;
import otp.mobile.common.domain.EventSeating;
import otp.mobile.common.domain.Seat;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	public ReservationSuccessDto reserve(Long eventId, Long seatId) {

		log.info("Reading event details file to check if event exists, eventId={}", eventId);

		List<Event> events = EventJsonParserUtil.readEvents();

		Optional<Event> eventOpt = events.stream().filter(e -> e.getEventId() == eventId).findFirst();

		if (!eventOpt.isPresent()) {
			log.warn("Event does not exist");
			throw new ReservationException(MobileErrorMessage.PARTNER_EVENT_DOESNT_EXIST);

		}

		log.info("Reading event seating data to check if seating exists, seatId={}", seatId);

		EventSeating eventSeating = EventJsonParserUtil.readEventData(eventId);

		String formattedSeatId = "S" + seatId;
		Optional<Seat> seatOpt = eventSeating.getSeats().stream().filter(e -> e.getId().equals(formattedSeatId)).findFirst();

		if (!seatOpt.isPresent()) {
			log.warn("Seat does not exist");
			throw new ReservationException(MobileErrorMessage.PARTNER_SEAT_DOESNT_EXIST);
		}

		Seat seat = seatOpt.get();

		if (seat.getReserved()) {
			log.warn("Seat is already reserved");
			throw new ReservationException(MobileErrorMessage.PARTNER_SEAT_ALREADY_RESERVED);
		}

		ReservationSuccessDto result = new ReservationSuccessDto();
		log.info("Reserving seating");
		long minId = 1;
		long maxId = 1000;
		long generatedId = minId + (long) (Math.random() * (maxId - minId));

		result.setReservationId(generatedId);
		result.setSuccess(true);

		log.info("Seat succesfully reserved");

		return result;
	}

}
