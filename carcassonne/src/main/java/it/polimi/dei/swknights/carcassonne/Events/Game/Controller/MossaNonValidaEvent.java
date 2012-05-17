package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

/**
 * Event to be triggered when a user tries to perform a non valid move
 * @author edoardopasi & dave
 *
 */

public class MossaNonValidaEvent extends ControllerEvent
{

	public MossaNonValidaEvent(Object source)
	{
		super(source);
		this.setComando(MessaggiController.move_not_valid);
	}

	private static final long	serialVersionUID	= 3592976018587414189L;

}
