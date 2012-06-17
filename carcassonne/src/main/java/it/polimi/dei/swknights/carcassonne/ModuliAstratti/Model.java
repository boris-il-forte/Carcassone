package it.polimi.dei.swknights.carcassonne.ModuliAstratti;

import it.polimi.dei.swknights.carcassonne.Events.EventSource;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;

/**
 * Basic schema for Model
 * 
 * @author edoardopasi & dave
 * 
 */
public interface Model extends EventSource
{
	/**
	 * Method used to fire events
	 * @param event the event to be fired
	 */
	void fire(ControllerEvent event);
}
