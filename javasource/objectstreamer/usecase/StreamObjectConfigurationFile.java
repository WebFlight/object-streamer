package objectstreamer.usecase;

import com.mendix.systemwideinterfaces.core.IMendixObject;

public interface StreamObjectConfigurationFile extends StreamObjectConfiguration {

	void setFile(IMendixObject file);
	
}
