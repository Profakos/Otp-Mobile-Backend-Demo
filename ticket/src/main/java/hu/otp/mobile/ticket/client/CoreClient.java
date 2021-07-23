package hu.otp.mobile.ticket.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CoreClient {

	@Value("${rest.url.core}")
	private String coreUrl;

	public boolean validateCard(String userToken, long cardId, int payment) {

		String url = coreUrl + "/validate-card";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("user-token", userToken)
				.queryParam("card-id", cardId).queryParam("payment", payment);

		HttpEntity<Boolean> response = restTemplate.getForEntity(builder.build().encode().toUri(), Boolean.class);

		return response.getBody();

	}
}
