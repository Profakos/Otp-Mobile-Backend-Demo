package hu.otp.mobile.ticket.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import hu.otp.mobile.ticket.client.CoreClient;
import hu.otp.mobile.ticket.client.PartnerClient;
import hu.otp.mobile.ticket.service.ReservationService;
import hu.otp.mobile.ticket.service.impl.ReservationServiceImpl;
import opt.mobile.common.dto.ReservationSuccessDto;
import opt.mobile.common.dto.ValidationDto;
import opt.mobile.common.exceptions.EventException;
import opt.mobile.common.exceptions.MobileError;
import opt.mobile.common.exceptions.ReservationException;
import opt.mobile.common.exceptions.ValidationException;
import otp.mobile.common.domain.Event;
import otp.mobile.common.domain.EventSeating;
import otp.mobile.common.domain.Seat;

@SpringBootTest
public class ReservationServiceTest {

	@Mock
	private CoreClient coreClient;
	@Mock
	private PartnerClient partnerClient;

	@InjectMocks
	private ReservationService reservationService = new ReservationServiceImpl();

	private final long eventId = 1;
	private final long seatId = 2;
	private final long cardId = 2;
	private final int payment = 1000;
	private final String userToken = "dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJkY2N0MyQkNCRkNGQTMwRkNDQjM2RjcyRENBMjJBODE3";

	private ValidationDto createValidation(boolean success) {
		ValidationDto cardValidation = new ValidationDto();
		cardValidation.setSuccess(success);
		return cardValidation;
	}

	private Event createEvent(long eventId, int dayFromToday) {
		// today + dayFromToday
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, dayFromToday);
		Date startDate = c.getTime();

		// start date + 1
		c.add(Calendar.DATE, 1);
		Date endDate = c.getTime();

		Long startTimeStamp = startDate.getTime() / 1000;
		Long endTimeStamp = endDate.getTime() / 1000;

