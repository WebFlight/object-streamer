package objectstreamer.domain.exception;

public class InputValidationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4293874328948544805L;

	public InputValidationException(ErrorMessage errorMessage) {
		super(errorMessage.getDescription());
	}

}
