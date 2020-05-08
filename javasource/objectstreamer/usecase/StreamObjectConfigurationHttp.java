package objectstreamer.usecase;

import java.io.IOException;
import java.io.OutputStream;

public class StreamObjectConfigurationHttp extends StreamObjectConfigurationImpl{
	
	public StreamObjectConfigurationHttp () {
		
	}
	
	protected OutputStream getOutputStream() throws IOException {
		return this.getContext().getRuntimeResponse().get().getHttpServletResponse().getOutputStream();
	}
	
}
