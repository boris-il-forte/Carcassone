package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.Game.ComandoView;

public class TileEvent extends ViewEvent
{

	public TileEvent(Object source, Color colore, PuntoCardinale punto)
	{
		super(source);
		this.puntoDestinazione = punto;
		this.color = colore;
	}

	@Override
	protected void setComando()
	{
		this.comando = ComandoView.tile;
	}

	public String getColoreSegnalino()
	{
		return this.color.toString();
	}

	public PuntoCardinale getPuntoDestinazione()
	{
		return this.puntoDestinazione;
	}

	private final Color color ;
	private final PuntoCardinale	puntoDestinazione;

	private static final long		serialVersionUID	= 2085506187547788810L;

}
