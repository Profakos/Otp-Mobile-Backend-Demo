package hu.otp.mobile.partner.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.otp.mobile.partner.service.ReservationService;
import hu.otp.mobile.partner.util.EventJsonParserUtil;
import opt.mobile.backend.common.dto.ErrorMessage;
import opt.mobile.backend.common.dto.ReservationResult;
import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.Seat;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	public ReservationResult reserve(Long eventId, Long seatId) {

		log.info("Reading event details file to check if event exists, eventId={}", eventId);

		List<Event> events = EventJsonParserUtil.readEvents();

		Optional<Event> eventOpt = events.stream().filter(e -> e.getEventId() == eventId).findFirst();

		ReservationResult result = new ReservationResult();

		if (!eventOpt.isPresent()) {
			log.warn("Event does not exist");
			result.setErrorCode(ErrorMessage.EVENT_DOESNT_EXIST.getErrorCode());
			result.setSuccess(false);
			return result;
		}

		log.info("Reading event seating data to check if seating exists, seatId={}", seatId);

		EventSeating eventSeating = EventJsonParserUtil.readEventData(eventId);

		String formattedSeatId = "S" + seatId;
		Optional<Seat> seatOpt = eventSeating.getSeats().stream().filter(e -> e.getId().equals(formattedSeatId)).findFirst();

		if (!seatOpt.isPresent()) {
			log.warn("Seat does not exist");
			result.setErrorCode(ErrorMessage.SEAT_DOESNT_EXIST.getErrorCode());
			result.setSuccess(false);
			return result;
		}

		Seat seat = seatOpt.get();

		if (seat.getReserved()) {
			log.warn("Seat is already reserved");
			result.setErrorCode(ErrorMessage.SEAT_ALREADY_RESERVED.getErrorCode());
			result.setSuccess(false);
			return result;
		}

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
