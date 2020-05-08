package objectstreamer.usecase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class JsonMapperImpl implements JsonMapper{
	
	private String exportMapping;
	
	public JsonMapperImpl() {
		
	}
	
	public void setExportMapping(String exportMapping) {
		this.exportMapping = exportMapping;
	}

	@Override
	public String map(IContext context, List<IMendixObject> objects) throws IOException{
		InputStream inputStream = Core.integration().exportToStream(context, this.exportMapping, objects, false);
		String result = convert(inputStream);
		return result;
	}
	
	private String convert(InputStream inputStream) throws IOException {
		StringBuilder textBuilder = new StringBuilder();
	    try (
	    		InputStream closableInputStream = inputStream;
	    		InputStreamReader inputStreamReader = new InputStreamReader(closableInputStream, Charset.forName(StandardCharsets.UTF_8.name()));
	    		Reader reader = new BufferedReader(inputStreamReader) ) {
	        int c = 0;
	        while ((c = reader.read()) != -1) {
	            textBuilder.append((char) c);
	        }
	    }
	    
	    return textBuilder.toString();
	}

}
