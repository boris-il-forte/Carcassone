package it.polimi.dei.swknights.carcassonne.Exceptions;

/**
 * exception thrown when user ask for a color that's not one of the game ones
 * 
 * @author dave
 * 
 */
public class ColoreNonTrovatoException extends RuntimeException
{
	/**
	 * Default constructor
	 * 
	 * @param colore
	 *            the color asked
	 */
	public ColoreNonTrovatoException(String message)
	{
		super(message);
	}

	private static final long	serialVersionUID	= -7096348429507565319L;

}
