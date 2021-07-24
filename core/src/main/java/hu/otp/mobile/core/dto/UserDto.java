package hu.otp.mobile.core.dto;

import hu.otp.mobile.core.domain.User;

public class UserDto {

	private User user;
	private boolean success;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
