package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

/**
 * Event to be triggered when the game finishes that is when no more cards are available
 * @author edoardopasi & dave
 *
 */

public class FinePartitaEvent extends ControllerEvent
{

	public FinePartitaEvent(Object source)
	{
		super(source);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setComando()
	{
		this.messaggio = MessaggiController.end;

	}

	
	private static final long	serialVersionUID	= 7228959705104329672L;

}
