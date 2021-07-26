package hu.otp.mobile.ticket.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

	@Test
	void testReserve() {

		long eventId = 1;
		long seatId = 2;
		long cardId = 1;
		int payment = 1000;
		String userToken = "dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJkY2N0MyQkNCRkNGQTMwRkNDQjM2RjcyRENBMjJBODE3";

		List<Event> events = new ArrayList<>();

		// Setting tomorrow
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		Date startDate = c.getTime();

		c.add(Calendar.DATE, 1);
		Date endDate = c.getTime();

		Long startTimeStamp = startDate.getTime() / 1000;
		Long endTimeStamp = endDate.getTime() / 1000;

		Event event = new Event();
		event.setEventId(eventId);
		event.setStartTimeStamp(startTimeStamp.toString());
		event.setEndTimeStamp(endTimeStamp.toString());
		events.add(event);

		EventSeating eventSeating = new EventSeating();
		eventSeating.setEventId(Long.toString(eventId));
		List<Seat> seats = new ArrayList<Seat>();

		Seat seat = new Seat();
		seat.setCurrency("HUF");
		seat.setId("S" + seatId);
		seat.setPrice(payment);
		seat.setReserved(false);
		seats.add(seat);

		eventSeating.setSeats(seats);

		ValidationDto cardValidation = new ValidationDto();
		cardValidation.setSuccess(true);

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
}
