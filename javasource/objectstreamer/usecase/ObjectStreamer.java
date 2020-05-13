package objectstreamer.usecase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.gson.stream.JsonWriter;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class ObjectStreamer {

	private StreamObjectConfigurationImpl streamObjectConfiguration;
	private int offset = 0;
	private int maxObjectsPerBatch = 0;
	private int actualObjectsPerBatch = 0;
	private IContext context;

	public ObjectStreamer(StreamObjectConfiguration streamObjectConfiguration) {
		this.streamObjectConfiguration = (StreamObjectConfigurationImpl) streamObjectConfiguration;
	}

	public void stream() {
		try (OutputStream outputStream = this.streamObjectConfiguration.getOutputStream();
				OutputStreamWriter outputStreamWriter = this.streamObjectConfiguration.getOutputStreamWriter(outputStream);
				BufferedWriter bufferedWriter = this.streamObjectConfiguration.getBufferedWriter(outputStreamWriter);
				JsonWriter writer = this.streamObjectConfiguration.getWriter(bufferedWriter)) {

			writer.beginArray();

			while (nextBatchExists()) {
				createEmptyContext();
				List<IMendixObject> objects = retrieveObjects();
				actualObjectsPerBatch = objects.size();
				
				if (noObjectsToReturn()) {
					break;
				}
				
				if(isFirstIteration()) {
					maxObjectsPerBatch = actualObjectsPerBatch;
				}
				
				String jsonWithoutArrayBrackets = stripBrackets(this.streamObjectConfiguration.getJsonMapper().map(context, objects));

				if (anyObjectsToReturn()) {
					writer.jsonValue(jsonWithoutArrayBrackets);
				}
				
				increaseOffset();
			}

			writer.endArray();

		} catch (IOException e) {
			Core.getLogger("Streamer").error(e.getMessage(), e);
			;
		} catch (NoSuchElementException e) {
			Core.getLogger("Streamer").error(e.getMessage(), e);
			;
		}
	}
	
	private boolean nextBatchExists() {
		return maxObjectsPerBatch == actualObjectsPerBatch;
	}
	
	private void createEmptyContext() {
		this.context = this.streamObjectConfiguration.getContext().createClone();
	}
	
	private List<IMendixObject> retrieveObjects() {
		return this.streamObjectConfiguration.getXpathQuery().execute(context);
	}
	
	private boolean noObjectsToReturn() {
		return actualObjectsPerBatch == 0;
	}
	
	private boolean isFirstIteration() {
		return this.offset == 0;
	}
	
	private String stripBrackets(String json) {
		int length = json.length();
		String jsonWithoutArrayBrackets = json.substring(1, length - 1);
		return jsonWithoutArrayBrackets;
	}
	
	private void increaseOffset() {
		offset += actualObjectsPerBatch;
		this.streamObjectConfiguration.getXpathQuery().setOffset(offset);
	}
	
	private boolean anyObjectsToReturn() {
		return actualObjectsPerBatch > 0;
	}

}
