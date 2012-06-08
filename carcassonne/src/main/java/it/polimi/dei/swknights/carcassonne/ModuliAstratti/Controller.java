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
	void riceviInput(ViewEvent event);
}
