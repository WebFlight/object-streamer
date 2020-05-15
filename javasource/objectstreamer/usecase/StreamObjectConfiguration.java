package objectstreamer.usecase;

import java.util.List;

import com.mendix.datastorage.XPathBasicQuery;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public interface StreamObjectConfiguration {
	
	public void setContext(IContext context);
	
	public XPathBasicQuery getXPathQuery();

	void setFile(IMendixObject file);
	
	void setHeaders(List<IMendixObject> headers);
	
	public void setMicroflow(String microflow);
	
	public void setBatchSize(int batchSize);

}
