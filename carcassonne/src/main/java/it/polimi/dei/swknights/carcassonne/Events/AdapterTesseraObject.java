package it.polimi.dei.swknights.carcassonne.Events;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Segnalino;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;

/**
 * A version of AdapterTessera thought to be used with RMI communication
 * 
 * @author edoardopasi & dave
 * @see AdapterTessera
 */

public class AdapterTesseraObject extends AdapterTessera
{
	/**
	 * Default constructor
	 * 
	 * @param tessera
	 *            the card to be passed in the events
	 */
	public AdapterTesseraObject(Tessera tessera)
	{
		this.tessera = tessera.clone();
	}

	/**
	 * Return the protocol string of this card
	 */
	@Override
	public String toProtocolString()
	{
		return this.tessera.toString();
	}

	/**
	 * Gets the marker color on this card
	 */
	@Override
	public Color getColorSegnalino()
	{
		Segnalino segnalino = this.tessera.getSegnalino();
		return (segnalino == null) ? null : segnalino.getColore();
	}

	private static final long	serialVersionUID	= -3083803275769710214L;

	private Tessera				tessera;
}
