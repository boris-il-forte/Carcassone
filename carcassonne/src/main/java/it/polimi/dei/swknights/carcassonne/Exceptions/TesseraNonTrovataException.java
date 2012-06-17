package it.polimi.dei.swknights.carcassonne.Exceptions;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

/**
 * Thrown when the Card on the given Coordinate is not found
 * 
 * @author edoardopasi
 * 
 */
public class TesseraNonTrovataException extends Exception
{
	/**
	 * Default constructor
	 * 
	 * @param coordinate
	 *            the coordinates where the card was not found
	 */
	public TesseraNonTrovataException(Coordinate coordinate)
	{
		super("La tessera alla coordinata" + coordinate + "non è stata trovata");
	}

	private static final long	serialVersionUID	= 2844279070241861687L;

}
