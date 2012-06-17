package it.polimi.dei.swknights.carcassonne.Exceptions;

/**
 * Exception thrown when a not valid move is tried to be done
 * @author edoardopasi & dave
 * 
 */
public class MossaNonValidaException extends Exception
{
	/**
	 * default constructor
	 * @param message the string stating why the move is not valid
	 */
	public MossaNonValidaException(String message)
	{
		super(message);
	}

	private static final long	serialVersionUID	= 920182706143832535L;

}
