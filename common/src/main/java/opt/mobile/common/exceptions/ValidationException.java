package opt.mobile.common.exceptions;

public class ValidationException extends MobileBackendException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -6133430319593955812L;

	public ValidationException(MobileErrorMessage errorMessage) {
		super(errorMessage);
	}

}
