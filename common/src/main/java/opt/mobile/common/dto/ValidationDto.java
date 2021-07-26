package opt.mobile.common.dto;

import opt.mobile.common.exceptions.MobileError;

public class ValidationDto {

	private int userId;
	private boolean success;
	private MobileError error;

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

	public MobileError getError() {
		return error;
	}

	public void setError(MobileError error) {
		this.error = error;
	}
}
