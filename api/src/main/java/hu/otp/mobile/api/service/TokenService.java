package hu.otp.mobile.api.service;

public interface TokenService {

	/**
	 * Checks if the user-token represents a valid user in the database
	 * 
	 * @param userToken, the encoded user token
	 * @return boolean representing if the token is valid
	 */
	public boolean validateUser(String userToken);

}
