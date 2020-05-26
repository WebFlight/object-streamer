package objectstreamer.domain.exception;

public enum ErrorMessage {

	INPUT_PARAMETER_FILE("Please specify a FileDocument (or specialization) object as input parameter. Parameter appears to be empty."),
	INPUT_PARAMETER_MICROFLOW("Please specify a microflow as input parameter. Parameter appears to be empty."),
	INPUT_PARAMETER_BATCHSIZE("Please specify a batch size as input parameter. Parameter appears to be empty.");

	private final String description;

	private ErrorMessage(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


	@Override
	public String toString() {
		return description;
	}

}
