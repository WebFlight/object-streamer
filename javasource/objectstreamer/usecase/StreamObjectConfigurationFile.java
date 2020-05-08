package objectstreamer.usecase;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IMendixObject;


public class StreamObjectConfigurationFile extends StreamObjectConfigurationImpl{
	private IMendixObject file;
	
	public StreamObjectConfigurationFile () {
		
	}
	
	@Override
	public void setFile(IMendixObject file) {
		this.file = file;
	}
	
	protected OutputStream getOutputStream() throws IOException {
		PipedInputStream pipedInputStream = new PipedInputStream();
		PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);
		FileStreamWriter fileStreamWriter = new FileStreamWriter(this.getContext(), file, pipedInputStream);
		Core.executeVoid(fileStreamWriter);
		return pipedOutputStream;
	}
	
}
