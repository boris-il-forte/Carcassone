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
	void fire(ControllerEvent event);
}
