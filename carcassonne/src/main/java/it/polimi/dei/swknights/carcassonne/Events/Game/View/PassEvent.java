package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.Events.Game.ComandiView;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.ControllerHandler;

/**
 * Event triggered by the view to notify that the current player does not want
 * to place a marker on just placed card
 * 
 * @author edoardopasi & dave
 * 
 */

public class PassEvent extends ViewEvent
{

	public PassEvent(Object source)
	{
		super(source);
		this.setComando(ComandiView.pass);
	}

	@Override
	public void accept(ControllerHandler handler)
	{
		handler.visit(this);
	}

	private static final long	serialVersionUID	= 2085506187547788810L;

}
