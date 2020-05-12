package objectstreamer.usecase;

import java.io.IOException;
import java.io.OutputStream;

import objectstreamer.domain.port.JsonMapper;

public class StreamObjectConfigurationHttp extends StreamObjectConfigurationImpl{
	
	
	public StreamObjectConfigurationHttp(JsonMapper jsonMapper) {
		super(jsonMapper);
	}

	
	protected OutputStream getOutputStream() throws IOException {
		return this.getContext().getRuntimeResponse().get().getHttpServletResponse().getOutputStream();
	}
	
}
