package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.ModuloControllerHandler;

/**
 * Event triggered by the view to notify that the current player wants to rotate
 * the current card
 * 
 * @author edoardopasi & dave
 * 
 */

public class RotateEvent extends ViewEvent
{

	public RotateEvent(Object source)
	{
		super(source);
	}

	private static final long	serialVersionUID	= 2085506187547788810L;

	@Override
	public void accept(ModuloControllerHandler handler)
	{
		handler.visit(this);
	}

}
