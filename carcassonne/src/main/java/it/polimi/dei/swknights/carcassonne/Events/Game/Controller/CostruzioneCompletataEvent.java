package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.ControllerHandler;

import java.util.Map;

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

	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);
		
	}

	public  Map<AdapterTessera, Coordinate>  getTessereAggiornate()
	{
		return this.tessereAggiornate;

	}

	private  Map<AdapterTessera, Coordinate> tessereAggiornate;
	private static final long		serialVersionUID	= -7907111208766111881L;

}
