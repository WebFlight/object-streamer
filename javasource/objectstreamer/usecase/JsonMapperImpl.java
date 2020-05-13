package objectstreamer.usecase;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.port.JsonMapper;
import objectstreamer.domain.port.ObjectListToJsonExporter;

public class JsonMapperImpl implements JsonMapper{
	
	private String exportMapping;
	private ObjectListToJsonExporter exporter;
	private InputStreamToStringConverter converter;
	
	public JsonMapperImpl(ObjectListToJsonExporter exporter, InputStreamToStringConverter converter) {
		this.exporter = exporter;
		this.converter = converter;
	}
	
	public void setExportMapping(String exportMapping) {
		this.exportMapping = exportMapping;
	}

	@Override
	public String map(IContext context, List<IMendixObject> objects) throws IOException{
		InputStream inputStream = exporter.export(context, this.exportMapping, objects);
		String result = converter.convert(inputStream);
		return result;
	}

}
