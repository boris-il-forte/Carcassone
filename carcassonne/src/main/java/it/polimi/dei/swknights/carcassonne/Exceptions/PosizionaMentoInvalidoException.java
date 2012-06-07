package it.polimi.dei.swknights.carcassonne.Exceptions;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

public class PosizionaMentoInvalidoException extends Exception
{
	public PosizionaMentoInvalidoException(Coordinate coordinate)
	{
		super("Le coordinate : " + coordinate + "non sono valide per il posizionamento del segnalino");
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 946087634433014784L;

}
