package opt.mobile.common.exceptions;

public class ReservationException extends MobileBackendException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -7204479625750148565L;

	public ReservationException(MobileErrorMessage errorMessage) {
		super(errorMessage);
	}

}
