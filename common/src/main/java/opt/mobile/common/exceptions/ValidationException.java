package opt.mobile.common.exceptions;

public class ValidationException extends MobileException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -6133430319593955812L;

	public ValidationException(MobileError errorMessage) {
		super(errorMessage);
	}

}
