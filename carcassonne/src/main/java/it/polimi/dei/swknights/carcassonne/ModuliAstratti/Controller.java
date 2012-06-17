package it.polimi.dei.swknights.carcassonne.ModuliAstratti;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

import java.util.EventListener;

/**
 * Controller interface, runnable and listener
 * 
 * @author edoardopasi & dave
 * 
 */
public interface Controller extends EventListener, Runnable
{
	/**
	 * method to receive events
	 * @param event the event to be handle
	 */
	void riceviInput(ViewEvent event);

}
