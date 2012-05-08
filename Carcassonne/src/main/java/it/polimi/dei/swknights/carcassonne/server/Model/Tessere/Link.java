package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;

/**
 * That class represent all the connections on a card e.g. a street that cross the card
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
		boolean tempSN = this.SN;
		this.SN = this.OE;
		this.OE = tempSN;
		boolean tempSE = this.SE;
		this.SE = this.NE;
		this.NE = this.NO;
		this.NO = this.SO;
		this.SO = tempSE;

	}

	public Boolean SN;

	public Boolean SE;

	public Boolean OE;

	public Boolean SO;

	public Boolean NE;

	public Boolean NO;

	public Tessera myTessera;

	public boolean isConnected(PuntoCardinale puntoCardinale1, PuntoCardinale puntoCardinale2)
	{
		boolean linkNecessario = false;

		if (puntoCardinale1 == PuntoCardinale.nord && puntoCardinale2 == PuntoCardinale.est)
			linkNecessario = this.NE;

		if (puntoCardinale1 == PuntoCardinale.nord && puntoCardinale2 == PuntoCardinale.ovest)
			linkNecessario = this.NO;

		if (puntoCardinale1 == PuntoCardinale.nord && puntoCardinale2 == PuntoCardinale.sud)
			linkNecessario = this.SN;

		if (puntoCardinale1 == PuntoCardinale.sud && puntoCardinale2 == PuntoCardinale.est)
			linkNecessario = this.SE;

		if (puntoCardinale1 == PuntoCardinale.sud && puntoCardinale2 == PuntoCardinale.ovest)
			linkNecessario = this.SO;

		if (puntoCardinale1 == PuntoCardinale.est && puntoCardinale2 == PuntoCardinale.ovest)
			linkNecessario = this.OE;

		return linkNecessario;
	}

}