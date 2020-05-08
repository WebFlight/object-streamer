package objectstreamer.usecase;

import java.util.NoSuchElementException;

public class StreamObjectConfigurationFactory {

	public StreamObjectConfiguration create(String streamType) {
		if("File".equalsIgnoreCase(streamType)) {
			return new StreamObjectConfigurationFile();
		}
		
		if("Http".equalsIgnoreCase(streamType)) {
			return new StreamObjectConfigurationHttp();
		}
		
		throw new NoSuchElementException(String.format("Stream type %s not found.", streamType));
	}
	
}
