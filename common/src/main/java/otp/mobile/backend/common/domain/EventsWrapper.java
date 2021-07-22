package otp.mobile.backend.common.domain;

import java.util.List;

public class EventsWrapper {
	private List<Event> data;
	private Boolean success;

	public List<Event> getData() {
		return data;
	}

	public void setData(List<Event> data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
