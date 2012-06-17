package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Color;
import java.io.Serializable;

/**
 * This class is used to have the abstraction of a Card but without some
 * information Used in communication not to pass too much data. It is possible
 * for the user of that class get: All information it can have from
 * {@link AdapterTessera} plus Player, Coordinates and Card Id
 * 
 * @author edoardopasi & dave
 * 
 */
public class DecoraTessera implements Serializable
{
	/**
	 * Default constructor
	 * 
	 * @param tessera
	 *            the event's card representation
	 * @param coordinate
	 *            the coordinates of the card
	 * @param giocatore
	 *            the current player
	 */
	public DecoraTessera(AdapterTessera tessera, Coordinate coordinate, Color giocatore)
	{
		this.tessera = tessera;
		this.coordinate = coordinate;
		this.giocatore = giocatore;
	}

	/**
	 * Getter method
	 * 
	 * @return the event's card representation
	 */
	public AdapterTessera getTessera()
	{
		return this.tessera;

	}

	/**
	 * Getter method
	 * 
	 * @return the current player's color
	 */
	public Color getGiocatore()
	{
		return this.giocatore;
	}

	/**
	 * getter method
	 * 
	 * @return the coordinates of the card
	 */
	public Coordinate getCoordinate()
	{
		return this.coordinate;
	}

	private Coordinate			coordinate;

	private Color				giocatore;

	private AdapterTessera		tessera;

	private static final long	serialVersionUID	= 4724116605542858814L;

}
