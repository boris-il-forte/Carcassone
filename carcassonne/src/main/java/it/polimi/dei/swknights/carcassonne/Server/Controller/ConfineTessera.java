package it.polimi.dei.swknights.carcassonne.Server.Controller;

import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

/**
 * This class is used to get an abstraction of the neighbour of the construction
 * The neighbour can be full e.g. when hosting a street of a city or empty when
 * no card was placed there
 * 
 * @author dave
 * @version 1.1
 */

public class ConfineTessera
{
	/**
	 * Constructor to create a border of a construction
	 * 
	 * @param tessera
	 *            The card adjoining to this card
	 * @param lato
	 *            The element of the card considered
	 */

	public ConfineTessera(Tessera tessera, PuntoCardinale puntoCardinale)
	{
		this.tessera = tessera;
		this.puntoCardinale = puntoCardinale;
	}

	@Override
	public int hashCode()
	{
		final int numeroPrimo = 31;
		int hash = 1;
		hash = numeroPrimo * hash + this.tessera.hashCode();
		hash = numeroPrimo * hash + this.puntoCardinale.hashCode();
		return hash;
	}

	@Override
	/**
	 * two neighbours are equals iff the elements are of the same type 
	 * e.g. city and city,  and the card is the same
	 * more formally:  this.lato == confine.lato && this.card == confine.card
	 */
	public boolean equals(Object object)
	{
		boolean isEqual = false;
		if (object instanceof ConfineTessera)
		{
			ConfineTessera confine = (ConfineTessera) object;
			if (this.tessera == confine.tessera && this.puntoCardinale.equals(confine.puntoCardinale))
			{
				isEqual = true;
			}
		}
		return isEqual;
	}

	@Override
	public String toString()
	{
		return " card : " +  this.tessera.toString() +  " point: " + this.puntoCardinale.toString();
	}
	
	public PuntoCardinale getPuntoCardinale()
	{
		return this.puntoCardinale;
	}

	public Tessera getTessera()
	{
		return this.tessera;
	}

	private final Tessera			tessera;

	private final PuntoCardinale	puntoCardinale;

}
