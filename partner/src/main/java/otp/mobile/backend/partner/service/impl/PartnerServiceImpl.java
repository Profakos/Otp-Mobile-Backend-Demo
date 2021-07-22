package otp.mobile.backend.partner.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import opt.mobile.backend.common.dto.ErrorMessage;
import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.ReservationResult;
import otp.mobile.backend.common.domain.Seat;
import otp.mobile.backend.partner.service.PartnerService;
import otp.mobile.backend.partner.util.EventJsonParserUtil;

@Service
public class PartnerServiceImpl implements PartnerService {

	@Override
	public EventSeating fetchEvent(int eventId) {

		return EventJsonParserUtil.readEventData(eventId);
	}

	@Override
	public List<Event> fetchEvents() {

		return EventJsonParserUtil.readEvents();
	}

	@Override
	public ReservationResult reserveSeat(int eventId, int seatId) {

		List<Event> events = EventJsonParserUtil.readEvents();

		Optional<Event> eventOpt = events.stream().filter(e -> e.getEventId() == eventId).findFirst();

		ReservationResult result = new ReservationResult();

		if (!eventOpt.isPresent()) {
			result.setErrorCode(ErrorMessage.EVENT_DOESNT_EXIST.getErrorCode());
			result.setSuccess(false);
			return result;
		}

		EventSeating eventSeating = EventJsonParserUtil.readEventData(eventId);

		String formattedSeatId = "S" + seatId;
		Optional<Seat> seatOpt = eventSeating.getSeats().stream().filter(e -> e.getId().equals(formattedSeatId)).findFirst();

		if (!seatOpt.isPresent()) {
			result.setErrorCode(ErrorMessage.SEAT_DOESNT_EXIST.getErrorCode());
			result.setSuccess(false);
			return result;
		}

		Seat seat = seatOpt.get();

		if (seat.getReserved()) {
			result.setErrorCode(ErrorMessage.SEAT_ALREADY_RESERVED.getErrorCode());
			result.setSuccess(false);
			return result;
		}

		long minId = 1;
		long maxId = 1000;
		long generatedId = minId + (long) (Math.random() * (maxId - minId));

		result.setReservationId(generatedId);
		result.setSuccess(true);

		return result;
	}

}
