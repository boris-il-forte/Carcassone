package it.polimi.dei.swknights.carcassonne.Events;

import java.util.EventListener;
import java.util.EventObject;

public interface ControllerListener extends EventListener
{

	public void riceviModificheModel(EventObject event);

}