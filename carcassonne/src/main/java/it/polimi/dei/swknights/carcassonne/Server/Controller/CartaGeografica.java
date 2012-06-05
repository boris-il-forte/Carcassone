package it.polimi.dei.swknights.carcassonne.Server.Controller;

import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to keep track the complex building in the game board
 * like streets or cities
 * @author edoardopasi
 *
 */
public class CartaGeografica
{
	public CartaGeografica()
	{
		this.mappaConfini = new HashMap<ConfineTessera, Costruzione>();
		this.mappaCostruzioni = new HashMap<Costruzione, List<ConfineTessera>>();
		this.costruzioniCompletate = new HashSet<Costruzione>();
	}

	public Costruzione getCostruzioneAggregata(Costruzione pezzoCostruzione, ConfineTessera confinante)
	{
		Costruzione costruzioneConfinante = this.mappaConfini.get(confinante);
		costruzioneConfinante.joinCostruzioni(pezzoCostruzione);
		this.aggiornaConfini(confinante, costruzioneConfinante);
		return costruzioneConfinante;
	}

	public Set<Costruzione> getCostruzioni()
	{
		return this.mappaCostruzioni.keySet();
	}

	public Set<Costruzione> getCostruzioniCompletate()
	{
		Set<Costruzione> completate = this.costruzioniCompletate;
		this.costruzioniCompletate.clear();
		return completate;
	}

	public void put(ConfineTessera nuovoConfine, Costruzione costruzione)
	{
		mappaConfini.put(nuovoConfine, costruzione);
		List<ConfineTessera> listaConfini = this.mappaCostruzioni.get(costruzione);
		if (listaConfini == null)
		{
			listaConfini = new ArrayList<ConfineTessera>();
			mappaCostruzioni.put(costruzione, listaConfini);
		}
		listaConfini.add(nuovoConfine);
	}

	public boolean areCostruzioniCompletate()
	{
		return this.costruzioniCompletate.size() > 0;
	}

	private void aggiornaConfini(ConfineTessera confine, Costruzione nuovaCostruzione)
	{
		Costruzione costruzione = this.mappaConfini.remove(confine);
		List<ConfineTessera> listaConfini = this.mappaCostruzioni.remove(costruzione);
		listaConfini.remove(confine);
		if (listaConfini.isEmpty())
		{
			this.costruzioniCompletate.add(nuovaCostruzione);
		}
		else
		{
			this.mappaCostruzioni.put(nuovaCostruzione, listaConfini);
		}
	}

	private Map<ConfineTessera, Costruzione>		mappaConfini;

	private Map<Costruzione, List<ConfineTessera>>	mappaCostruzioni;

	private Set<Costruzione>						costruzioniCompletate;
}
