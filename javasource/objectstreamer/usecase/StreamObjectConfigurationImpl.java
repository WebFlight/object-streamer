package objectstreamer.usecase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;

import com.google.gson.stream.JsonWriter;
import com.mendix.datastorage.XPathBasicQuery;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public abstract class StreamObjectConfigurationImpl implements StreamObjectConfiguration {
	
	private IContext context;
	private XPathBasicQuery xPathQuery;
	private String microflow;
	private Optional<List<IMendixObject>> inputParameters;
	private int batchSize;
	
	protected StreamObjectConfigurationImpl () {

	}
	
	public void setContext(IContext context) {
		this.context = context;
	}
	
	
	public void setFile(IMendixObject file) {
		
	}
	
	public void setHeaders(List<IMendixObject> headers) {
		
	}
	
	public XPathBasicQuery getXPathQuery() {
		return this.xPathQuery;
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

	protected String getMicroflow() {
		return microflow;
	}

	public void setMicroflow(String microflow) {
		this.microflow = microflow;
	}
	
	public Optional<List<IMendixObject>> getInputParameters() {
		return this.inputParameters;
	}
	
	public void setInputParameters(List<IMendixObject> inputParameters) {
		this.inputParameters = Optional.ofNullable(inputParameters);
	}

	protected int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	
	

}
