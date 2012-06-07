package it.polimi.dei.swknights.carcassonne.Client.View;

import java.util.HashMap;
import java.util.Map;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

/**
 * This class describes the skeleton of the Command Line map
 * 
 */
public class DatiMappa
{
	public DatiMappa(Coordinate min, Coordinate max)
	{
		this.creaBoundingMap(min, max);
	}

	/**
	 * 
	 * @return height of the map
	 */
	public Integer getAltezza()
	{
		return this.getMaxA(PuntoCardinale.sud) - this.getMaxA(PuntoCardinale.nord) + 1;
	}

	/**
	 * 
	 * @return the width of the map
	 */
	public Integer getLarghezza()
	{
		return this.getMaxA(PuntoCardinale.est) - this.getMaxA(PuntoCardinale.ovest) + 1;
	}

	/**
	 * 
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
