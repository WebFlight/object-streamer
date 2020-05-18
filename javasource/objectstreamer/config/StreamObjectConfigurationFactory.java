package objectstreamer.config;

import objectstreamer.adapter.MendixActionExecutor;
import objectstreamer.adapter.MendixFileStreamWriter;
import objectstreamer.domain.port.ActionExecutor;
import objectstreamer.domain.port.FileStreamWriter;
import objectstreamer.usecase.StreamObjectConfigurationFile;
import objectstreamer.usecase.StreamObjectConfigurationFileImpl;
import objectstreamer.usecase.StreamObjectConfigurationHttp;
import objectstreamer.usecase.StreamObjectConfigurationHttpImpl;

public class StreamObjectConfigurationFactory {
	
	private final FileStreamWriter fileStreamWriter = new MendixFileStreamWriter();
	private final ActionExecutor<Void> actionExecutor = new MendixActionExecutor();
	
	public StreamObjectConfigurationFile createFileConfiguration() {
			return new StreamObjectConfigurationFileImpl(this.fileStreamWriter, this.actionExecutor);
	}
	
	public StreamObjectConfigurationHttp createHttpConfiguration() {
		return new StreamObjectConfigurationHttpImpl();
}
	
	
	
}
