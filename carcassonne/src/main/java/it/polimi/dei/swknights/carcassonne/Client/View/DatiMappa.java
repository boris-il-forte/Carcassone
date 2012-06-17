package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.HashMap;
import java.util.Map;

/**
 * This class describes the skeleton of the Command Line map
 * 
 */
public class DatiMappa
{
	/**
	 * Default constructor. Set how much of the map should be displayed
	 * simultaneously
	 * 
	 * @param min
	 *            the minimum coordinate to be displayed
	 * @param max
	 *            the maximum coordinate to be displayed
	 */
	public DatiMappa(Coordinate min, Coordinate max)
	{
		this.creaBoundingMap(min, max);
	}

	/**
	 * Getter method
	 * @return height of the map
	 */
	public Integer getAltezza()
	{
		return this.getMaxA(PuntoCardinale.sud) - this.getMaxA(PuntoCardinale.nord) + 1;
	}

	/**
	 * Getter method
	 * @return the width of the map
	 */
	public Integer getLarghezza()
	{
		return this.getMaxA(PuntoCardinale.est) - this.getMaxA(PuntoCardinale.ovest) + 1;
	}

	/**
	 * Getter method
	 * @param puntoCardinale
	 *            the cardinal point of the requested max
	 * @return the coordinate of the max point in that given direction
	 */
	public int getMaxA(PuntoCardinale puntoCardinale)
	{
		return this.boundingMap.get(puntoCardinale);
	}

	private void creaBoundingMap(Coordinate min, Coordinate max)
	{
		this.boundingMap = new HashMap<PuntoCardinale, Integer>();
		this.boundingMap.put(PuntoCardinale.nord, min.getY());
		this.boundingMap.put(PuntoCardinale.sud, max.getY());
		this.boundingMap.put(PuntoCardinale.ovest, min.getX());
		this.boundingMap.put(PuntoCardinale.est, max.getX());
	}

	private Map<PuntoCardinale, Integer>	boundingMap;

}
