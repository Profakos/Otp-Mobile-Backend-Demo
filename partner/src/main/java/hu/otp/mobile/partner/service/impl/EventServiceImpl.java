package hu.otp.mobile.partner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.otp.mobile.partner.service.EventService;
import hu.otp.mobile.partner.util.EventJsonParserUtil;
import opt.mobile.common.exceptions.EventException;
import opt.mobile.common.exceptions.MobileError;
import otp.mobile.common.domain.Event;
import otp.mobile.common.domain.EventSeating;

@Service
public class EventServiceImpl implements EventService {

	private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

	@Override
	public EventSeating getEvent(Long eventId) {

		log.info("Reading event seating data, eventId={}", eventId);

		EventSeating eventSeating = EventJsonParserUtil.readEventData(eventId);

		if (eventSeating == null)
			throw new EventException(MobileError.PARTNER_EVENT_DOESNT_EXIST);

		return eventSeating;
	}

	@Override
	public List<Event> getEvents() {

		log.info("Reading event details file");

		return EventJsonParserUtil.readEvents();
	}

}
