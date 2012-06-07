package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.Vicinato;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

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
		this.larghezza = coordinateRelativeSE.getX() + 1;
		this.altezza = coordinateRelativeSE.getY() + 1;
		this.creaMappaTessere(listaTessere);
	}

	public boolean hasNextTessera()
	{
		return this.tessereIterator.hasNext();
	}

	public boolean hasNextVuoto()
	{
		return this.vuotiIterator.hasNext();
	}

	public Entry<String, Integer> nextTessera()
	{
		return this.tessereIterator.next();
	}

	public Entry<Coordinate, Integer> nextVuoto()
	{
		return this.vuotiIterator.next();
	}

	private void creaMappaTessere(List<EntryTessera> listaTessere)
	{
		Map<String, Integer> mappaTessere = new HashMap<String, Integer>();
		Map<Coordinate, Integer> mappaViciniVuoti = new HashMap<Coordinate, Integer>();
		for (EntryTessera entryTessera : listaTessere)
		{
			Coordinate coordinateTessera = entryTessera.getCoordinate();
			String stringaTessera = entryTessera.getTessera().toProtocolString();
			Vicinato vicinato = entryTessera.getVicinato();
			this.aggiungiTessere(coordinateTessera, stringaTessera, mappaTessere);
			this.aggiungiViciniVuoti(coordinateTessera, vicinato, mappaViciniVuoti);
		}
		this.tessereIterator = mappaTessere.entrySet().iterator();
		this.vuotiIterator = mappaViciniVuoti.entrySet().iterator();
	}

	private void aggiungiTessere(Coordinate coordinateTessera, String stringaTessera,
			Map<String, Integer> mappaTessere)
	{
		Integer numeroCasella = this.traduciCoordinate(coordinateTessera);
		mappaTessere.put(stringaTessera, numeroCasella);
	}

	private void aggiungiViciniVuoti(Coordinate coordinateTessera, Vicinato vicinato,
			Map<Coordinate, Integer> mappaViciniVuoti)
	{
		for (PuntoCardinale puntoCardinale : PuntoCardinale.values())
		{
			if (!vicinato.haVicinoA(puntoCardinale))
			{
				Coordinate coordinateVuoti = coordinateTessera.getCoordinateA(puntoCardinale);
				Integer numeroVicino = this.traduciCoordinate(coordinateVuoti);
				mappaViciniVuoti.put(coordinateVuoti, numeroVicino);
			}
		}
	}

	private Integer traduciCoordinate(Coordinate coordinateTessera)
	{
		int x = coordinateTessera.getX();
		int y = coordinateTessera.getY();
		x -= this.coordinateNordOvest.getX();
		y -= this.coordinateNordOvest.getY();
		if (x <= this.larghezza && y <= this.altezza)
		{
			return x + this.larghezza * y;
		}
		else
		{
			throw new IllegalArgumentException("coordinate tessera " + coordinateTessera
					+ "sono fuori dal Bounding box, larghezza: " + this.larghezza + " altezza: "
					+ this.altezza + " CoordinateNO " + this.coordinateNordOvest);
		}

	}

	private Coordinate								coordinateNordOvest;

	private Integer									larghezza;

	private Integer									altezza;

	private Iterator<Entry<Coordinate, Integer>>	vuotiIterator;

	private Iterator<Entry<String, Integer>>		tessereIterator;

}
