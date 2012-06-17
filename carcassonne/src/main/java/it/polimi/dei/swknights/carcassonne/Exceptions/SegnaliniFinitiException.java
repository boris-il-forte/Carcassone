package it.polimi.dei.swknights.carcassonne.Exceptions;

/**
 * Exception thrown when the players has no more marker but tries to play another one
 * @author dave
 *
 */
public class SegnaliniFinitiException extends Exception
{

	/**
	 * Default constructor
	 */
	public SegnaliniFinitiException()
	{
		super("Segnalini del giocatore finiti");
	}

	private static final long	serialVersionUID	= 4405032866451452294L;

}
