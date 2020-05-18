package objectstreamer.usecase;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class StreamObjectConfigurationHttpImpl extends StreamObjectConfigurationImpl implements StreamObjectConfigurationHttp {
	
	private Optional<List<IMendixObject>> headers;
	
	public StreamObjectConfigurationHttpImpl() {
		super();
	}

	
	protected OutputStream getOutputStream() throws IOException {
		IMxRuntimeResponse response = this.getContext().getRuntimeResponse().get();
		OutputStream outputStream = response.getOutputStream();
		headers.ifPresent(headerList -> 
			headerList.forEach(header -> 
				response.addHeader(header.getValue(getContext(), "Key"), header.getValue(getContext(), "Value")
						)
				)
			);
		return outputStream;
	}
	
	@Override
	public void setHeaders(List<IMendixObject> headers) {
		this.headers = Optional.ofNullable(headers);
	}
	
}