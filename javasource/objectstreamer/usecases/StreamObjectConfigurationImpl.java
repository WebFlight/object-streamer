package objectstreamer.usecases;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.google.gson.stream.JsonWriter;
import com.mendix.datastorage.XPathBasicQuery;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public abstract class StreamObjectConfigurationImpl implements StreamObjectConfiguration {
	
	private IContext context;
	private XPathBasicQuery xPathQuery;
	private JsonMapper jsonMapper;
	
	protected StreamObjectConfigurationImpl () {
		
	}
	
	public void setContext(IContext context) {
		this.context = context;
	}
	
	public void setJsonMapper(JsonMapper jsonMapper) {
		this.jsonMapper = jsonMapper;
	}
	
	public JsonMapper getJsonMapper() {
		return this.jsonMapper;
	}
	
	public void setFile(IMendixObject file) {
		
	}
	
	public void setXPathQuery(XPathBasicQuery xPathQuery) {
		this.xPathQuery = xPathQuery;
	}
	
	protected IContext getContext() {
		return this.context;
	}
	
	protected XPathBasicQuery getXpathQuery() {
		return this.xPathQuery;
	}
	
	protected OutputStreamWriter getOutputStreamWriter(OutputStream outputStream) {
		return new OutputStreamWriter(outputStream);
	}
	
	protected BufferedWriter getBufferedWriter(OutputStreamWriter outputStreamWriter) {
		return new BufferedWriter(outputStreamWriter);
	}
	
	protected JsonWriter getWriter(BufferedWriter bufferedWriter) {
		return new JsonWriter(bufferedWriter);
	}
	
	abstract protected OutputStream getOutputStream() throws IOException;

}
