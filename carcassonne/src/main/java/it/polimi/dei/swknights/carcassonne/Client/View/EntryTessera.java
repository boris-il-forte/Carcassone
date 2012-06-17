package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

/**
 * This class just joins data needed to printer to print the map in CLI mode
 * Data are: coordinates, adapter card and neighbourhood of the card
 * 
 * @author edoardopasi & dave
 * 
 */
public class EntryTessera
{
	/**
	 * Data are: coordinates, adapter card and neighbourhood of the card
	 * 
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

	/**
	 * Getter method
	 * 
	 * @return the coordinates of the tile
	 */
	public Coordinate getCoordinate()
	{
		return this.coordinate;
	}

	/**
	 * Getter method
	 * 
	 * @return the tile
	 */
	public AdapterTessera getTessera()
	{
		return this.tessera;
	}

	/**
	 * getter method
	 * 
	 * @return the neighborhood of the tile
	 */
	public Vicinato getVicinato()
	{
		return this.vicinato;
	}

	private Coordinate		coordinate;
	
	private Vicinato		vicinato;
	
	private AdapterTessera	tessera;

}
