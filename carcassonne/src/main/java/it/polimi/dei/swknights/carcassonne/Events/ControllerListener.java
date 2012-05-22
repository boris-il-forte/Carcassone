package it.polimi.dei.swknights.carcassonne.Events;

import java.util.EventListener;
import java.util.EventObject;

public interface ControllerListener extends EventListener
{
	void riceviModificheModel(EventObject event);
}
