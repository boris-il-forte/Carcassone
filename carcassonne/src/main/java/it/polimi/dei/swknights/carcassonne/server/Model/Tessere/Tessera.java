package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Controller.ConfineTessera;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;

import java.util.ArrayList;
import java.util.Arrays;
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
	 * @return: a list containing the Costruction of the card
	 */
	public Map<Costruzione, List<PuntoCardinale>> getCostruzioni()
	{

		Map<Costruzione, List<PuntoCardinale>> mappaCostruzioniPunti = new HashMap<Costruzione, List<PuntoCardinale>>();

		Map<Costruzione, PuntoCardinale> mappaCostruzioni = lati.getMappaCostruzioniPrimitive(this);

		List<Costruzione> listCostruzione = new ArrayList<Costruzione>(mappaCostruzioni.keySet());
		Costruzione tempAggregato1 = null;
		Costruzione tempAggregato2 = null;
		boolean[] inglobati = { false, false, false, false };

		int i = 0, j = 0;
		for (i = 0; i < listCostruzione.size() - 1; i++)
		{
			if (inglobati[i] == true) continue;
			List<PuntoCardinale> puntiCardinali = new ArrayList<PuntoCardinale>();
			tempAggregato1 = listCostruzione.get(i);

			for (j = i + 1; j < listCostruzione.size() - 1; j++)
			{
				puntiCardinali.add(mappaCostruzioni.get(tempAggregato1));
				if (isConnected(listCostruzione.get(i), listCostruzione.get(j), mappaCostruzioni))
				{
					inglobati[j] = true;
					puntiCardinali.add(mappaCostruzioni.get(tempAggregato2));
					tempAggregato2 = listCostruzione.get(j);
					tempAggregato1.joinCostruzioni(tempAggregato2);
				}
			}

			mappaCostruzioniPunti.put(tempAggregato1.getCopy(this), puntiCardinali);
		}

		return mappaCostruzioniPunti;
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

	// volendo qua andrebbero i metodi astratti per la gestione del monastero...

	public abstract String toString();

	// volendo qua andrebbero i metodi astratti per la gestione del monastero...

	protected int[] getNumerazioni()
	{
		int[] numerazione = new int[PuntoCardinale.NUMERO_DIREZIONI];
		int counter;
		int stradaCounter = 0;
		int cittaCounter = 0;
		List<PuntoCardinale> puntiDaProvare = new ArrayList<PuntoCardinale>(Arrays.asList(PuntoCardinale
				.values()));
		for (PuntoCardinale punto1 : puntiDaProvare)
		{
			switch (this.lati.getTipoElementoInDirezione(punto1))
			{
				case strada:
					counter = ++stradaCounter;
					break;
				case citta:
					counter = ++cittaCounter;
				default:
					continue;
			}
			for(PuntoCardinale punto2 : puntiDaProvare)
			{
				if(this.isConnected(punto1, punto2))
				{
					puntiDaProvare.remove(punto2);
					numerazione[punto2.toInt()] = counter;
				}
			}
		}
		return null;
	}

	private boolean isConnected(PuntoCardinale puntoCardinale1, PuntoCardinale puntoCardinale2)
	{
		if (puntoCardinale1 == puntoCardinale2)
			return true;
		else return this.link.areConnected(puntoCardinale1, puntoCardinale2);
	}

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
