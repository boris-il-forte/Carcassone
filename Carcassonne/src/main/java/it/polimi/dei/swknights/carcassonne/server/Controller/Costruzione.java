package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Costruzione
{
	public Costruzione()
	{
		this.listaSegnalini = new ArrayList<Segnalino>();
		this.dimensione = 1;
	}

	public void joinCostruzioni(Costruzione costruzione)
	{
		this.dimensione += costruzione.dimensione - this.contaPezziComuni(costruzione);
		this.listaSegnalini.addAll(costruzione.listaSegnalini);
	}

	public void addSegnalino(Segnalino segnalino)
	{
		this.listaSegnalini.add(segnalino);
	}

	public List<Color> controllataDa()
	{
		final int nuovo = 1; // TODO di troppo?
		Map<Color, Integer> contatore = this.inizializzaContatore();
		for (Segnalino segnalino : this.listaSegnalini)
		{
			Color colore = segnalino.getColore();
			int contato = contatore.get(colore) + nuovo;
			contatore.put(colore, contato);
		}
		return this.getListaControllori(contatore);
	}

	public abstract int contaPuntiGiocatore(Color colore);

	protected abstract int contaPezziComuni(Costruzione costruzione);

	private Map<Color, Integer> inizializzaContatore()
	{
		// TODO copia incolla è il male!
		Color colori[] = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK };
		final int vuoto = 0; // TODO di troppo?
		Map<Color, Integer> contatore = new HashMap<Color, Integer>();
		for (Color colore : colori)
			contatore.put(colore, vuoto);
		return contatore;
	}

	// TODO da valutare
	private List<Color> getListaControllori(Map<Color, Integer> contatore)
	{
		int max = 1;
		List<Color> controllori = new ArrayList<Color>();
		for (Color colore : contatore.keySet())
		{
			int numeroSegnalini = contatore.get(colore);
			if (numeroSegnalini > max) max = numeroSegnalini;
		}
		for (Color colore : contatore.keySet())
		{
			if (contatore.get(colore) == max) controllori.add(colore);
		}
		return controllori;
	}

	protected int				dimensione;

	private List<Segnalino>	listaSegnalini;
}
