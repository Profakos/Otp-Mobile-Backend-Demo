package hu.otp.mobile.api.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import opt.mobile.common.dto.ReservationSuccessDto;
import otp.mobile.common.domain.Event;
import otp.mobile.common.domain.EventSeating;

@Component
public class TicketClient {

	@Value("${rest.url.ticket}")
	private String ticketUrl;

	public EventSeating getEvent(Long eventId) {

		String url = ticketUrl + "/getEvent";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("eventId", eventId);

		HttpEntity<EventSeating> response = restTemplate.getForEntity(builder.build().encode().toUri(), EventSeating.class);

		return response.getBody();

	}

	public List<Event> getEvents() {

		String url = ticketUrl + "/getEvents";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<Event[]> response = restTemplate.getForEntity(builder.build().encode().toUri(), Event[].class);

		return Arrays.asList(response.getBody());
	}

	public ReservationSuccessDto reserve(String userToken, Long eventId, Long seatId, Long cardId) {

		String url = ticketUrl + "/reserve";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("User-Token", userToken)
				.queryParam("eventId", eventId).queryParam("seatId", seatId).queryParam("cardId", cardId);

		HttpEntity<ReservationSuccessDto> response = restTemplate.postForEntity(builder.build().encode().toUri(), null,
				ReservationSuccessDto.class);

		return response.getBody();
	}

}
