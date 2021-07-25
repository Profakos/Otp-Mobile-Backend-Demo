package hu.otp.mobile.core.service;

import opt.mobile.common.dto.ValidationDto;

public interface ValidationService {

	public ValidationDto validateCard(String userToken, long cardId, int payment);

	public ValidationDto validateUser(String userToken);
}
