package it.polimi.dei.swknights.carcassonne.Util;

import it.polimi.dei.swknights.carcassonne.Exceptions.ColoreNonTrovatoException;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Punteggi
{
	public Punteggi()
	{
		this.mappaPunteggi = this.inizializza();
	}

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

	public void addPunteggi(Punteggi punteggiParziali)
	{
		for (Color colore : ColoriGioco.getListaColori())
		{
			this.addPunteggi(colore, punteggiParziali.get(colore));
		}
	}

	public void addPunteggi(Color colore, int punti)
	{
		if (punti < 0)
		{
			throw new IllegalArgumentException("non puoi avere un punteggio negativo!!");
		}
		else
		{
			int punteggio = this.mappaPunteggi.get(colore) + punti;
			this.mappaPunteggi.put(colore, punteggio);
		}
	}

	public Set<Entry<Color, Integer>> entrySet()
	{
		return this.mappaPunteggi.entrySet();
	}

	public Set<Color> keySet()
	{
		return this.mappaPunteggi.keySet();
	}

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

}
