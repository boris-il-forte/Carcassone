package it.polimi.dei.swknights.carcassonne.server.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	}

	public Costruzione getCostruzioneAggregata(Costruzione pezzoCostruzione, ConfineTessera confinante)
	{
		Costruzione costruzioneConfinante = this.mappaConfini.get(confinante);
		//TOFIX : if costruz confinante == null    ?? (al momento errore !! )
		if (costruzioneConfinante == null)
		{
			System.out.println("confine non trovato " + confinante);
		}
		costruzioneConfinante.joinCostruzioni(pezzoCostruzione);
		this.aggiornaConfini(confinante, costruzioneConfinante);
		return costruzioneConfinante;
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

	private void aggiornaConfini(ConfineTessera confine, Costruzione nuovaCostruzione)
	{
		Costruzione costruzione = this.mappaConfini.remove(confine);
		List<ConfineTessera> listaConfini = this.mappaCostruzioni.remove(costruzione);
		this.mappaCostruzioni.put(nuovaCostruzione,listaConfini);
	}

	private Map<ConfineTessera, Costruzione>		mappaConfini;

	private Map<Costruzione, List<ConfineTessera>>	mappaCostruzioni;
}
