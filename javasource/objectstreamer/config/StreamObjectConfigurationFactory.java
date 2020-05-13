package objectstreamer.config;

import java.util.NoSuchElementException;

import objectstreamer.adapter.MendixFileStreamWriter;
import objectstreamer.adapter.MendixXPathGenerator;
import objectstreamer.domain.port.XPathGenerator;
import objectstreamer.usecase.JsonMapperImpl;
import objectstreamer.usecase.StreamObjectConfiguration;
import objectstreamer.usecase.StreamObjectConfigurationFile;
import objectstreamer.usecase.StreamObjectConfigurationHttp;

public class StreamObjectConfigurationFactory {
	
	private final JsonMapperImpl jsonMapper = new JsonMapperImpl();
	private final XPathGenerator xPathGenerator = new MendixXPathGenerator();

	public StreamObjectConfiguration create(String streamType) {
		if("File".equalsIgnoreCase(streamType)) {
			return new StreamObjectConfigurationFile(this.jsonMapper, this.xPathGenerator, new MendixFileStreamWriter());
		}
		
		if("Http".equalsIgnoreCase(streamType)) {
			return new StreamObjectConfigurationHttp(this.jsonMapper, this.xPathGenerator);
		}
		
		throw new NoSuchElementException(String.format("Stream type %s not found.", streamType));
	}
	
}
