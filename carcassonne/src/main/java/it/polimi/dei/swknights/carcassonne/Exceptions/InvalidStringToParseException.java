package it.polimi.dei.swknights.carcassonne.Exceptions;

public class InvalidStringToParseException extends Exception
{
	public InvalidStringToParseException(String invalidString, Throwable cause)
	{
		super(invalidString, cause);
	}

	public InvalidStringToParseException(String invalidString)
	{
		super(invalidString);
	}

	private static final long	serialVersionUID	= 6719771396116270766L;

}
