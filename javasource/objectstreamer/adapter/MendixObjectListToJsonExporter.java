package objectstreamer.adapter;

import java.io.InputStream;
import java.util.List;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.port.ObjectListToJsonExporter;

public class MendixObjectListToJsonExporter implements ObjectListToJsonExporter{

	@Override
	public InputStream export(IContext context, String exportMapping, List<IMendixObject> objects) {
		return Core.integration().exportToStream(context, exportMapping, objects, false);
	}

}
