package it.polimi.dei.swknights.carcassonne.Util;

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
		return this.mappaPunteggi.get(colore);
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
		int punteggio = this.mappaPunteggi.get(colore) + punti;
		this.mappaPunteggi.put(colore, punteggio);
	}

	public Set<Entry<Color, Integer>> entrySet()
	{
		return this.mappaPunteggi.entrySet();
	}

	public Set<Color> keySet()
	{
		return this.mappaPunteggi.keySet();
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
	
	private Map<Color, Integer> mappaPunteggi;
	
}
