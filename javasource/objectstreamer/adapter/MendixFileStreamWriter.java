package objectstreamer.adapter;

import java.io.InputStream;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.port.FileStreamWriter;

public class MendixFileStreamWriter implements FileStreamWriter {

	@Override
	public void write(IContext context, IMendixObject object, InputStream inputStream) {
		Core.storeFileDocumentContent(context, object, inputStream);
	}

}
