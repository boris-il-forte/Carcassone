package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
/**
 * Event to be triggered when a new turn begins
 * @author edoardopasi & dave
 *
 */


public class TurnoEvent extends ControllerEvent
{

	public TurnoEvent(Object source)
	{
		super(source);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setComando()
	{
		this.messaggio = MessaggiController.turn;
	}

	private static final long	serialVersionUID	= -8277130187657287093L;

}
