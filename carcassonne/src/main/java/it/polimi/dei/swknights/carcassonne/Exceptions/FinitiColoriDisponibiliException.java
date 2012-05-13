package it.polimi.dei.swknights.carcassonne.Exceptions;

public class FinitiColoriDisponibiliException extends Exception
{

	public FinitiColoriDisponibiliException()
	{
		super("Sono finiti i colori disponibili, pertanto non è " + "possibile creare altri giocatori !");
	}

	private static final long	serialVersionUID	= 13534543L;

}
