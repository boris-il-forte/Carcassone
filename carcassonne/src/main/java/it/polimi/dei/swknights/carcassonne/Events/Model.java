package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;

public interface Model extends EventSource
{
	void fire(ControllerEvent event);
}
