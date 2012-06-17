package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ControllerHandler;
import java.util.EventObject;

/**
 * Used to give a schema to the various view events
 * 
 * @author edoardopasi
 * 
 */
public abstract class ViewEvent extends EventObject
{
	/**
	 * Default constructor
	 * 
	 * @param source
	 *            the event source
	 */
	public ViewEvent(Object source)
	{
		super(source);
	}

	/**
	 * Used to ask the handler to "take care" of the event
	 * 
	 * @param handler
	 */
	public abstract void accept(ControllerHandler handler);

	private static final long	serialVersionUID	= -5387280202389373513L;

}
