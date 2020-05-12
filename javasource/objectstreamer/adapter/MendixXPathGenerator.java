package objectstreamer.adapter;

import com.mendix.core.Core;
import com.mendix.datastorage.XPathBasicQuery;

import objectstreamer.domain.port.XPathGenerator;

public class MendixXPathGenerator implements XPathGenerator {

	@Override
	public XPathBasicQuery generate(String entityForExport, String constraint) {
		return Core.createXPathQuery(String.format("//%s%s", entityForExport, constraint));
	}

}
