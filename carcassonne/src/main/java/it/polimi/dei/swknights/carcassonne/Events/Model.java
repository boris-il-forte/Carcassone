package it.polimi.dei.swknights.carcassonne.Events;

import java.util.EventObject;

public interface Model extends EventSource
{
	void fire(EventObject event);
}
