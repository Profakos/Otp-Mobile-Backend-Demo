package opt.mobile.common.dto;

import opt.mobile.common.exceptions.MobileErrorMessage;

public class ValidationDto {

	private int userId;
	private boolean success;
	private MobileErrorMessage error;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public MobileErrorMessage getError() {
		return error;
	}

	public void setError(MobileErrorMessage error) {
		this.error = error;
	}
}
