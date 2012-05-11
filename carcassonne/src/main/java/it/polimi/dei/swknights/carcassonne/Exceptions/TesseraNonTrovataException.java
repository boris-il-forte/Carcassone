package it.polimi.dei.swknights.carcassonne.Exceptions;

import it.polimi.dei.swknights.carcassonne.Coordinate;

public class TesseraNonTrovataException extends Exception
{
	public TesseraNonTrovataException(Integer coordinataX)
	{
		super("La tessera alla coordinata x=" + coordinataX + " non è stata trovata");
	}

	public TesseraNonTrovataException(Coordinate coordinate)
	{
		super("La tessera alla coordinata (" + coordinate.getX() + ", " + coordinate.getY() + ") non è stata trovata");
	}

	private static final long	serialVersionUID	= 2844279070241861687L;

}
