package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;

/**
 * Event to be triggered when a user tries to perform a non valid move
 * 
 * @author edoardopasi & dave
 * 
 */

public class MossaNonValidaEvent extends ControllerEvent
{

	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);
	
	}

	public MossaNonValidaEvent(Object source)
	{
		super(source);

	}

	private static final long	serialVersionUID	= 3592976018587414189L;

}
