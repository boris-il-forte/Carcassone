package it.polimi.dei.swknights.carcassonne.Exceptions;

/**
 * Exception thrown when the game is over 
 * @author dave
 *
 */
public class PartitaFinitaException extends Exception
{

	/**
	 * Default constructor
	 */
	public PartitaFinitaException()
	{
		super("Le tessere sono terminate: La partita è conclusa");
	}

	private static final long	serialVersionUID	= 9013683308135792500L;

}
