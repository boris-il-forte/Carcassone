package it.polimi.dei.swknights.carcassonne.ModuliAstratti;

import it.polimi.dei.swknights.carcassonne.Events.EventSource;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;

import java.util.EventListener;

/**
 * Basic schema for Model
 * 
 * @author edoardopasi & dave
 * 
 */
public interface View extends EventListener, Runnable, EventSource
{
	/**
	 * Method to receive 
	 * @param event
	 */
	void riceviModificheModel(ControllerEvent event);
}
