package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AggiornaMappaGui
{
	public AggiornaMappaGui(List<EntryTessera> listaTessere, Coordinate coordinateNordOvest,
			Coordinate coordinateRelativeSE)
	{
		this.coordinateNordOvest = coordinateNordOvest;
		this.larghezza = coordinateRelativeSE.getX();
		this.altezza = coordinateRelativeSE.getY();
		this.coordinateNordOvest = coordinateNordOvest;
		this.creaMappaTessere(listaTessere);
	}

	public boolean hasNextTessera()
	{
		return this.tessereIterator.hasNext();
	}

	public Entry<String, Integer> nextTessera()
	{
		return this.tessereIterator.next();
	}

	private void creaMappaTessere(List<EntryTessera> listaTessere)
	{
		Map<String, Integer> mappaTessere = new HashMap<String, Integer>();
		for (EntryTessera entryTessera : listaTessere)
		{
			Coordinate coordinateTessera = entryTessera.getCoordinate();
			String stringaTessera = entryTessera.getTessera().toProtocolString();
			Integer numeroCasella = this.traduciCoordinate(coordinateTessera);
			mappaTessere.put(stringaTessera, numeroCasella);
		}
		this.tessereIterator = mappaTessere.entrySet().iterator();

	}

	private Integer traduciCoordinate(Coordinate coordinateTessera)
	{
		int x = coordinateTessera.getX();
		int y = coordinateTessera.getY();
		x -= this.coordinateNordOvest.getX();
		y -= this.coordinateNordOvest.getY();
		if (x <= this.larghezza && y <= this.altezza)
		{
			return x + y * this.larghezza;
		}
		else
		{ 
			throw new IllegalArgumentException("coordinate tessera " + coordinateTessera
					+ "sono fuori dal Bounding box, larghezza: " + this.larghezza +" altezza: "+this.altezza
					+ " CoordinateNO " + this.coordinateNordOvest);
		}

	}

	private Coordinate							coordinateNordOvest;
	
	private Integer								larghezza;
	
	private Integer								altezza;
	
	private Iterator<Entry<String, Integer>>	tessereIterator;

}
