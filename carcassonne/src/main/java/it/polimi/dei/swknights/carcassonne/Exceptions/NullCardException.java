package it.polimi.dei.swknights.carcassonne.Exceptions;

/**
 * Exception thrown when a null card is passed to something
 * @author dave
 *
 */
public class NullCardException extends RuntimeException
{
	/**
	 * Default constructor
	 * @param s a string that says something about this exception
	 */
	public NullCardException(String s)
	{
		super(s);
	}

	private static final long	serialVersionUID	= 3486013880316256170L;

}
