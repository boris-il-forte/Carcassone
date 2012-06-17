package it.polimi.dei.swknights.carcassonne.Exceptions;

/**
 * Exception thrown when is passed an invalit string to a parser
 * @author edoardopasi & dave
 * 
 */
public class InvalidStringToParseException extends Exception
{
	/**
	 * Constructor
	 * @param invalidString the invalid string
	 * @param cause the exception that point out that the string is not valid
	 */
	public InvalidStringToParseException(String invalidString, Throwable cause)
	{
		super(invalidString, cause);
	}

	/**
	 * Constructor
	 * @param invalidString the invalid string
	 */
	public InvalidStringToParseException(String invalidString)
	{
		super(invalidString);
	}

	private static final long	serialVersionUID	= 6719771396116270766L;

}
