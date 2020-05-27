package test.objectstreamer.usecase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.exception.InputValidationException;
import objectstreamer.domain.port.ActionExecutor;
import objectstreamer.domain.port.FileStreamWriter;
import objectstreamer.usecase.StreamObjectConfigurationFileImpl;
import objectstreamer.usecase.StreamObjectConfigurationHttpImpl;
import objectstreamer.usecase.StreamObjectConfigurationValidator;

@ExtendWith(MockitoExtension.class)
class TestStreamObjectConfigurationValidator {
	
	private StreamObjectConfigurationFileImpl streamObjectConfigurationFile;
	private StreamObjectConfigurationHttpImpl streamObjectConfigurationHttp;
	private StreamObjectConfigurationValidator streamObjectConfigurationValidator = new StreamObjectConfigurationValidator();
	
	@Mock
	private FileStreamWriter fileStreamWriter;
	@Mock
	private ActionExecutor<Void> actionExecutor;
	@Mock
	private IContext context;
	@Mock
	private IMendixObject file;
	
	@BeforeEach
	void setupEach() {
		System.out.println(file==null);
		streamObjectConfigurationFile = new StreamObjectConfigurationFileImpl(fileStreamWriter, actionExecutor);
		streamObjectConfigurationFile.setBatchSize(Long.valueOf(100L));
		streamObjectConfigurationFile.setContext(context);
		streamObjectConfigurationFile.setFile(file);
		streamObjectConfigurationFile.setMicroflow("A microflow");
		
		streamObjectConfigurationHttp = new StreamObjectConfigurationHttpImpl();
		streamObjectConfigurationHttp.setBatchSize(Long.valueOf(100L));
		streamObjectConfigurationHttp.setContext(context);
		streamObjectConfigurationHttp.setMicroflow("A microflow");
	}

	@Test
	void validateFileValid() {
		streamObjectConfigurationValidator.validate(streamObjectConfigurationFile);
	}
	
	@Test
	void validateHttpValid() {
		streamObjectConfigurationValidator.validate(streamObjectConfigurationHttp);
	}
	
	@Test
	void validateHttpInvalidZeroBatchSize() {
		streamObjectConfigurationHttp.setBatchSize(Long.valueOf(0L));
		assertThrows(InputValidationException.class, () -> streamObjectConfigurationValidator.validate(streamObjectConfigurationHttp));
	}
	
	@Test
	void validateHttpInvalidEmptyBatchSize() {
		streamObjectConfigurationHttp.setBatchSize(null);
		assertThrows(InputValidationException.class, () -> streamObjectConfigurationValidator.validate(streamObjectConfigurationHttp));
	}
	
	@Test
	void validateHttpInvalidNegativeBatchSize() {
		streamObjectConfigurationHttp.setBatchSize(Long.valueOf(-10L));
		assertThrows(InputValidationException.class, () -> streamObjectConfigurationValidator.validate(streamObjectConfigurationHttp));
	}
	
	@Test
	void validateHttpInvalidEmptyContext() {
		streamObjectConfigurationHttp.setContext(null);
		assertThrows(InputValidationException.class, () -> streamObjectConfigurationValidator.validate(streamObjectConfigurationHttp));
	}
	
	@Test
	void validateHttpInvalidEmptyMicroflow() {
		streamObjectConfigurationHttp.setMicroflow(null);
		assertThrows(InputValidationException.class, () -> streamObjectConfigurationValidator.validate(streamObjectConfigurationHttp));
	}
	
	@Test
	void validateFileInvalidEmptyFile() {
		streamObjectConfigurationFile.setFile(null);
		assertThrows(InputValidationException.class, () -> streamObjectConfigurationValidator.validate(streamObjectConfigurationFile));
	}

}
