package objectstreamer.domain.port;

import java.io.InputStream;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public interface FileStreamWriter {
	
	public void write(IContext context, IMendixObject object, InputStream inputStream);

}
