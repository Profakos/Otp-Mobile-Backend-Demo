package hu.otp.mobile.api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import opt.mobile.common.dto.ValidationDto;

@Component
public class CoreClient {

	@Value("${rest.url.core}")
	private String coreUrl;

	public ValidationDto validateUser(String userToken) {

		String url = coreUrl + "/validate-user";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("user-token", userToken);

		HttpEntity<ValidationDto> response = restTemplate.getForEntity(builder.build().encode().toUri(), ValidationDto.class);

		return response.getBody();

	}
}
