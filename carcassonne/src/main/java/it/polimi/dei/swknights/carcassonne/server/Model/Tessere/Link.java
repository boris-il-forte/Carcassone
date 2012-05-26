package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import java.util.Arrays;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;

/**
 * That class represent all the connections on a card e.g. a street that cross
 * the card
 * 
 * @author Edo & Dave
 * 
 */
public class Link
{
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
		for (Bussola direzione : Bussola.values())
		{
			direzioni[direzione.toInt()] = links[direzione.toInt()];
		}
	}

	public Link(boolean NS, boolean NE, boolean NW, boolean WE, boolean SE, boolean SW)
	{
		this.direzioni[Bussola.NS.toInt()] =NS;
		this.direzioni[Bussola.NE.toInt()] =NE;
		this.direzioni[Bussola.NW.toInt()] =NW;
		this.direzioni[Bussola.WE.toInt()] =WE;
		this.direzioni[Bussola.SE.toInt()] =SE;
		this.direzioni[Bussola.SW.toInt()] =SW;
	}

	/**
	 * Returns whether two Elements on two given Cardinal Points are connected
	 * 
	 * @param puntoCardinale1
	 * @param puntoCardinale2
	 * @return
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
			return direzioni[agoBussola.toInt()];
		}
	}

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

	private boolean[]	direzioni	= new boolean[Bussola.NUMERO_DIREZIONI];
	
	private static final int LINKS_NUMBER = 6;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 43;
		int result = 1;
		result = prime * result + Arrays.hashCode(direzioni);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (! (obj instanceof Link )) return false;
		Link other = (Link) obj;
		if (!Arrays.equals(direzioni, other.direzioni)) return false;
		return true;
	}

}
