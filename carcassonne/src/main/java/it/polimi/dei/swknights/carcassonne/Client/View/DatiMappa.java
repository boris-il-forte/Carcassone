package it.polimi.dei.swknights.carcassonne.Client.View;

import java.util.HashMap;
import java.util.Map;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;

public class DatiMappa
{
	public DatiMappa(Coordinate min, Coordinate max)
	{
		this.creaBoundingMap(min, max);	
	}
	
	private void creaBoundingMap(Coordinate min, Coordinate max)
	{
		this.boundingMap = new HashMap<PuntoCardinale, Integer>();
		this.boundingMap.put(PuntoCardinale.nord, min.getY());
		this.boundingMap.put(PuntoCardinale.sud, max.getY());
		this.boundingMap.put(PuntoCardinale.ovest, min.getX());
		this.boundingMap.put(PuntoCardinale.est, max.getX());
	}

	public Integer getAltezza()
	{
		return getMaxA(PuntoCardinale.sud) - getMaxA(PuntoCardinale.nord);
	}
	
	public Integer getLarghezza()
	{
		return getMaxA(PuntoCardinale.est) - getMaxA(PuntoCardinale.ovest);
	}
	
	public int getMaxA(PuntoCardinale puntoCardinale)
	{
		return this.boundingMap.get(puntoCardinale);
	}
	
	private Map<PuntoCardinale, Integer> boundingMap;
	
}
