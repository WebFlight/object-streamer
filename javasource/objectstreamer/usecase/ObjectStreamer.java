package objectstreamer.usecase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;

import com.google.gson.stream.JsonWriter;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;

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

			while (true) {
				createEmptyContext();
				String batch = Core.microflowCall(this.streamObjectConfiguration.getMicroflow())
						.withParam("Offset", this.offset)
						.withParam("BatchSize", this.streamObjectConfiguration.getBatchSize())
						.execute(context);
				
				if (batch.isEmpty() || batch.equals("") || batch.equals("[]")) {
					break;
				}
				
				String jsonWithoutArrayBrackets = stripBrackets(batch);

				writer.jsonValue(jsonWithoutArrayBrackets);
				
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
