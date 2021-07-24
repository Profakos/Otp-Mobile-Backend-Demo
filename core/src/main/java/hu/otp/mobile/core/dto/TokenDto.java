package hu.otp.mobile.core.dto;

public class TokenDto {

	private int userId;
	private String email;
	private String deviceHash;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeviceHash() {
		return deviceHash;
	}

	public void setDeviceHash(String deviceHash) {
		this.deviceHash = deviceHash;
	}
}
