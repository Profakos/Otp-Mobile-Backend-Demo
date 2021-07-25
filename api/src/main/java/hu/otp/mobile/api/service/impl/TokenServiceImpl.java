package hu.otp.mobile.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.api.client.CoreClient;
import hu.otp.mobile.api.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	private static Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Autowired
	CoreClient coreClient;

	@Override
	public boolean validateUser(String userToken) {

		log.info("Sending user validation query to core module");

		boolean result = coreClient.validateUser(userToken);

		if (!result) {
			log.info("User authentication failed");

		} else {
			log.info("User authentication successful");

		}

		return result;
	}

}
