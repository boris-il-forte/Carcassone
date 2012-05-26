package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import java.util.List;
import java.util.Map;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.DecoraTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

/**
 * Event to be triggered when a streat or a city is completed
 * 
 * @author edoardopasi & dave
 * 
 */
public class CostruzioneCompletataEvent extends ControllerEvent
{

	public CostruzioneCompletataEvent(Object source, Map<AdapterTessera, Coordinate> tessereAggiornate)
	{
		super(source);
		this.setComando(MessaggiController.costruction);
		this.tessereAggiornate = tessereAggiornate;
	}

	public  Map<AdapterTessera, Coordinate>  getTessereAggiornate()
	{
		return this.tessereAggiornate;

	}

	private  Map<AdapterTessera, Coordinate> tessereAggiornate;
	private static final long		serialVersionUID	= -7907111208766111881L;

}
