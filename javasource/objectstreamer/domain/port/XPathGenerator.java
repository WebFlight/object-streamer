package objectstreamer.domain.port;

import com.mendix.datastorage.XPathBasicQuery;

public interface XPathGenerator {
	
	public XPathBasicQuery generate(String entityForExport, String constraint);

}
