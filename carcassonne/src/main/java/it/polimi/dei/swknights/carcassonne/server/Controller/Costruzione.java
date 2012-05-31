package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Class that implements the abstract idea of a human construction is used as
 * the base of the algorithm use to calculate victory points generated by
 * controlling constructions
 * 
 */

public abstract class Costruzione
{
	/**
	 * Default constructor Simply initialize a new construction whit at least
	 * one element
	 * 
	 * @param tessera
	 *            the first element of the construction
	 */

	public Costruzione(Tessera tessera)
	{
		this.contatoreSegnalini = this.inizializzaContatore();
		this.elementi = new HashSet<Tessera>();

		this.elementi.add(tessera);
	}

	/**
	 * Method that calculates the victory points generated from structure.
	 * 
	 * @return the point generated from this construction by the player
	 */
	
	public Map<Color, Integer> getPunteggi(boolean costruzioneCompletata)
	{
		List<Color> controllori = this.controllataDa();
		Map<Color, Integer> mappaPunteggi = this.inizializzaContatore();
		for(Color colore : controllori)
		{
			mappaPunteggi.put(colore, this.getPuntiCostruzione(costruzioneCompletata));
		}
		return mappaPunteggi;
	}
	
	/**
	 * Method used to join two constructions
	 * 
	 * @param costruzione
	 *            The construction to be joined with
	 */

	public void joinCostruzioni(Costruzione costruzione)
	{
		this.elementi.addAll(costruzione.elementi);
		for (Color colore : ColoriGioco.getListaColori())
		{
			int numeroSegnalini = this.contatoreSegnalini.get(colore)
					+ costruzione.contatoreSegnalini.get(colore);
			this.contatoreSegnalini.put(colore, numeroSegnalini);
		}
	}

	/**
	 * Method used to add a marker to this construction
	 * 
	 * @param segnalino
	 *            The marker to be added to this construction
	 */

	public void addSegnalino(Color coloresegnalino)
	{
		int numSegnalini = this.contatoreSegnalini.get(coloresegnalino);
		numSegnalini++;
		this.contatoreSegnalini.put(coloresegnalino, numSegnalini);
	}

	/**
	 * Method used to retrive the players that owns this construction
	 * 
	 * @return the list of the colors of the player controlling this
	 *         construction
	 */

	public List<Color> controllataDa()
	{
		int max = 1;
		List<Color> controllori = new ArrayList<Color>();
		for (Entry<Color, Integer> entryColore : this.contatoreSegnalini.entrySet())
		{
			int numeroSegnalini = entryColore.getValue();
			if (numeroSegnalini > max)
			{
				max = numeroSegnalini;
			}
		}
		for (Entry<Color, Integer> entryColore : this.contatoreSegnalini.entrySet())
		{
			if (entryColore.getValue() == max)
			{
				controllori.add(entryColore.getKey());
			}
		}
		return controllori;
	}

	public int contaElementi()
	{
		return this.elementi.size();
	}

	@Override
	public String toString()
	{
		String s = " tessere componenti :";
		for (Tessera t : elementi)
		{
			s = s + t.toString();
		}
		s = s + "segnalini :";
		for (Color segnalino : this.contatoreSegnalini.keySet())
		{
			s = s + segnalino.toString();
		}
		return s;
	}

	protected abstract int getPuntiCostruzione(boolean costruzioneCompletata);
	
	protected int getSize()
	{
		return this.elementi.size();
	}

	private Map<Color, Integer> inizializzaContatore()
	{
		Map<Color, Integer> contatore = new HashMap<Color, Integer>();
		for (Color colore : ColoriGioco.getListaColori())
		{
			contatore.put(colore, 0);
		}
		return contatore;
	}

	private Set<Tessera>		elementi;
	
	private Map<Color, Integer>	contatoreSegnalini;
}
