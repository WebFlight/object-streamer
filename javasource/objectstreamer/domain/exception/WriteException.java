package objectstreamer.domain.exception;

public class WriteException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4293874328948544805L;

	public WriteException(ErrorMessage errorMessage) {
		super(errorMessage.getDescription());
	}

}
