package it.polimi.dei.swknights.carcassonne.Util;

import it.polimi.dei.swknights.carcassonne.Exceptions.ColoreNonTrovatoException;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Class used to count points
 * 
 * @author dave
 * 
 */
public class Punteggi implements Serializable
{
	/**
	 * Default constructor
	 */
	public Punteggi()
	{
		this.mappaPunteggi = this.inizializza();
	}

	/**
	 * Getter method
	 * 
	 * @param colore
	 *            the color of the player you want to query
	 * @return the score of the selected player
	 */
	public Integer get(Color colore)
	{
		if (this.mappaPunteggi.get(colore) != null)
		{
			return this.mappaPunteggi.get(colore);
		}
		else
		{
			throw new ColoreNonTrovatoException(" non è stato possibile reperire il punteggio del colore : "
					+ colore.toString());
		}

	}

	/**
	 * Getter method
	 * 
	 * @return the list of player with the maximum score
	 */
	public List<Color> getVincitoriAttuale()
	{
		int massimo = 0;
		List<Color> papabili = new ArrayList<Color>();
		for (Entry<Color, Integer> entry : this.mappaPunteggi.entrySet())
		{
			int punteggio = entry.getValue();
			if (punteggio > massimo)
			{
				massimo = punteggio;
			}

		}

		for (Entry<Color, Integer> entry : this.mappaPunteggi.entrySet())
		{
			int punteggio = entry.getValue();
			if (punteggio == massimo)
			{
				papabili.add(entry.getKey());
			}

		}

		return papabili;
	}

	/**
	 * Method used to add points to all players
	 * 
	 * @param punteggiParziali
	 *            the map of scores to be added
	 */
	public void addPunteggi(Punteggi punteggiParziali)
	{
		for (Color colore : ColoriGioco.getListaColori())
		{
			this.addPunteggi(colore, punteggiParziali.get(colore));
		}
	}

	/**
	 * Method used to add point to some players
	 * 
	 * @param colore
	 *            the player's color
	 * @param punti
	 *            the score to add to the given player
	 */
	public void addPunteggi(Color colore, int punti)
	{
		if (punti < 0)
		{
			throw new IllegalArgumentException("non puoi avere un punteggio negativo!!");
		}
		else
		{
			this.modificaPunteggi(colore, punti);
		}
	}

	/**
	 * Getter method
	 * @return the entry set of the score map
	 */
	public Set<Entry<Color, Integer>> entrySet()
	{
		return this.mappaPunteggi.entrySet();
	}

	/**
	 * Getter method
	 * @return the player's color
	 */
	public Set<Color> keySet()
	{
		return this.mappaPunteggi.keySet();
	}

	/**
	 * String serialization of this score
	 */
	@Override
	public String toString()
	{
		StringBuilder stringaPunti = new StringBuilder();
		for (Entry<Color, Integer> entry : this.mappaPunteggi.entrySet())
		{
			stringaPunti.append(ColoriGioco.getName(entry.getKey()));
			stringaPunti.append(": ");
			stringaPunti.append(entry.getValue());
			stringaPunti.append("   ");
		}
		return stringaPunti.toString();

	}

	protected void modificaPunteggi(Color colore, int punti)
	{
		int punteggio = this.mappaPunteggi.get(colore) + punti;
		this.mappaPunteggi.put(colore, punteggio);
	}

	private Map<Color, Integer> inizializza()
	{
		Map<Color, Integer> contatore = new HashMap<Color, Integer>();
		for (Color colore : ColoriGioco.getListaColori())
		{
			contatore.put(colore, 0);
		}
		return contatore;
	}

	private Map<Color, Integer>	mappaPunteggi;

	private static final long	serialVersionUID	= 7226747197782984560L;

}
