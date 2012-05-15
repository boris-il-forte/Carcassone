package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.ComandoView;


public class PlaceEvent extends ViewEvent {

	public PlaceEvent(Object source, AdapterTessera tessera, Coordinate coordinate) {
		super(source);
		this.tessera = tessera;
		this.coordinateDestinazione = coordinate;
	}

	@Override
	protected void setComando() {
		this.comando = ComandoView.place;
		
	}
	
	public Coordinate getCoordinateDestinazione()
	{
		return this.coordinateDestinazione;
	}
	public String getDescrizioneTessera()
	{
		return this.tessera.toProtocolString();
	}
	
	private  final AdapterTessera tessera;
	private  final Coordinate coordinateDestinazione;
	
	private static final long serialVersionUID = 2085506187547788810L;

}
