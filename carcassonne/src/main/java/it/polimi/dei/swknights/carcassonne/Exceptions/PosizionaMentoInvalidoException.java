package it.polimi.dei.swknights.carcassonne.Exceptions;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

/**
 * Exception thrown when a player try to put a card in an invalid position
 * 
 * @author dave
 * 
 */
public class PosizionaMentoInvalidoException extends Exception
{
	/**
	 * Default constructor
	 * 
	 * @param coordinate
	 *            the coordinates where the player has tried to put the card in
	 */
	public PosizionaMentoInvalidoException(Coordinate coordinate)
	{
		super("Le coordinate : " + coordinate + "non sono valide per il posizionamento del segnalino");
	}

	private static final long	serialVersionUID	= 946087634433014784L;

}
