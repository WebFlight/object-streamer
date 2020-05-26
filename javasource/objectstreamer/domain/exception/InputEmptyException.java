package objectstreamer.domain.exception;

public class InputEmptyException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4293874328948544805L;

	public InputEmptyException(ErrorMessage errorMessage) {
		super(errorMessage.getDescription());
	}

}
