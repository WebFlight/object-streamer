package objectstreamer.usecase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.google.gson.stream.JsonWriter;
import com.mendix.datastorage.XPathBasicQuery;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.port.JsonMapper;
import objectstreamer.domain.port.XPathGenerator;

public abstract class StreamObjectConfigurationImpl implements StreamObjectConfiguration {
	
	private IContext context;
	private XPathBasicQuery xPathQuery;
	private JsonMapper jsonMapper;
	private XPathGenerator xPathGenerator;
	private String microflow;
	private int batchSize;
	
	protected StreamObjectConfigurationImpl (JsonMapper jsonMapper, XPathGenerator xPathGenerator) {
		this.jsonMapper = jsonMapper;
		this.xPathGenerator = xPathGenerator;
	}
	
	public void setContext(IContext context) {
		this.context = context;
	}
	
	
	public void setFile(IMendixObject file) {
		
	}
	
	public XPathBasicQuery getXPathQuery() {
		return this.xPathQuery;
	}
	
	public void setXPathQuery(String entityForExport, String constraint) {
		this.xPathQuery = xPathGenerator.generate(entityForExport, constraint); 
	}
	
	protected IContext getContext() {
		return this.context;
	}
	
	protected XPathBasicQuery getXpathQuery() {
		return this.xPathQuery;
	}
	
	public JsonMapper getJsonMapper() {
		return this.jsonMapper;
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

	protected String getMicroflow() {
		return microflow;
	}

	public void setMicroflow(String microflow) {
		this.microflow = microflow;
	}

	protected int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	
	

}
