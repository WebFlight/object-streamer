package objectstreamer.usecase;

import java.io.InputStream;

import com.mendix.core.actionmanagement.CoreAction;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.port.FileStreamWriter;

public class FileStreamWriterAction extends CoreAction<Void> {
	
	private IMendixObject object;
	private InputStream inputStream;
	private FileStreamWriter fileStreamWriter;
	
	protected FileStreamWriterAction(IContext context, FileStreamWriter fileStreamWriter) {
		super(context);
		this.fileStreamWriter = fileStreamWriter;
	}
	
	protected void setObject(IMendixObject object) {
		this.object = object;
	}
	
	protected void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public Void execute(){
		fileStreamWriter.write(getContext(), this.object, this.inputStream);
		return null;
	}

}
