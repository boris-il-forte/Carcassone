package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.Game.ComandoView;
import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;

public class TileEvent extends ViewEvent
{

	public TileEvent(Object source, Segnalino segnalino, PuntoCardinale punto)
	{
		super(source);
		this.segnalino = segnalino;
		this.puntoDestinazione = punto;
	}

	@Override
	protected void setComando()
	{
		this.comando = ComandoView.tile;
	}

	public String getColoreSegnalino()
	{
		return this.segnalino.getColore().toString();
	}

	public PuntoCardinale getPuntoDestinazione()
	{
		return this.puntoDestinazione;
	}

	private final Segnalino			segnalino;
	private final PuntoCardinale	puntoDestinazione;

	private static final long		serialVersionUID	= 2085506187547788810L;

}
