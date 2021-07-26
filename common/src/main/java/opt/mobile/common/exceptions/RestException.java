package opt.mobile.common.exceptions;

public class RestException extends MobileException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -6133430319593955812L;

	public RestException(MobileError errorMessage) {
		super(errorMessage);
	}

}
