package objectstreamer.usecase;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.port.ActionExecutor;
import objectstreamer.domain.port.FileStreamWriter;


public class StreamObjectConfigurationFileImpl extends StreamObjectConfigurationImpl implements StreamObjectConfigurationFile {

	private FileStreamWriter fileStreamWriter;
	private IMendixObject file;
	private ActionExecutor<Void> actionExecutor;
	
	public StreamObjectConfigurationFileImpl(FileStreamWriter fileStreamWriter, ActionExecutor<Void> actionExecutor) {
		super();
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
		IMendixObject file = getFile();
		fileStreamWriterAction.setObject(file);
		actionExecutor.execute(fileStreamWriterAction);
		return pipedOutputStream;
	}
	
	private IMendixObject getFile() {
		return this.file;
	}
	
}
