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

	public Link(boolean[] links)
	{
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
		Bussola agoBussola = Bussola.NE;

		if (puntoCardinale1 == PuntoCardinale.nord && puntoCardinale2 == PuntoCardinale.est)
			agoBussola = Bussola.NE;

		if (puntoCardinale1 == PuntoCardinale.nord && puntoCardinale2 == PuntoCardinale.ovest)
			agoBussola = Bussola.NW;

		if (puntoCardinale1 == PuntoCardinale.nord && puntoCardinale2 == PuntoCardinale.sud)
			agoBussola = Bussola.NS;

		if (puntoCardinale1 == PuntoCardinale.sud && puntoCardinale2 == PuntoCardinale.est)
			agoBussola = Bussola.SE;

		if (puntoCardinale1 == PuntoCardinale.sud && puntoCardinale2 == PuntoCardinale.ovest)
			agoBussola = Bussola.SW;

		if (puntoCardinale1 == PuntoCardinale.est && puntoCardinale2 == PuntoCardinale.ovest)
			agoBussola = Bussola.WE;

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
