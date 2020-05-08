package objectstreamer.usecase;

import java.io.InputStream;

import com.mendix.core.Core;
import com.mendix.core.actionmanagement.CoreAction;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class FileStreamWriter extends CoreAction<Void> {
	
	private IMendixObject object;
	private InputStream inputStream;
	
	protected FileStreamWriter(IContext context, IMendixObject object, InputStream inputStream) {
		super(context);
		this.object = object;
		this.inputStream = inputStream;
	}

	@Override
	public Void execute(){
		Core.storeFileDocumentContent(getContext(), this.object, this.inputStream);
		return null;
	}

}
