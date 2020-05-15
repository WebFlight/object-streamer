package objectstreamer.config;

import java.util.NoSuchElementException;

import objectstreamer.adapter.MendixActionExecutor;
import objectstreamer.adapter.MendixFileStreamWriter;
import objectstreamer.domain.port.ActionExecutor;
import objectstreamer.domain.port.FileStreamWriter;
import objectstreamer.usecase.StreamObjectConfiguration;
import objectstreamer.usecase.StreamObjectConfigurationFile;
import objectstreamer.usecase.StreamObjectConfigurationHttp;

public class StreamObjectConfigurationFactory {
	
	private final FileStreamWriter fileStreamWriter = new MendixFileStreamWriter();
	private final ActionExecutor<Void> actionExecutor = new MendixActionExecutor();

	public StreamObjectConfiguration create(String streamType) {
		if("File".equalsIgnoreCase(streamType)) {
			return new StreamObjectConfigurationFile(this.fileStreamWriter, this.actionExecutor);
		}
		
		if("Http".equalsIgnoreCase(streamType)) {
			return new StreamObjectConfigurationHttp();
		}
		
		throw new NoSuchElementException(String.format("Stream type %s not found.", streamType));
	}
	
}
