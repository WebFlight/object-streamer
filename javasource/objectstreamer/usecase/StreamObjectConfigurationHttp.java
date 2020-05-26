package objectstreamer.usecase;

import java.util.List;

import com.mendix.systemwideinterfaces.core.IMendixObject;

public interface StreamObjectConfigurationHttp extends StreamObjectConfiguration {

	void setHeaders(List<IMendixObject> headers);
	
}
