package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Controller.ConfineTessera;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the abstract representation of the cards that can be placed on
 * the game table. Contains basic algorithms to control valid neighborhood, and
 * to retrieve the abstract constructions, to be used to calculate victory
 * points
 * 
 * @author edo & dave
 * 
 */

public abstract class Tessera implements Cloneable
{
	Tessera(Lati lati, Link link)
	{
		this.lati = lati;
		this.link = link;
		Map<String, String> m = new HashMap<String, String>();
		m.remove(null);
	}
	
	@Override
	public abstract Tessera clone();

	/**
	 * This method is used to make a clockwise rotation of the card
	 */
	public void ruota()
	{
		this.lati.ruota();
		this.link.ruota();
	}

	/**
	 * This method return whether the given card at the given position accepts
	 * the neighborhood of this card
	 * 
	 * @param tessera
	 *            the possible neighbor of the card that is being placed
	 * @param puntoCardinale
	 *            the position of the given card regard this card
	 * @return whether the card could be good neighbor
	 */

	public boolean buonVicino(Tessera tessera, PuntoCardinale puntoCardinale)
	{
		Elemento elementoMio = this.lati.getTipoElementoInDirezione(puntoCardinale);
		Elemento elementoSuo = tessera.lati.getTipoElementoInDirezione(puntoCardinale.opposto());

		return (elementoMio.equals(elementoSuo));
	}

	/**
	 * gets the aggregated costructions on the card e.g a street that crosses
	 * from north to south the card
	 * 
	 * @return a list containing the Costruction of the card
	 */
	public Map<Costruzione, List<PuntoCardinale>> getMapCostruzioniPunti()
	{

		Map<Costruzione, List<PuntoCardinale>> mappaCostruzioniPunti = new HashMap<Costruzione, List<PuntoCardinale>>();
		Map<Costruzione, PuntoCardinale> mappaCostruzioni = lati.getMappaCostruzioniPrimitive(this);
		List<Costruzione> listCostruzione = new ArrayList<Costruzione>(mappaCostruzioni.keySet());

		List<List<PuntoCardinale>> giaMesse = new ArrayList<List<PuntoCardinale>>();

		for (Costruzione costruzione : listCostruzione)
		{
			List<PuntoCardinale> puntiConnessi = this
					.puntiConnessiACostruzione(costruzione, mappaCostruzioni);

			if (!giaMesse.contains(puntiConnessi))
			{
				giaMesse.add(puntiConnessi);
				mappaCostruzioniPunti.put(costruzione, puntiConnessi);

			}
		}

		return mappaCostruzioniPunti;
	}

	/**
	 * Returns the neighbour of the given cardinal point The neighbout can be
	 * for instance: city + this card if there is a city in the given cardinal
	 * point grass + this card if there is nothing (grass) in the given cardinal
	 * poin
	 * 
	 * @param puntoCardinale
	 * @return the neighbour
	 */
	public ConfineTessera getConfineA(PuntoCardinale puntoCardinale)
	{
		return new ConfineTessera(this, puntoCardinale);
	}

	/**
	 * 
	 * @param marker
	 *            the marker to be placed
	 * @param puntoCardinale
	 *            where place the marker
	 */
	public void setSegnalino(Segnalino segnalino, PuntoCardinale puntoCardinale)
	{
		this.lati.setSegnalino(segnalino, puntoCardinale);
	}

	// volendo qua andrebbero i metodi astratti per la gestione del monastero...
	
	
	@Override
	public abstract String toString();

	// volendo qua andrebbero i metodi astratti per la gestione del monastero...

	private List<PuntoCardinale> puntiConnessiACostruzione(Costruzione c,
			Map<Costruzione, PuntoCardinale> mapCostruzPunto)
	{
		List<PuntoCardinale> puntiConnessi = new ArrayList<PuntoCardinale>();
		PuntoCardinale puntoDaEsaminare = mapCostruzPunto.get(c);
		for (PuntoCardinale p : PuntoCardinale.values())
		{
			if (this.link.areConnected(p, puntoDaEsaminare))
			{
				puntiConnessi.add(p);
			}
		}

		return puntiConnessi;

	}

	protected final Lati	lati;

	protected final Link	link;

}
