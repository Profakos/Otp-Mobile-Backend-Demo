package hu.otp.mobile.core.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.otp.mobile.core.domain.User;
import hu.otp.mobile.core.domain.UserBankCard;
import hu.otp.mobile.core.domain.UserDevice;
import hu.otp.mobile.core.dto.TokenDto;
import hu.otp.mobile.core.dto.UserDto;
import hu.otp.mobile.core.repository.UserBankCardRepository;
import hu.otp.mobile.core.repository.UserDeviceRepository;
import hu.otp.mobile.core.repository.UserRepository;
import hu.otp.mobile.core.repository.UserTokenRepository;
import hu.otp.mobile.core.service.ValidationService;
import hu.otp.mobile.core.util.TokenParserUtil;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	UserBankCardRepository userBankCardRepository;
	@Autowired
	UserDeviceRepository userDeviceRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserTokenRepository userTokenRepository;

	private String formatCardId(long cardId) {

		String idString = Long.toString(cardId);

		StringBuilder idStringBuilder = new StringBuilder();

		idStringBuilder.append("C");

		while (idStringBuilder.length() + idString.length() < 5) {
			idStringBuilder.append("0");
		}

		idStringBuilder.append(idString);

		return idStringBuilder.toString();
	}

	private UserDto getAndValidateUser(String userToken) {
		UserDto userDto = new UserDto();
		userDto.setSuccess(false);

		TokenDto tokenDto = TokenParserUtil.decodeToken(userToken);

		if (tokenDto == null)
			return userDto;

		Optional<User> userOpt = userRepository.findByUserId(tokenDto.getUserId());

		if (!userOpt.isPresent())
			return userDto;

		User user = userOpt.get();
		userDto.setUser(user);

		if (!userTokenRepository.findByUserIdAndToken(tokenDto.getUserId(), userToken).isPresent())
			return userDto;

		if (!user.getEmail().equals(tokenDto.getEmail()))
			return userDto;

		Optional<UserDevice> userDeviceOpt = userDeviceRepository.findByUserIdAndDeviceHash(tokenDto.getUserId(), tokenDto.getDeviceHash());

		if (!userDeviceOpt.isPresent())
			return userDto;

		userDto.setSuccess(true);
		return userDto;
	}

	@Override
	public boolean validateCard(String userToken, long cardId, int payment) {

		UserDto userDto = getAndValidateUser(userToken);

		if (!userDto.isSuccess())
			return false;

		String formattedCardId = formatCardId(cardId);

		Optional<UserBankCard> userBankCardOpt = userBankCardRepository.findByUserIdAndCardId(userDto.getUser().getUserId(),
				formattedCardId);

		if (!userBankCardOpt.isPresent())
			return false;

		UserBankCard userBankCard = userBankCardOpt.get();

		if (userBankCard.getAmount() - payment < 0)
			return false;

		return true;
	}

	@Override
	public boolean validateUser(String userToken) {

		UserDto userDto = getAndValidateUser(userToken);

		return userDto.isSuccess();
	}

}
