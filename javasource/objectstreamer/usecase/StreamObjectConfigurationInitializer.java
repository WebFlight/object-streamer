package objectstreamer.usecase;

import java.util.List;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.config.StreamObjectConfigurationFactory;

public class StreamObjectConfigurationInitializer {
	
	private StreamObjectConfigurationFactory factory;
	
	public StreamObjectConfigurationInitializer(StreamObjectConfigurationFactory factory) {
		this.factory = factory;
	}
	
	public StreamObjectConfigurationFile initializeFile(IContext context, IMendixObject __file, String microflow, Long batchSize, List<IMendixObject> __inputParameters) {
		StreamObjectConfigurationFile streamObjectConfiguration = this.factory.createFileConfiguration();
		
		configureGenericParameters(streamObjectConfiguration, context, microflow, batchSize, __inputParameters);
		streamObjectConfiguration.setFile(__file);
		
		return streamObjectConfiguration;
	}
	
	public StreamObjectConfigurationHttp initializeHttp(IContext context, String microflow, Long batchSize, List<IMendixObject> __headers, List<IMendixObject> __inputParameters) {
		StreamObjectConfigurationHttp streamObjectConfiguration = this.factory.createHttpConfiguration();
		
		configureGenericParameters(streamObjectConfiguration, context, microflow, batchSize, __inputParameters);
		streamObjectConfiguration.setHeaders(__headers);
		
		return streamObjectConfiguration;
	}
	
	private void configureGenericParameters(StreamObjectConfiguration streamObjectConfiguration, IContext context, String microflow, Long batchSize, List<IMendixObject> __inputParameters) {
		streamObjectConfiguration.setContext(context);
		streamObjectConfiguration.setMicroflow(microflow);
		streamObjectConfiguration.setBatchSize(batchSize);
		streamObjectConfiguration.setInputParameters(__inputParameters);
	}

}
