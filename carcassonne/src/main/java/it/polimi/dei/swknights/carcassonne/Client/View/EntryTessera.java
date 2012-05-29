package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
/**
 * This class just joins data needed to printer to print the map
 * in CLI mode
 * Data are:  coordinates, adapter card  and neighbourhood of the card
 * @author edoardopasi & dave
 *
 */
public class EntryTessera
{
	/**
	 * Data are:  coordinates, adapter card  and neighbourhood of the card
	 * @param coordinate
	 * @param tessera
	 * @param vicinato
	 */
	EntryTessera(Coordinate coordinate, AdapterTessera tessera, Vicinato vicinato)
	{
		this.coordinate = coordinate;
		this.tessera = tessera;
		this.vicinato = vicinato;
	}
	
	public Coordinate getCoordinate()
	{
		return this.coordinate;
	}
	
	public AdapterTessera getTessera()
	{
		return this.tessera;
	}
	
	public Vicinato getVicinato()
	{
		return this.vicinato;
	}

	private Coordinate	coordinate;
	private Vicinato	vicinato;
	private AdapterTessera	tessera;

}
