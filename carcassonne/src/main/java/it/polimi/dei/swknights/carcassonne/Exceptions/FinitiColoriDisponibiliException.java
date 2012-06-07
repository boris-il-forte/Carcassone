package it.polimi.dei.swknights.carcassonne.Exceptions;

/**
 * No more colors are available, Typically thrown when more then 5 player try to
 * play
 * 
 * @author edoardopasi & dave
 * 
 */
public class FinitiColoriDisponibiliException extends Exception
{

	public FinitiColoriDisponibiliException()
	{
		super("Sono finiti i colori disponibili, pertanto non è " + "possibile creare altri giocatori !");
	}

	private static final long	serialVersionUID	= 13534543L;

}
