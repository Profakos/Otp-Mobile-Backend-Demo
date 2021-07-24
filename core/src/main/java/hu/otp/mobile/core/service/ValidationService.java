package hu.otp.mobile.core.service;

public interface ValidationService {

	public boolean validateCard(String userToken, long cardId, int payment);

	public boolean validateUser(String userToken);
}
