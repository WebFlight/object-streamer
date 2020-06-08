package objectstreamer.domain.exception;

public enum ErrorMessage {

	INPUT_PARAMETER_FILE_EMPTY("Please specify a FileDocument (or specialization) object as input parameter. Parameter appears to be empty."),
	INPUT_PARAMETER_MICROFLOW_EMPTY("Please specify a microflow as input parameter. Parameter appears to be empty."),
	INPUT_PARAMETER_BATCHSIZE_EMPTY("Please specify a batch size as input parameter. Parameter appears to be empty."),
	INPUT_PARAMETER_BATCHSIZE_RANGE("Please specify a batch size as input parameter that is larger than 0."),
	INPUT_PARAMETER_CONTEXT_EMPTY("Mendix IContext is incorrectly stored in StreamObjectConfiguration. Parameter appears to be empty."),
	WRITE("Exception while writing data to stream.");

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
