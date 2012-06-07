package it.polimi.dei.swknights.carcassonne.Server.Controller;

import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The aim of this class is give to its user information about the buildings on
 * cards
 * 
 * @author dave
 * 
 */
public class CartelloStradale
{
	/**
	 * Constructor that initializes the map used by the algorithm
	 * 
	 * @param mappaCostruzioni
	 */
	public CartelloStradale(Map<Costruzione, List<PuntoCardinale>> mappaCostruzioni)
	{
		this.mappaCostruzioni = mappaCostruzioni;

	}

	/**
	 * Method to keep updated information stored in this class
	 * 
	 * @param costruzioneAggregata
	 *            the new construction to be added
	 * @param costruzione
	 *            the old construction to be removed
	 */
	public void aggiorna(Costruzione costruzioneAggregata, Costruzione costruzione)
	{

		List<PuntoCardinale> listaPunti = this.mappaCostruzioni.remove(costruzione);
		this.mappaCostruzioni.put(costruzioneAggregata, listaPunti);
	}

	/**
	 * Method to retrieve the information stored and updated by this class
	 * 
	 * @return a map that binds CardinalPoints to constructions
	 */

	public Map<PuntoCardinale, Costruzione> getIndicazioni()
	{
		Map<PuntoCardinale, Costruzione> indicazioni = new HashMap<PuntoCardinale, Costruzione>();
		for (Entry<Costruzione, List<PuntoCardinale>> entry : this.mappaCostruzioni.entrySet())
		{

			for (PuntoCardinale punto : entry.getValue())
			{
				indicazioni.put(punto, entry.getKey());
			}
		}
		return indicazioni;
	}

	private Map<Costruzione, List<PuntoCardinale>>	mappaCostruzioni;

}
