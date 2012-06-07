package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.ModuloControllerHandler;

import java.util.EventObject;

/**
 * Used to give a schema to the various view events
 * 
 * @author edoardopasi
 * 
 */
public abstract class ViewEvent extends EventObject
{
	public ViewEvent(Object source)
	{
		super(source);
	}

	public abstract void accept(ModuloControllerHandler handler);

	private static final long	serialVersionUID	= -5387280202389373513L;

}
