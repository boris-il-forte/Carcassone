package it.polimi.dei.swknights.carcassonne.Server.Model.Tessere;

import java.io.Serializable;

import it.polimi.dei.swknights.carcassonne.Util.Bussola;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

/**
 * That class represent all the connections on a card e.g. a street that cross
 * the card
 * 
 * @author Edo & Dave
 * 
 */
public class Link implements Cloneable, Serializable
{
	/**
	 * Array of six booleans to be passed in the following order NS(0), NE(1),
	 * NW(2), WE(3), SE(4), SW(5);
	 * 
	 * @param links
	 * @throws IllegalArgumentException
	 */
	public Link(boolean[] links) throws IllegalArgumentException
	{
		if (links.length != LINKS_NUMBER) { throw new IllegalArgumentException(
				"You are supposed to pass six booleans to Link!"); }
		this.direzioni = links.clone();
	}

	/**
	 * Clone method
	 */
	@Override
	public Link clone() throws CloneNotSupportedException
	{
		Link copia;
		copia = (Link) super.clone();
		copia.direzioni = this.direzioni.clone();
		return copia;
	}

	/**
	 * rotate the connections according to a clockwise 90° rotation
	 */
	public void ruota()
	{
		boolean tempSN = this.direzioni[Bussola.NS.ordinal()];
		this.direzioni[Bussola.NS.ordinal()] = this.direzioni[Bussola.WE.ordinal()];
		this.direzioni[Bussola.WE.ordinal()] = tempSN;
		boolean tempSE = this.direzioni[Bussola.SE.ordinal()];
		this.direzioni[Bussola.SE.ordinal()] = this.direzioni[Bussola.NE.ordinal()];
		this.direzioni[Bussola.NE.ordinal()] = this.direzioni[Bussola.NW.ordinal()];
		this.direzioni[Bussola.NW.ordinal()] = this.direzioni[Bussola.SW.ordinal()];
		this.direzioni[Bussola.SW.ordinal()] = tempSE;
	}

	/**
	 * Returns whether two Elements on two given Cardinal Points are connected
	 * 
	 * @param puntoCardinale1
	 * @param puntoCardinale2
	 * @return true if they are connected
	 */
	public boolean areConnected(PuntoCardinale puntoCardinale1, PuntoCardinale puntoCardinale2)
	{
		if (puntoCardinale1.equals(puntoCardinale2))
		{
			return true;
		}
		else
		{
			Bussola agoBussola = Bussola.componi(puntoCardinale1, puntoCardinale2);
			return this.direzioni[agoBussola.toInt()];
		}
	}

	/**
	 * String serialization of the card connections. uset in socket protocol
	 */
	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (Bussola direzione : Bussola.values())
		{
			boolean connesso = this.direzioni[direzione.toInt()];
			char valore = (connesso) ? ('1') : ('0');
			stringBuilder.append(direzione.toString());
			stringBuilder.append('=');
			stringBuilder.append(valore);
			stringBuilder.append(' ');
		}
		final int lastCharIndex = stringBuilder.length() - 1;
		stringBuilder.deleteCharAt(lastCharIndex);
		return stringBuilder.toString();
	}

	private boolean[]			direzioni			= new boolean[Bussola.NUMERO_DIREZIONI];

	private static final int	LINKS_NUMBER		= 6;

	private static final long	serialVersionUID	= -5768645230627759238L;

}
