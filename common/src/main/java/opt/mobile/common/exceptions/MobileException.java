package opt.mobile.common.exceptions;

public abstract class MobileException extends RuntimeException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2000806666773328100L;

	private final MobileError mobileError;

	public MobileException(MobileError mobileError) {
		super();
		this.mobileError = mobileError;
	}

	public MobileError getMobileError() {
		return mobileError;
	}
}
