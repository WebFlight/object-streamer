package test.objectstreamer.usecase;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.config.StreamObjectConfigurationFactory;
import objectstreamer.usecase.StreamObjectConfigurationFile;
import objectstreamer.usecase.StreamObjectConfigurationHttp;
import objectstreamer.usecase.StreamObjectConfigurationInitializer;

@ExtendWith(MockitoExtension.class)
class TestStreamObjectConfigurationInitializer {
	
	@Mock
	private StreamObjectConfigurationFactory factory;
	
	@Mock
	private IContext context;
	@Mock
	private IMendixObject __file;
	private String microflow = "TestMicroflow";
	private Long batchSize = 100L;
	@Mock
	private IMendixObject inputParameter;
	private List<IMendixObject> __inputParameters = new ArrayList<>();
	@Mock
	private IMendixObject header;
	private List<IMendixObject> __headers = new ArrayList<>();
	@Mock
	private StreamObjectConfigurationFile configurationFile;
	@Mock
	private StreamObjectConfigurationHttp configurationHttp;
	
	@BeforeEach
	void setup() {
		__inputParameters.add(inputParameter);
		__headers.add(header);
	}

	@Test
	void testInitializeFile() {
		Mockito.when(factory.createFileConfiguration()).thenReturn(configurationFile);
		StreamObjectConfigurationInitializer initializer = new StreamObjectConfigurationInitializer(factory);
		initializer.initializeFile(context, __file, microflow, batchSize, __inputParameters);
		Mockito.verify(configurationFile, Mockito.times(1)).setContext(context);
		Mockito.verify(configurationFile, Mockito.times(1)).setFile(__file);
		Mockito.verify(configurationFile, Mockito.times(1)).setMicroflow(microflow);
		Mockito.verify(configurationFile, Mockito.times(1)).setBatchSize(batchSize);
		Mockito.verify(configurationFile, Mockito.times(1)).setInputParameters(__inputParameters);
	}
	
	@Test
	void testInitializeHttp() {
		Mockito.when(factory.createHttpConfiguration()).thenReturn(configurationHttp);
		StreamObjectConfigurationInitializer initializer = new StreamObjectConfigurationInitializer(factory);
		initializer.initializeHttp(context, microflow, batchSize, __headers, __inputParameters);
		Mockito.verify(configurationHttp, Mockito.times(1)).setContext(context);
		Mockito.verify(configurationHttp, Mockito.times(1)).setHeaders(__headers);
		Mockito.verify(configurationHttp, Mockito.times(1)).setMicroflow(microflow);
		Mockito.verify(configurationHttp, Mockito.times(1)).setBatchSize(batchSize);
		Mockito.verify(configurationHttp, Mockito.times(1)).setInputParameters(__inputParameters);
	}

}
