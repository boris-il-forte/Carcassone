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

	/**
	 * Default constructor
	 * 
	 * @param source
	 *            the source of the event
	 * @param tessereAggiornate
	 *            the list of the tiles to be updated
	 * @param punteggi
	 *            the player's score
	 */
	public CostruzioneCompletataEvent(Object source, Map<AdapterTessera, Coordinate> tessereAggiornate,
			Punteggi punteggi)
	{
		super(source);
		this.tessereAggiornate = tessereAggiornate;
		this.punteggi = punteggi;
	}

	/**
	 * Getter for the score
	 * @return the score of the player
	 */
	public Punteggi getPunteggi()
	{
		return this.punteggi;
	}

	/**
	 * Accept method for visitor's pattern
	 */
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
