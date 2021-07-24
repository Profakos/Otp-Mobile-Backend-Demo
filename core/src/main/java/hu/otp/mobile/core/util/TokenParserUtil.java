package hu.otp.mobile.core.util;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.otp.mobile.core.dto.TokenDto;

public final class TokenParserUtil {

	private static final Logger log = LoggerFactory.getLogger(TokenParserUtil.class);

	public static TokenDto decodeToken(String userToken) {

		log.debug("Parsing token, userToken={}", userToken);

		String decodedToken;

		try {
			decodedToken = new String(Base64.getDecoder().decode(userToken));
		} catch (IllegalArgumentException e) {
			return null;
		}

		String[] splitToken = decodedToken.split("&");

		if (splitToken.length != 3)
			return null;

		TokenDto tokenDto = new TokenDto();
		tokenDto.setEmail(splitToken[0]);

		int userId;
		try {
			userId = Integer.parseInt(splitToken[1]);
		} catch (NumberFormatException e) {
			userId = -1000;
		}

		tokenDto.setUserId(userId);
		tokenDto.setDeviceHash(splitToken[2]);

		return tokenDto;
	}

}
