package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

public class CostruzioneCompletataEvent extends ControllerEvent
{

	public CostruzioneCompletataEvent(Object source)
	{
		super(source);
		
	}

	@Override
	protected void setComando()
	{
		this.messaggio = MessaggiController.costruction;
	}

	
	private static final long	serialVersionUID	= -7907111208766111881L;

}
