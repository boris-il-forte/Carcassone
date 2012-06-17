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

/**
 * Class that implements an iterator over the cards and the empty box the GUI
 * must display.
 * 
 * @author dave
 * 
 */
public class AggiornaMappaGui
{
	/**
	 * Deafault constructor
	 * 
	 * @param listaTessere
	 *            the list of cards to display
	 * @param coordinateNordOvest
	 *            the NW coordinate
	 * @param coordinateRelativeSE
	 *            the SE coordinate
	 */
	public AggiornaMappaGui(List<EntryTessera> listaTessere, Coordinate coordinateNordOvest,
			Coordinate coordinateRelativeSE)
	{
		this.coordinateNordOvest = coordinateNordOvest;
		this.larghezza = coordinateRelativeSE.getX() + 1;
		this.creaMappaTessere(listaTessere);
	}

	/**
	 * hasNext method like another iterator
	 * @return true if there is another card to display 
	 */
	public boolean hasNextTessera()
	{
		return this.tessereIterator.hasNext();
	}

	/**
	 * hasNext method like another iterator
	 * @return true if there is another empty position to display 
	 */
	public boolean hasNextVuoto()
	{
		return this.vuotiIterator.hasNext();
	}

	/**
	 * next  method like another iterator
	 * @return the next card to display
	 */
	public Entry<Integer, String> nextTessera()
	{
		return this.tessereIterator.next();
	}

	/**
	 * next  method like another iterator
	 * @return the next empty position to display
	 */
	public Entry<Coordinate, Integer> nextVuoto()
	{
		return this.vuotiIterator.next();
	}

	private void creaMappaTessere(List<EntryTessera> listaTessere)
	{
		Map<Integer, String> mappaTessere = new HashMap<Integer, String>();
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
			Map<Integer, String> mappaTessere)
	{
		Integer numeroCasella = this.traduciCoordinate(coordinateTessera);
		mappaTessere.put(numeroCasella, stringaTessera);
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
		return x + this.larghezza * y;
	}

	private Coordinate								coordinateNordOvest;

	private Integer									larghezza;

	private Iterator<Entry<Integer, String>>		tessereIterator;

	private Iterator<Entry<Coordinate, Integer>>	vuotiIterator;

}
