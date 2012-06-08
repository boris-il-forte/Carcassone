package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.util.Map;

/**
 * Event to be triggered when a streat or a city is completed
 * 
 * @author edoardopasi & dave
 * 
 */
public class CostruzioneCompletataEvent extends ControllerEvent
{

	public CostruzioneCompletataEvent(Object source, Map<AdapterTessera, Coordinate> tessereAggiornate,
			Punteggi punteggi)
	{
		super(source);
		this.tessereAggiornate = tessereAggiornate;
		this.punteggi = punteggi;
	}

	public Punteggi getPunteggi()
	{
		return this.punteggi;
	}

	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);

	}

	/**
	 * Gives the caller information to update all the Cards interested by the
	 * completation of the building
	 * 
	 * @return A Map associating each {@link AdapterTessera} to its
	 *         {@link Coordinate}
	 */
	public Map<AdapterTessera, Coordinate> getTessereAggiornate()
	{
		return this.tessereAggiornate;

	}

	private Punteggi						punteggi;
	private Map<AdapterTessera, Coordinate>	tessereAggiornate;
	private static final long				serialVersionUID	= -7907111208766111881L;

}
