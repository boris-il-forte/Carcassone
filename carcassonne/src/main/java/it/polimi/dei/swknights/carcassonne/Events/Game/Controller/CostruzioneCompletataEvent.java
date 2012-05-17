package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import java.util.List;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
/**
 * Event to be triggered when a streat or a city is completed
 * @author edoardopasi & dave
 *
 */
public class CostruzioneCompletataEvent extends ControllerEvent
{

	public CostruzioneCompletataEvent(Object source, List<AdapterTessera> tessereAggiornate)
	{
		super(source);
		this.setComando(MessaggiController.costruction);
		this.tessereAggiornate = tessereAggiornate;
	}

	public List<AdapterTessera> getTessereAggiornate()
	{
		return this.tessereAggiornate;
	}
	private List<AdapterTessera> tessereAggiornate;
	private static final long	serialVersionUID	= -7907111208766111881L;

}
