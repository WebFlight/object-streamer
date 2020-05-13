package objectstreamer.usecase;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.port.ActionExecutor;
import objectstreamer.domain.port.FileStreamWriter;
import objectstreamer.domain.port.JsonMapper;
import objectstreamer.domain.port.XPathGenerator;


public class StreamObjectConfigurationFile extends StreamObjectConfigurationImpl{

	private FileStreamWriter fileStreamWriter;
	private IMendixObject file;
	private ActionExecutor<Void> actionExecutor;
	
	public StreamObjectConfigurationFile(JsonMapper jsonMapper, XPathGenerator xPathGenerator, FileStreamWriter fileStreamWriter, ActionExecutor<Void> actionExecutor) {
		super(jsonMapper, xPathGenerator);
		this.fileStreamWriter = fileStreamWriter;
		this.actionExecutor = actionExecutor;
	}	
	
	
	@Override
	public void setFile(IMendixObject file) {
		this.file = file;
	}
	
	protected OutputStream getOutputStream() throws IOException {
		PipedInputStream pipedInputStream = new PipedInputStream();
		PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);
		FileStreamWriterAction fileStreamWriterAction = new FileStreamWriterAction(getContext(), this.fileStreamWriter);
		fileStreamWriterAction.setInputStream(pipedInputStream);
		fileStreamWriterAction.setObject(file);
		actionExecutor.execute(fileStreamWriterAction);
		return pipedOutputStream;
	}
	
}
