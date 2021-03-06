package hu.otp.mobile.partner.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import otp.mobile.common.domain.Event;
import otp.mobile.common.domain.EventSeating;
import otp.mobile.common.domain.EventSeatingWrapper;
import otp.mobile.common.domain.EventsWrapper;

public final class EventJsonParserUtil {

	private EventJsonParserUtil() {
		throw new IllegalStateException("Utility class");
	}

	private static final Logger log = LoggerFactory.getLogger(EventJsonParserUtil.class);

	private static <T> T parseContent(String content, Class<T> type) {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			return objectMapper.readValue(content, type);
		} catch (IOException e) {
			log.debug("Failed to parse content value");
			return null;
		}

	}

	private static String readContent(Resource resource) {
		String content;

		try (Reader reader = new InputStreamReader(resource.getInputStream(), "UTF-8")) {
			log.debug("Reading resource");

			content = FileCopyUtils.copyToString(reader);
		} catch (IOException e) {
			return null;
		}

		return content;
	}

	public static EventSeating readEventData(long id) {

		String path = "event-data/getEvent" + id + ".json";

		log.debug("Reading data from path={}", path);

		String content = readContent(new ClassPathResource(path));

		if (content == null) {
			log.warn("Event content not found");
			return null;
		}

		EventSeatingWrapper wrapper = parseContent(content, EventSeatingWrapper.class);

		if (wrapper == null) {
			log.warn("Failed to parse content");
			return null;
		}

		return wrapper.getData();

	}

	public static List<Event> readEvents() {

		String path = "event-data/getEvents.json";

		log.debug("Reading data from path={}", path);

		String content = readContent(new ClassPathResource(path));

		if (content == null) {
			log.debug("Event content not found");
			return new ArrayList<>();
		}

		EventsWrapper wrapper = parseContent(content, EventsWrapper.class);

		if (wrapper == null) {
			log.warn("Failed to parse content");
			return new ArrayList<>();
		}

		return wrapper.getData();
	}

}
