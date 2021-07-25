package otp.mobile.common.domain;

public class EventSeatingWrapper {

	private EventSeating data;
	private Boolean success;

	public EventSeating getData() {
		return data;
	}

	public void setData(EventSeating data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
