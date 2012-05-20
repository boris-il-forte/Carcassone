package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.Game.ComandoView;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.ControllerHandler;

/**
 * Event triggered by the view to notify that the current player wants to place
 * a marker on the just placed card
 * 
 * @author edoardopasi & dave
 * 
 */

public class TileEvent extends ViewEvent
{

	public TileEvent(Object source, Color colore, PuntoCardinale punto)
	{
		super(source);
		setComando(ComandoView.tile);
		this.puntoDestinazione = punto;
		this.color = colore;
	}

	/**
	 * the color of the marker he wants to place
	 * 
	 * @return
	 */
	public String getColoreSegnalino()
	{
		return this.color.toString();
	}

	public PuntoCardinale getPuntoDestinazione()
	{
		return this.puntoDestinazione;
	}

	private final Color				color;
	private final PuntoCardinale	puntoDestinazione;

	private static final long		serialVersionUID	= 2085506187547788810L;

	@Override
	public void accept(ControllerHandler handler)
	{
		handler.visit(this);
	}

}
