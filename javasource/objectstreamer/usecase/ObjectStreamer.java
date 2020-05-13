package objectstreamer.usecase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.gson.stream.JsonWriter;
import com.mendix.core.Core;
import com.mendix.datastorage.XPathBasicQuery;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class ObjectStreamer {

	private StreamObjectConfigurationImpl streamObjectConfiguration;

	public ObjectStreamer(StreamObjectConfiguration streamObjectConfiguration) {
		this.streamObjectConfiguration = (StreamObjectConfigurationImpl) streamObjectConfiguration;
	}

	public void stream() {
		try (OutputStream outputStream = this.streamObjectConfiguration.getOutputStream();
				OutputStreamWriter outputStreamWriter = this.streamObjectConfiguration.getOutputStreamWriter(outputStream);
				BufferedWriter bufferedWriter = this.streamObjectConfiguration.getBufferedWriter(outputStreamWriter);
				JsonWriter writer = this.streamObjectConfiguration.getWriter(bufferedWriter)) {

			writer.beginArray();

			int offset = 0;
			int batchCount = 0;
			int batchSize = 0;
			
			XPathBasicQuery xPathQuery = this.streamObjectConfiguration.getXpathQuery();

			while (batchSize == batchCount) {
				IContext varyingContext = this.streamObjectConfiguration.getContext().createClone();
				xPathQuery.setOffset(offset);

				List<IMendixObject> objects = xPathQuery.execute(varyingContext);

				batchCount = objects.size();
				
				if(offset == 0) {
					batchSize = objects.size();
				}
				
				offset += batchSize;

				String output = this.streamObjectConfiguration.getJsonMapper().map(varyingContext, objects);

				int outputLength = output.length();

				String outputWithoutBrackets = output.substring(1, outputLength - 1);

				if (batchCount > 0) {
					writer.jsonValue(outputWithoutBrackets);
				}
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

}
