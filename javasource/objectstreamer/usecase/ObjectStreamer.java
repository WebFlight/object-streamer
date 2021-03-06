package objectstreamer.usecase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.stream.JsonWriter;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import objectstreamer.domain.exception.ErrorMessage;
import objectstreamer.domain.exception.WriteException;

public class ObjectStreamer {

	private StreamObjectConfigurationImpl streamObjectConfiguration;
	private int offset = 0;
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

			Map<String, Object> parameterMap = new HashMap<>();
			Optional<List<IMendixObject>> inputParameters = streamObjectConfiguration.getInputParameters();
			
			inputParameters.ifPresent(
					params -> params.forEach(
							param -> parameterMap.put(
									param.getValue(this.streamObjectConfiguration.getContext(), "Name"), param.getValue(this.streamObjectConfiguration.getContext(), "Value")
									)));
			
			while (true) {
				createEmptyContext();
				
				String batch = Core.microflowCall(this.streamObjectConfiguration.getMicroflow())
						.withParam("Offset", this.offset)
						.withParam("BatchSize", this.streamObjectConfiguration.getBatchSize())
						.withParams(parameterMap)
						.execute(context);
				
				if (batch == null) {
					break;
				}
				
				if (batch.isEmpty() || batch.equals("[]")) {
					break;
				}
				
				String jsonWithoutArrayBrackets = stripBrackets(batch);

				writer.jsonValue(jsonWithoutArrayBrackets);
				
				increaseOffset();
			}

			writer.endArray();

		} catch (IOException e) {
			throw new WriteException(ErrorMessage.WRITE, e);
		} 
	}
	
	private void createEmptyContext() {
		this.context = this.streamObjectConfiguration.getContext().createClone();
	}
	
	private String stripBrackets(String json) {
		int length = json.length();
		String jsonWithoutArrayBrackets = json.substring(1, length - 1);
		return jsonWithoutArrayBrackets;
	}
	
	private void increaseOffset() {
		offset += streamObjectConfiguration.getBatchSize();
	}

}
