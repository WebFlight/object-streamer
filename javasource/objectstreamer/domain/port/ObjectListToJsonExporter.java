package objectstreamer.domain.port;

import java.io.InputStream;
import java.util.List;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public interface ObjectListToJsonExporter {
	
	public InputStream export(IContext context, String exportMapping, List<IMendixObject> objects);

}
