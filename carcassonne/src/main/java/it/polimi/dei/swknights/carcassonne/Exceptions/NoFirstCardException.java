package it.polimi.dei.swknights.carcassonne.Exceptions;

/**
 * Exception thrown when there is no first card in the deck
 * @author dave
 *
 */
public class NoFirstCardException extends Exception
{

	/**
	 * Default constructor
	 * @param message a message stating why this exception has to be thrown
	 */
	public NoFirstCardException(String message)
	{
		super(message);
	}

	private static final long	serialVersionUID	= -8230108219724161198L;

}
