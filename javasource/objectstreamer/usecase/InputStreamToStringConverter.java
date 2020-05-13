package objectstreamer.usecase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class InputStreamToStringConverter {
	
	protected String convert(InputStream inputStream) throws IOException {
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
