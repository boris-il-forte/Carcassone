package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
/**
 * Basic schema for Model
 * @author edoardopasi & dave
 *
 */
public interface Model extends EventSource
{
	void fire(ControllerEvent event);
}
