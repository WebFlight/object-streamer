package objectstreamer.usecases;

import java.io.IOException;
import java.util.List;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public interface JsonMapper {
	
	public String map(IContext context, List<IMendixObject> objects) throws IOException;

}
