package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.Game.ComandoView;

public class PlaceEvent extends ViewEvent
{

	public PlaceEvent(Object source, Coordinate coordinate)
	{
		super(source);
		this.coordinateDestinazione = coordinate;
	}

	public Coordinate getCoordinateDestinazione()
	{
		return this.coordinateDestinazione;
	}

	@Override
	protected void setComando()
	{
		this.comando = ComandoView.place;
	
	}

	private final Coordinate	coordinateDestinazione;

	private static final long	serialVersionUID	= 2085506187547788810L;

}
