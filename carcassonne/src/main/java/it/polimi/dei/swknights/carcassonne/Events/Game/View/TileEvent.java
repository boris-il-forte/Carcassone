package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ControllerHandler;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;

/**
 * Event triggered by the view to notify that the current player wants to place
 * a marker on the just placed card
 * 
 * @author edoardopasi & dave
 * 
 */

public class TileEvent extends ViewEvent
{

	/**
	 * Default constructor
	 * 
	 * @param source
	 *            the event source
	 * @param colore
	 *            the marker color
	 * @param punto
	 *            the cardinal point where the marker must be placed
	 */
	public TileEvent(Object source, Color colore, PuntoCardinale punto)
	{
		super(source);
		this.puntoDestinazione = punto;
		this.color = colore;
	}

	/**
	 * the color of the marker he wants to place
	 * 
	 * @return
	 */
	public Color getColoreSegnalino()
	{
		return this.color;
	}

	/**
	 * Getter method
	 * @return where the marker should be placed
	 */
	public PuntoCardinale getPuntoDestinazione()
	{
		return this.puntoDestinazione;
	}

	/**
	 * Accept method for visitor's pattern
	 */
	@Override
	public void accept(ControllerHandler handler)
	{
		handler.visit(this);
	}

	private final Color				color;

	private final PuntoCardinale	puntoDestinazione;

	private static final long		serialVersionUID	= 2085506187547788810L;

}
