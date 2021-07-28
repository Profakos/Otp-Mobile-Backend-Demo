package hu.otp.mobile.ticket.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import hu.otp.mobile.ticket.util.SslUtil;
import opt.mobile.common.dto.ReservationSuccessDto;
import opt.mobile.common.exceptions.CustomTextException;
import opt.mobile.common.exceptions.MobileError;
import opt.mobile.common.exceptions.RestException;
import otp.mobile.common.domain.Event;
import otp.mobile.common.domain.EventSeating;

@Component
public class PartnerClient {

	private final Logger log = LoggerFactory.getLogger(PartnerClient.class);

	private final String sslFailureMessage = "Failed to create ssl rest template";

	@Value("${rest.url.partner}")
	private String partnerUrl;

	@Autowired
	private SslUtil sslUtil;

	public EventSeating getEvent(Long eventId) {

		String url = partnerUrl + "/getEvent";

		RestTemplate restTemplate;
		try {
			restTemplate = sslUtil.createSslRestemplate();
		} catch (Exception e) {
			log.warn(sslFailureMessage);
			return null;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("eventId", eventId);
		HttpEntity<EventSeating> response;

		try {
			response = restTemplate.getForEntity(builder.build().encode().toUri(), EventSeating.class);
		} catch (ResourceAccessException e) {
			throw new RestException(MobileError.TICKET_EXTERNAL_SYSTEM_UNAVAILABLE);
		} catch (RestClientException e) {
			throw new CustomTextException(e.getMessage());
		}

		return response.getBody();

	}

	public List<Event> getEvents() {

		String url = partnerUrl + "/getEvents";

		RestTemplate restTemplate;
		try {
			restTemplate = sslUtil.createSslRestemplate();
		} catch (Exception e) {
			log.warn(sslFailureMessage);
			return new ArrayList<>();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<Event[]> response;

		try {
			response = restTemplate.getForEntity(builder.build().encode().toUri(), Event[].class);
		} catch (ResourceAccessException e) {
			throw new RestException(MobileError.TICKET_EXTERNAL_SYSTEM_UNAVAILABLE);
		} catch (RestClientException e) {
			throw new CustomTextException(e.getMessage());
		}

		return Arrays.asList(response.getBody());
	}

	public ReservationSuccessDto reserve(Long eventId, Long seatId) {

		String url = partnerUrl + "/reserve";

		RestTemplate restTemplate;
		try {
			restTemplate = sslUtil.createSslRestemplate();
		} catch (Exception e) {
			log.warn(sslFailureMessage);
			return null;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("eventId", eventId).queryParam("seatId", seatId);

		HttpEntity<ReservationSuccessDto> response;

		try {
			response = restTemplate.postForEntity(builder.build().encode().toUri(), null, ReservationSuccessDto.class);
		} catch (ResourceAccessException e) {
			throw new RestException(MobileError.TICKET_EXTERNAL_SYSTEM_UNAVAILABLE);
		} catch (RestClientException e) {
			throw new CustomTextException(e.getMessage());
		}

		return response.getBody();
	}

}
