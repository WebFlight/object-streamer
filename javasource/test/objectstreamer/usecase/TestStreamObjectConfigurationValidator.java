package test.objectstreamer.usecase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import objectstreamer.domain.port.ActionExecutor;
import objectstreamer.domain.port.FileStreamWriter;
import objectstreamer.usecase.StreamObjectConfigurationFileImpl;
import objectstreamer.usecase.StreamObjectConfigurationHttpImpl;
import objectstreamer.usecase.StreamObjectConfigurationValidator;

class TestStreamObjectConfigurationValidator {
	
	private static StreamObjectConfigurationFileImpl streamObjectConfigurationFile;
	private static StreamObjectConfigurationHttpImpl streamObjectConfigurationHttp;
	private static StreamObjectConfigurationValidator streamObjectConfigurationValidator = new StreamObjectConfigurationValidator();
	
	@Mock
	private static FileStreamWriter fileStreamWriter;
	@Mock
	private static ActionExecutor<Void> actionExecutor;
	
	@BeforeAll
	static void setup() {
		streamObjectConfigurationFile = new StreamObjectConfigurationFileImpl(fileStreamWriter, actionExecutor);
		streamObjectConfigurationHttp = new StreamObjectConfigurationHttpImpl();
	}

	@Test
	void validateFileTrue() {
		streamObjectConfigurationValidator.validate(streamObjectConfigurationFile);
	}
	
	@Test
	void validateHttpTrue() {
		streamObjectConfigurationValidator.validate(streamObjectConfigurationHttp);
	}

}
