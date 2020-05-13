package objectstreamer.usecase;

import com.mendix.datastorage.XPathBasicQuery;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.port.JsonMapper;

public interface StreamObjectConfiguration {
	
	public void setContext(IContext context);
	
	public JsonMapper getJsonMapper();
	
	public void setXPathQuery(XPathBasicQuery xPathQuery);

	void setFile(IMendixObject file);

}
