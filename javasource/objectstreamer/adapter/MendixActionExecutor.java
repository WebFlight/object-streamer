package objectstreamer.adapter;

import com.mendix.core.Core;
import com.mendix.core.actionmanagement.CoreAction;

import objectstreamer.domain.port.ActionExecutor;

public class MendixActionExecutor implements ActionExecutor<Void> {

	@Override
	public void execute(CoreAction<Void> action) {
		Core.executeVoid(action);	
	}

}
