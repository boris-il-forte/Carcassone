package it.polimi.dei.swknights.carcassonne.Exceptions;

public class ColoreNonPresenteException extends Exception
{
	public ColoreNonPresenteException(String colore)
	{
		super("Colore non presente" + colore);
	}

	private static final long	serialVersionUID	= 1105146406377280578L;

}
