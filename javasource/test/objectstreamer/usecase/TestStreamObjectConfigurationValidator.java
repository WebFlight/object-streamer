package test.objectstreamer.usecase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.port.ActionExecutor;
import objectstreamer.domain.port.FileStreamWriter;
import objectstreamer.usecase.StreamObjectConfigurationFileImpl;
import objectstreamer.usecase.StreamObjectConfigurationHttpImpl;
import objectstreamer.usecase.StreamObjectConfigurationValidator;

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
		streamObjectConfigurationFile = new StreamObjectConfigurationFileImpl(fileStreamWriter, actionExecutor);
		streamObjectConfigurationHttp = new StreamObjectConfigurationHttpImpl();
	}

	@Test
	void validateFileValid() {
		streamObjectConfigurationFile.setBatchSize(Long.valueOf(100L));
		streamObjectConfigurationFile.setContext(context);
		streamObjectConfigurationFile.setFile(file);
		streamObjectConfigurationFile.setMicroflow("A microflow");
		streamObjectConfigurationValidator.validate(streamObjectConfigurationFile);
	}
	
	@Test
	void validateHttpValid() {
		streamObjectConfigurationHttp.setBatchSize(Long.valueOf(100L));
		streamObjectConfigurationHttp.setContext(context);
		streamObjectConfigurationHttp.setMicroflow("A microflow");
		streamObjectConfigurationValidator.validate(streamObjectConfigurationHttp);
	}

}
