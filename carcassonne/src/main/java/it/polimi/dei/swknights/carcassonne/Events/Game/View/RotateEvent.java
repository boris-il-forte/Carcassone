package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.Events.Game.ComandoView;

/**
 * Event triggered by the view to notify that the current player 
 * wants to rotate the current card
 * @author edoardopasi & dave
 *
 */

public class RotateEvent extends ViewEvent
{

	public RotateEvent(Object source)
	{
		super(source);

	}

	@Override
	protected void setComando()
	{
		this.comando = ComandoView.rotate;

	}

	private static final long	serialVersionUID	= 2085506187547788810L;

}
