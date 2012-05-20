package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

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
	 * Array of six booleans to be passed in the following order
	 *  NS(0), NE(1), NW(2), WE(3), SE(4), SW(5);
	 * @param links
	 * @throws IllegalArgumentException
	 */
	public Link(boolean[] links) throws IllegalArgumentException
	{
		if(links.length != 6)
		{
			throw new IllegalArgumentException("You are supposed to pass six booleans to Link!");
		}
		for (Bussola direzione : Bussola.values())
		{
			direzioni[direzione.toInt()] = links[direzione.toInt()];
		}
	}
	/**
	 * Returns whether two Elements on two given Cardinal Points are connected
	 * @param puntoCardinale1
	 * @param puntoCardinale2
	 * @return
	 */
	public boolean areConnected(PuntoCardinale puntoCardinale1, PuntoCardinale puntoCardinale2)
	{
		Bussola agoBussola = Bussola.componi(puntoCardinale1, puntoCardinale2);
		return direzioni[agoBussola.toInt()];
	}
	
	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(Bussola direzione : Bussola.values())
		{
			boolean connesso = this.direzioni[direzione.toInt()];
			char valore = (connesso)?('1'):('0');
			stringBuilder.append(direzione.toString());
			stringBuilder.append('=');
			stringBuilder.append(valore);
			stringBuilder.append(' ');
		}
		final int lastCharIndex = stringBuilder.length()-1;
		stringBuilder.deleteCharAt(lastCharIndex);
		return stringBuilder.toString();
	}

	private boolean[]	direzioni	= new boolean[Bussola.NUMERO_DIREZIONI];

}
