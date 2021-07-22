package otp.mobile.backend.partner.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import otp.mobile.backend.common.domain.EventSeating;
import otp.mobile.backend.common.domain.EventSeatingWrapper;

public final class EventJsonParserUtil {

	private static final Logger log = LoggerFactory.getLogger(EventJsonParserUtil.class);

	public static EventSeating readEventSeating(long id) {

		String path = "event-data/getEvent" + id + ".json";

		log.debug("Reading data from path={}", path);

		String content = readContent(new ClassPathResource(path));

		if (content == null) {
			log.debug("Event content not found");
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			EventSeatingWrapper wrapper = objectMapper.readValue(content, EventSeatingWrapper.class);
			return wrapper.getData();
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

}
