package it.polimi.dei.swknights.carcassonne.ModuliAstratti;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

/**
 * Abstract class, it is basically a schema for a controller
 * @author dave
 *
 */
public abstract class AbstractController implements Controller
{
	/**
	 * method used to receive events
	 */
	public abstract void riceviInput(ViewEvent event);

}
