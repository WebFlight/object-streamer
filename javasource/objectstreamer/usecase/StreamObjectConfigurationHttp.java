package objectstreamer.usecase;

import java.io.IOException;
import java.io.OutputStream;

import objectstreamer.domain.port.JsonMapper;
import objectstreamer.domain.port.XPathGenerator;

public class StreamObjectConfigurationHttp extends StreamObjectConfigurationImpl{
	
	
	public StreamObjectConfigurationHttp(JsonMapper jsonMapper, XPathGenerator xPathGenerator) {
		super(jsonMapper, xPathGenerator);
	}

	
	protected OutputStream getOutputStream() throws IOException {
		return this.getContext().getRuntimeResponse().get().getHttpServletResponse().getOutputStream();
	}
	
}
