package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ModuloViewHandler;
import java.util.EventObject;

/**
 * This class describes a generic ControllerEvent, it basically provide just a
 * schema for the specific Controller events
 * 
 * @author edoardopasi
 * 
 */
public abstract class ControllerEvent extends EventObject
{

	public ControllerEvent(Object source)
	{
		super(source);

	}

	/**
	 * Used to ask the handler to "take care" of the event
	 * 
	 * @param handler
	 */
	public abstract void accept(ModuloViewHandler handler);

	/**
	 * Set the command, used to avoid instanceof and give the Event a nicer
	 * toString
	 * 
	 * @param messaggio
	 */

	private static final long	serialVersionUID	= 3191102477186388256L;

}
