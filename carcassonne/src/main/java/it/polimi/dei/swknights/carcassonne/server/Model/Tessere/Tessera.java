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

public abstract class Tessera
{
	Tessera(Lati lati, Link link)
	{
		this.lati = lati;
		this.link = link;
		Map<String, String> m = new HashMap<String, String>();
		m.remove(null);
	}

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
		Elemento elementoSuo = this.lati.getTipoElementoInDirezione(puntoCardinale.opposto());

		return (elementoMio == elementoSuo);
	}

	/**
	 * gets the aggregated costructions on the card e.g a street that crosses
	 * from north to south the card
	 * 
	 * @return a list containing the Costruction of the card
	 */
	public Map<Costruzione, List<PuntoCardinale>> getCostruzioni()
	{

		Map<Costruzione, List<PuntoCardinale>> mappaCostruzioniPunti = new HashMap<Costruzione, List<PuntoCardinale>>();
		Map<Costruzione, PuntoCardinale> mappaCostruzioni = lati.getMappaCostruzioniPrimitive(this);
		List<Costruzione> listCostruzione = new ArrayList<Costruzione>(mappaCostruzioni.keySet());

		for(Costruzione costruzione: listCostruzione)
		{
			List<PuntoCardinale> puntiConnessi = this.puntiConnessiACostruzione(costruzione, mappaCostruzioni);
			if(mappaCostruzioniPunti.values().contains(puntiConnessi) == false)
			{
				mappaCostruzioniPunti.put(costruzione, puntiConnessi);
			
			}
		}
			

		return mappaCostruzioniPunti;
	}
	
	private List<PuntoCardinale> puntiConnessiACostruzione(Costruzione c, Map<Costruzione, PuntoCardinale> mapCostruzPunto)
	{
		List<PuntoCardinale> puntiConnessi = new ArrayList<PuntoCardinale>();
		PuntoCardinale puntoDaEsaminare = mapCostruzPunto.get(c);
		for(PuntoCardinale p : PuntoCardinale.values())
		{
			if ( this.link.areConnected(p, puntoDaEsaminare) )
			{
				puntiConnessi.add(p);
			}
		}
		
		return puntiConnessi;
		
	}
	
	//TOODO: oppure mi faccio dare la mappa dall'altra parte da link??
	private Map<PuntoCardinale, Costruzione> PuntoCostruzione  (Map<Costruzione, PuntoCardinale> mappaCostruzioni)
	{
		Map<PuntoCardinale, Costruzione> mappaPuntoCostruzione = new HashMap<PuntoCardinale, Costruzione>();
		List<Costruzione> listCostruzione = new ArrayList<Costruzione>(mappaCostruzioni.keySet());
		for( Costruzione c : listCostruzione)
		{
			for(PuntoCardinale p : PuntoCardinale.values())
			{
				if(mappaCostruzioni.get(c) == p)
				{
					mappaPuntoCostruzione.put(p, c);
				}
			}
		}
			
		return mappaPuntoCostruzione;
	}

	/**
	 * Returns the neighbour of the given cardinal point or null if the
	 * neighbour is empty
	 * 
	 * @param puntoCardinale
	 * @return the neighbour or null if empty
	 */
	public ConfineTessera getVicino(PuntoCardinale puntoCardinale)
	{
		Elemento elemento = this.lati.getTipoElementoInDirezione(puntoCardinale);
		return new ConfineTessera(this, elemento);
	}
	/**
	 * 
	 * @param marker the marker to be placed
	 * @param puntoCardinale where place the marker
	 */
	public void setSegnalino(Segnalino segnalino, PuntoCardinale puntoCardinale)
	{
		this.lati.setSegnalino(segnalino,puntoCardinale);
	}

	// volendo qua andrebbero i metodi astratti per la gestione del monastero...

	public abstract String toString();

	// volendo qua andrebbero i metodi astratti per la gestione del monastero...


	private boolean isConnected(Costruzione costruzione1, Costruzione costruzione2,
			Map<Costruzione, PuntoCardinale> mappaCostruzioni)
	{
		PuntoCardinale puntoCardinale1 = mappaCostruzioni.get(costruzione1);
		PuntoCardinale puntoCardinale2 = mappaCostruzioni.get(costruzione2);
		return this.link.areConnected(puntoCardinale1, puntoCardinale2);
	}

	protected final Lati	lati;

	protected final Link	link;

}
