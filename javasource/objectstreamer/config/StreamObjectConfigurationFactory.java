package objectstreamer.config;

import java.util.NoSuchElementException;

import objectstreamer.adapter.MendixActionExecutor;
import objectstreamer.adapter.MendixFileStreamWriter;
import objectstreamer.adapter.MendixObjectListToJsonExporter;
import objectstreamer.adapter.MendixXPathGenerator;
import objectstreamer.domain.port.ActionExecutor;
import objectstreamer.domain.port.FileStreamWriter;
import objectstreamer.domain.port.JsonMapper;
import objectstreamer.domain.port.XPathGenerator;
import objectstreamer.usecase.InputStreamToStringConverter;
import objectstreamer.usecase.JsonMapperImpl;
import objectstreamer.usecase.StreamObjectConfiguration;
import objectstreamer.usecase.StreamObjectConfigurationFile;
import objectstreamer.usecase.StreamObjectConfigurationHttp;

public class StreamObjectConfigurationFactory {
	
	private final JsonMapper jsonMapper = new JsonMapperImpl(new MendixObjectListToJsonExporter(), new InputStreamToStringConverter());
	private final XPathGenerator xPathGenerator = new MendixXPathGenerator();
	private final FileStreamWriter fileStreamWriter = new MendixFileStreamWriter();
	private final ActionExecutor<Void> actionExecutor = new MendixActionExecutor();

	public StreamObjectConfiguration create(String streamType) {
		if("File".equalsIgnoreCase(streamType)) {
			return new StreamObjectConfigurationFile(this.jsonMapper, this.xPathGenerator, this.fileStreamWriter, this.actionExecutor);
		}
		
		if("Http".equalsIgnoreCase(streamType)) {
			return new StreamObjectConfigurationHttp(this.jsonMapper, this.xPathGenerator);
		}
		
		throw new NoSuchElementException(String.format("Stream type %s not found.", streamType));
	}
	
}
