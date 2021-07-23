package hu.otp.mobile.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import hu.otp.mobile.api.service.ApiService;
import otp.mobile.backend.common.domain.Event;
import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.ReservationResult;

@Service
public class ApiServiceImpl implements ApiService {

	@Override
	public EventSeating getEvent(int eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservationResult pay(int eventId, int seatId, int cardId) {
		// TODO Auto-generated method stub
		return null;
	}

}
