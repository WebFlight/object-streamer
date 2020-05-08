package objectstreamer.usecase;

import com.mendix.datastorage.XPathBasicQuery;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public interface StreamObjectConfiguration {
	
	public void setContext(IContext context);
	
	public void setXPathQuery(XPathBasicQuery xPathQuery);
	
	public void setJsonMapper(JsonMapper jsonMapper);

	void setFile(IMendixObject file);

}
