package otp.mobile.backend.common.domain;

public class Event {

	private int eventId;
	private String title;
	private String location;
	private String starTimeStamp;
	private String endTimeStamp;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStarTimeStamp() {
		return starTimeStamp;
	}

	public void setStarTimeStamp(String starTimeStamp) {
		this.starTimeStamp = starTimeStamp;
	}

	public String getEndTimeStamp() {
		return endTimeStamp;
	}

	public void setEndTimeStamp(String endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}

}
