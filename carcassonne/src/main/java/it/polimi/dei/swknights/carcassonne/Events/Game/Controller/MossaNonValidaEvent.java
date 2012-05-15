package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.ComandoView;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

public class MossaNonValidaEvent extends ControllerEvent
{

	public MossaNonValidaEvent(Object source)
	{
		super(source);
		
	}

	@Override
	protected void setComando()
	{
		this.messaggio = MessaggiController.move_not_valid;

	}

	private static final long	serialVersionUID	= 3592976018587414189L;

}
