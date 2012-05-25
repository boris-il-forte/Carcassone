package it.polimi.dei.swknights.carcassonne.Events;

import java.util.EventListener;
import java.util.EventObject;

public interface View extends EventListener, Runnable, EventSource
{
	void riceviModificheModel(EventObject event);
}
