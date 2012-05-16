package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
/**
 * Event to be triggered when a streat or a city is completed
 * @author edoardopasi & dave
 *
 */
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
