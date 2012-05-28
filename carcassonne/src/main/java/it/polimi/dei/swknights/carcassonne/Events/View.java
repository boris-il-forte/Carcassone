package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;

import java.util.EventListener;

public interface View extends EventListener, Runnable, EventSource
{
	void riceviModificheModel(ControllerEvent event);
}
