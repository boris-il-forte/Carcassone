package it.polimi.dei.swknights.carcassonne.exceptions;

public class finitiColoriDisponibiliException extends Exception
{

	public finitiColoriDisponibiliException()
	{
		super("Sono finiti i colori disponibili, pertanto non è " +
				"possibile creare altri giocatori !");
	}
	private static final long serialVersionUID = 13534543L;

}
