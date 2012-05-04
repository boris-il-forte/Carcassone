package it.polimi.dei.swknights.carcassonne.Exceptions;

public class RigaNonTrovataException extends Exception
{
	public RigaNonTrovataException(Integer riga)
	{
		super("Non esiste la riga n " + riga);
	}
	private static final long serialVersionUID = -4902334967575966538L;
	
}
