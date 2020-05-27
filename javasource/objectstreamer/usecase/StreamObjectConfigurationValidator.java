package objectstreamer.usecase;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.exception.ErrorMessage;
import objectstreamer.domain.exception.InputValidationException;

public class StreamObjectConfigurationValidator {

	public void validate(StreamObjectConfigurationHttp streamObjectConfiguration) {
		validateBatchSize(streamObjectConfiguration);
		validateMicroflow(streamObjectConfiguration);
		validateContext(streamObjectConfiguration);
	}
	
	public void validate(StreamObjectConfigurationFile streamObjectConfiguration) {
		validateBatchSize(streamObjectConfiguration);
		validateMicroflow(streamObjectConfiguration);
		validateContext(streamObjectConfiguration);
		validateFile(streamObjectConfiguration);
	}
	
	private void validateBatchSize(StreamObjectConfiguration streamObjectConfiguration) {
		Long batchSize = ((StreamObjectConfigurationImpl) streamObjectConfiguration).getBatchSize();
		validateBatchSizeEmpty(batchSize);
		validateBatchSizeRange(batchSize);
	}
	
	private void validateBatchSizeEmpty(Long batchSize) {
		validateEmptyValue(batchSize, ErrorMessage.INPUT_PARAMETER_BATCHSIZE_EMPTY);
	}
	
	private void validateBatchSizeRange(Long batchSize) {
		if (batchSize <= 0L) {
			throw new InputValidationException(ErrorMessage.INPUT_PARAMETER_BATCHSIZE_RANGE);
		}
	}
	
	private void validateMicroflow(StreamObjectConfiguration streamObjectConfiguration) {
		String microflow = ((StreamObjectConfigurationImpl) streamObjectConfiguration).getMicroflow();
		validateEmptyValue(microflow, ErrorMessage.INPUT_PARAMETER_MICROFLOW_EMPTY);
	}
	
	private void validateContext(StreamObjectConfiguration streamObjectConfiguration) {
		IContext context = ((StreamObjectConfigurationImpl) streamObjectConfiguration).getContext();
		validateEmptyValue(context, ErrorMessage.INPUT_PARAMETER_CONTEXT_EMPTY);
	}
	
	private void validateFile(StreamObjectConfigurationFile streamObjectConfiguration) {
		IMendixObject file = ((StreamObjectConfigurationFileImpl) streamObjectConfiguration).getFile();
		validateEmptyValue(file, ErrorMessage.INPUT_PARAMETER_FILE_EMPTY);
	}
	
	private void validateEmptyValue(Object object, ErrorMessage errorMessage) {
		if (object == null) {
			throw new InputValidationException(errorMessage);
		}
	}

}
