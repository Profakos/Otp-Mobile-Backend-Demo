package opt.mobile.backend.common.dto;

public enum ErrorMessage {

	/** Event does not exist */
	EVENT_DOESNT_EXIST("Nem létezik ilyen esemény!", 90001),

	/** Seat does not exist */
	SEAT_DOESNT_EXIST("Nem létezik ilyen szék!", 90002),

	/** Seat is already reserved */
	SEAT_ALREADY_RESERVED("Már lefoglalt székre nem lehet jegyet eladni!", 90010);

	private String label;
	private int errorCode;

	private ErrorMessage(String label, int errorCode) {

		this.label = label;
		this.errorCode = errorCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
