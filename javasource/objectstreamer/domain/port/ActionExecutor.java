package objectstreamer.domain.port;

import com.mendix.core.actionmanagement.CoreAction;

public interface ActionExecutor<R> {
	
	public void execute(CoreAction<R> action);

}
