// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package objectstreamer.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import objectstreamer.config.StreamObjectConfigurationFactory;
import objectstreamer.usecase.ObjectStreamer;
import objectstreamer.usecase.StreamObjectConfigurationFile;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class StreamObjectsToFile extends CustomJavaAction<java.lang.Void>
{
	private java.lang.Long batchSize;
	private IMendixObject __file;
	private system.proxies.FileDocument file;
	private java.lang.String microflow;
	private java.util.List<IMendixObject> __inputParameters;
	private java.util.List<objectstreamer.proxies.InputParameter> inputParameters;

	public StreamObjectsToFile(IContext context, java.lang.Long batchSize, IMendixObject file, java.lang.String microflow, java.util.List<IMendixObject> inputParameters)
	{
		super(context);
		this.batchSize = batchSize;
		this.__file = file;
		this.microflow = microflow;
		this.__inputParameters = inputParameters;
	}

	@java.lang.Override
	public java.lang.Void executeAction() throws Exception
	{
		this.file = __file == null ? null : system.proxies.FileDocument.initialize(getContext(), __file);

		this.inputParameters = new java.util.ArrayList<objectstreamer.proxies.InputParameter>();
		if (__inputParameters != null)
			for (IMendixObject __inputParametersElement : __inputParameters)
				this.inputParameters.add(objectstreamer.proxies.InputParameter.initialize(getContext(), __inputParametersElement));

		// BEGIN USER CODE
		file.getClass();
		IContext context = this.getContext();
		
		StreamObjectConfigurationFactory factory = new StreamObjectConfigurationFactory();
		
		StreamObjectConfigurationFile streamObjectConfiguration = factory.createFileConfiguration();
		streamObjectConfiguration.setContext(context);
		streamObjectConfiguration.setFile(__file);
		streamObjectConfiguration.setMicroflow(microflow);
		streamObjectConfiguration.setBatchSize(batchSize.intValue());
		
		ObjectStreamer objectStreamer = new ObjectStreamer(streamObjectConfiguration);
		objectStreamer.stream();
		
		return null;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "StreamObjectsToFile";
	}

	// BEGIN EXTRA CODE

	// END EXTRA CODE
}
