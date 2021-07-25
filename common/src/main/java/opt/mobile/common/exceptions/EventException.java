package opt.mobile.common.exceptions;

public class EventException extends MobileBackendException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -4962404574192459845L;

	public EventException(MobileErrorMessage errorMessage) {
		super(errorMessage);
	}

}
