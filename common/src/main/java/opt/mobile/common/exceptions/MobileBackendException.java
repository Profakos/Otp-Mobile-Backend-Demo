package opt.mobile.common.exceptions;

public abstract class MobileBackendException extends RuntimeException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2000806666773328100L;

	private final MobileErrorMessage errorMessage;

	public MobileBackendException(MobileErrorMessage errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public MobileErrorMessage getMobileErrorMessage() {
		return errorMessage;
	}
}
