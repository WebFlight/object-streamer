package objectstreamer.domain.exception;

public class WriteException extends RuntimeException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9011016937375848485L;

	public WriteException(ErrorMessage errorMessage) {
		super(errorMessage.getDescription());
	}
	
	public WriteException(ErrorMessage errorMessage, Throwable cause) {
		super(errorMessage.getDescription(), cause);
	}

}
