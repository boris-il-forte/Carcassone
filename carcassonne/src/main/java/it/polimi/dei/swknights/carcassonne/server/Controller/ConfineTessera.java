package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

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

	public ConfineTessera(Tessera tessera, Elemento lato)
	{
		this.tessera = tessera;
		this.lato = lato;
	}

	@Override
	public int hashCode()
	{
		final int numeroPrimo = 31;
		int hash = 1;
		hash = numeroPrimo * hash + this.lato.hashCode();
		hash = numeroPrimo * hash + this.tessera.hashCode();
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
			if (this.lato == confine.lato && this.tessera == confine.tessera) isEqual = true;
		}
		return isEqual;
	}

	private final Tessera	tessera;

	private final Elemento	lato;

}
