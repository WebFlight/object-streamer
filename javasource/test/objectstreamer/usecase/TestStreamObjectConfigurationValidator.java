package test.objectstreamer.usecase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import objectstreamer.domain.port.ActionExecutor;
import objectstreamer.domain.port.FileStreamWriter;
import objectstreamer.usecase.StreamObjectConfigurationFile;
import objectstreamer.usecase.StreamObjectConfigurationHttp;
import objectstreamer.usecase.StreamObjectConfigurationValidator;

class TestStreamObjectConfigurationValidator {
	
	private static StreamObjectConfigurationFile streamObjectConfigurationFile;
	private static StreamObjectConfigurationHttp streamObjectConfigurationHttp;
	private static StreamObjectConfigurationValidator streamObjectConfigurationValidator = new StreamObjectConfigurationValidator();
	
	@Mock
	private static FileStreamWriter fileStreamWriter;
	@Mock
	private static ActionExecutor<Void> actionExecutor;
	
	@BeforeAll
	static void setup() {
		streamObjectConfigurationFile = new StreamObjectConfigurationFile(fileStreamWriter, actionExecutor);
		streamObjectConfigurationHttp = new StreamObjectConfigurationHttp();
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