		Event event = new Event();
		event.setEventId(eventId);
		event.setStartTimeStamp(startTimeStamp.toString());
		event.setEndTimeStamp(endTimeStamp.toString());
		return event;
	}

	private EventSeating createEventSeating(long eventId, long seatId, boolean reserved) {
		EventSeating eventSeating = new EventSeating();
		eventSeating.setEventId(Long.toString(eventId));
		List<Seat> seats = new ArrayList<Seat>();

		Seat seat = new Seat();
		seat.setCurrency("HUF");
		seat.setId("S" + seatId);
		seat.setPrice(payment);
		seat.setReserved(reserved);
		seats.add(seat);

		eventSeating.setSeats(seats);
		return eventSeating;
	}

	private EventSeating createEmptyEventSeating(long eventId) {
		EventSeating eventSeating = new EventSeating();
		eventSeating.setEventId(Long.toString(eventId));
		List<Seat> seats = new ArrayList<Seat>();
		eventSeating.setSeats(seats);
		return eventSeating;
	}

	@Test
	void testReserve_success() {

		List<Event> events = new ArrayList<>();

		Event event = createEvent(eventId, 1);
		events.add(event);

		EventSeating eventSeating = createEventSeating(eventId, seatId, false);

		ValidationDto cardValidation = createValidation(true);

		ReservationSuccessDto expectedDto = new ReservationSuccessDto();
		expectedDto.setSuccess(true);

		// Mock

		Mockito.when(coreClient.validateCardPayment(userToken, cardId, payment)).thenReturn(cardValidation);
		Mockito.when(partnerClient.getEvents()).thenReturn(events);
		Mockito.when(partnerClient.getEvent(eventId)).thenReturn(eventSeating);
		Mockito.when(partnerClient.reserve(eventId, seatId)).thenReturn(expectedDto);

		// Verfication

		ReservationSuccessDto returnDto = reservationService.reserve(eventId, seatId, cardId, userToken);

		assertEquals(returnDto, expectedDto);
	}

	@Test
	void TestReserve_no_events() {

		ValidationDto cardValidation = new ValidationDto();
		cardValidation.setSuccess(true);

		// Mock
		Mockito.when(coreClient.validateCardPayment(userToken, cardId, payment)).thenReturn(cardValidation);
		Mockito.when(partnerClient.getEvents()).thenReturn(new ArrayList<Event>());

		// Verfication

		EventException e = assertThrows(EventException.class, () -> reservationService.reserve(eventId, seatId, cardId, userToken));

		assertEquals(MobileError.TICKET_EVENT_DOESNT_EXIST.getErrorCode(), e.getMobileError().getErrorCode());
	}

	@Test
	void TestReserve_no_event_seating() {

		List<Event> events = new ArrayList<>();
		Event event = createEvent(eventId, 1);
		events.add(event);

		ValidationDto cardValidation = new ValidationDto();
		cardValidation.setSuccess(true);

		// Mock
		Mockito.when(coreClient.validateCardPayment(userToken, cardId, payment)).thenReturn(cardValidation);
		Mockito.when(partnerClient.getEvents()).thenReturn(events);

		// Verfication

		EventException e = assertThrows(EventException.class, () -> reservationService.reserve(eventId, seatId, cardId, userToken));

		assertEquals(MobileError.TICKET_EVENT_DOESNT_EXIST.getErrorCode(), e.getMobileError().getErrorCode());
	}

	@Test
	void TestReserve_event_begun() {
		List<Event> events = new ArrayList<>();
		Event event = createEvent(eventId, -1);
		events.add(event);

		ValidationDto cardValidation = new ValidationDto();
		cardValidation.setSuccess(true);

		// Mock
		Mockito.when(coreClient.validateCardPayment(userToken, cardId, payment)).thenReturn(cardValidation);
		Mockito.when(partnerClient.getEvents()).thenReturn(events);

		// Verfication

		ReservationException e = assertThrows(ReservationException.class,
				() -> reservationService.reserve(eventId, seatId, cardId, userToken));

		assertEquals(MobileError.TICKET_EVENT_ALREADY_BEGUN.getErrorCode(), e.getMobileError().getErrorCode());
	}

	@Test
	void testReserve_seat_doesnt_exist() {

		List<Event> events = new ArrayList<>();

		Event event = createEvent(eventId, 1);
		events.add(event);

		EventSeating eventSeating = createEmptyEventSeating(eventId);

		ValidationDto cardValidation = createValidation(true);

		ReservationSuccessDto expectedDto = new ReservationSuccessDto();
		expectedDto.setSuccess(true);

		// Mock

		Mockito.when(coreClient.validateCardPayment(userToken, cardId, payment)).thenReturn(cardValidation);
		Mockito.when(partnerClient.getEvents()).thenReturn(events);
		Mockito.when(partnerClient.getEvent(eventId)).thenReturn(eventSeating);

		// Verfication

		EventException e = assertThrows(EventException.class, () -> reservationService.reserve(eventId, seatId, cardId, userToken));

		assertEquals(MobileError.TICKET_SEAT_DOESNT_EXIST.getErrorCode(), e.getMobileError().getErrorCode());

	}

	@Test
	void testReserve_seat_reserved() {

		List<Event> events = new ArrayList<>();

		Event event = createEvent(eventId, 1);
		events.add(event);

		EventSeating eventSeating = createEventSeating(eventId, seatId, false);

		ValidationDto cardValidation = createValidation(false);

		ReservationSuccessDto expectedDto = new ReservationSuccessDto();
		expectedDto.setSuccess(true);

		// Mock

		Mockito.when(coreClient.validateCardPayment(userToken, cardId, payment)).thenReturn(cardValidation);
		Mockito.when(partnerClient.getEvents()).thenReturn(events);
		Mockito.when(partnerClient.getEvent(eventId)).thenReturn(eventSeating);

		// Verfication

		ValidationException e = assertThrows(ValidationException.class,
				() -> reservationService.reserve(eventId, seatId, cardId, userToken));

		assertEquals(MobileError.TICKET_CARD_VALIDATION_FAILED.getErrorCode(), e.getMobileError().getErrorCode());

	}
}
