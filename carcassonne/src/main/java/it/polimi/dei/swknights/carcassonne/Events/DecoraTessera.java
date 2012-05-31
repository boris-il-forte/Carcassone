package it.polimi.dei.swknights.carcassonne.Events;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
/**
 * This class is used to have the abstraction of a Card
 * but without some information
 * Used in communication not to pass too much data.
 * It is possible for the user of that class get:
 * All information it can have from {@link AdapterTessera} plus Player, Coordinates
 * and Card Id
 * @author edoardopasi & dave
 *
 */
public class DecoraTessera
{
	private int				idTessera;
	private Coordinate		coordinate;
	private Color			giocatore;
	private AdapterTessera	tessera;

	public DecoraTessera(AdapterTessera tessera, Coordinate coordinate, Color giocatore)
	{
		this.tessera = tessera;
		this.coordinate = coordinate;
		this.giocatore = giocatore;
	}

	public AdapterTessera getTessera()
	{
		return tessera;

	}

	public Color getGiocatore()
	{
		return giocatore;
	}

	public Coordinate getCoordinate()
	{
		return coordinate;
	}

	public int getIdTessera()
	{
		return idTessera;
	}

}
