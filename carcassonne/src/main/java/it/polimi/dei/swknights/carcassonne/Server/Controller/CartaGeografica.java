package it.polimi.dei.swknights.carcassonne.Server.Controller;

import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to keep track the complex building in the game board like
 * streets or cities
 * 
 * @author edoardopasi
 * 
 */
public class CartaGeografica
{
	/**
	 * Default Constructor for CartaGeografica. It only initializes the
	 * collections used by the object
	 */
	
	public CartaGeografica()
	{
		this.mappaConfini = new HashMap<ConfineTessera, Costruzione>();
		this.mappaCostruzioni = new HashMap<Costruzione, List<ConfineTessera>>();
		this.costruzioniCompletate = new HashSet<Costruzione>();
	}

	/**
	 * Retrives the union of the given construction towards the given border
	 * 
	 * @param pezzoCostruzione
	 *            the piece of construction we want to join
	 * @param confinante
	 *            the border considered for the join
	 * @return the joined construction
	 */

	public Costruzione getCostruzioneAggregata(Costruzione pezzoCostruzione, ConfineTessera confinante)
	{
		Costruzione costruzioneConfinante = this.mappaConfini.get(confinante);
		if(costruzioneConfinante == pezzoCostruzione)
		{
			this.gestisciAutoanello(confinante, costruzioneConfinante);
		}
		else
		{
			this.gestisciEstensione(pezzoCostruzione, costruzioneConfinante, confinante);
		}
		return costruzioneConfinante;
	}

	/**
	 * Getter
	 * 
	 * @return retrieves the construction key set
	 */

	public Set<Costruzione> getCostruzioni()
	{
		return this.mappaCostruzioni.keySet();
	}

	/**
	 * Getter
	 * 
	 * @return retrieves completed construction set
	 */

	public Set<Costruzione> getCostruzioniCompletate()
	{
		Set<Costruzione> completate = new HashSet<Costruzione>(this.costruzioniCompletate);
		this.costruzioniCompletate.clear();
		return completate;
	}

	/**
	 * add the construction to the completed ones if it is
	 * 
	 * @param costruzione
	 *            the construction to work with
	 */

	public void aggiornaCompletate(Costruzione costruzione)
	{
		if (this.mappaCostruzioni.get(costruzione) == null)
		{
			this.costruzioniCompletate.add(costruzione);
		}
	}

	/**
	 * Setter
	 * 
	 * @param nuovoConfine
	 *            a new border to be added
	 * @param costruzione
	 *            the corrispettive construction of the border
	 */

	public void put(ConfineTessera nuovoConfine, Costruzione costruzione)
	{
		this.mappaConfini.put(nuovoConfine, costruzione);
		List<ConfineTessera> listaConfini = this.mappaCostruzioni.get(costruzione);
		if (listaConfini == null)
		{
			listaConfini = new ArrayList<ConfineTessera>();
			this.mappaCostruzioni.put(costruzione, listaConfini);
		}
		listaConfini.add(nuovoConfine);
	}

	/**
	 * Observer method
	 * 
	 * @return whereas there are completed constructions
	 */

	public boolean areCostruzioniCompletate()
	{
		return this.costruzioniCompletate.size() > 0;
	}

	private void gestisciAutoanello(ConfineTessera confinante, Costruzione costruzioneConfinante)
	{
		this.mappaConfini.remove(confinante);
		List<ConfineTessera> listaConfini = this.mappaCostruzioni.remove(costruzioneConfinante);
		listaConfini.remove(confinante);
		if(listaConfini.size()>0)
		{
			this.mappaCostruzioni.put(costruzioneConfinante, listaConfini);
		}	
	}

	private void gestisciEstensione(Costruzione pezzoCostruzione, Costruzione costruzioneConfinante, ConfineTessera confinante)
	{
		costruzioneConfinante.joinCostruzioni(pezzoCostruzione);
		List<ConfineTessera> confiniPezzo = this.aggiungiConfiniPezzo(pezzoCostruzione, costruzioneConfinante);
		this.aggiornaConfini(confinante, costruzioneConfinante, confiniPezzo);
		
	}

	private List<ConfineTessera> aggiungiConfiniPezzo(Costruzione pezzoCostruzione,
			Costruzione costruzioneConfinante)
	{
		List<ConfineTessera> confiniPezzo = this.mappaCostruzioni.get(pezzoCostruzione);
		if(confiniPezzo == null)
		{
			confiniPezzo = new ArrayList<ConfineTessera>();
		}
		for(ConfineTessera confine : confiniPezzo)
		{
			this.mappaConfini.put(confine, costruzioneConfinante);
		}
		return confiniPezzo;
	}

	private void aggiornaConfini(ConfineTessera confine, Costruzione nuovaCostruzione, List<ConfineTessera> confiniPezzo)
	{
		Costruzione costruzione = this.mappaConfini.remove(confine);
		List<ConfineTessera> listaConfini = this.mappaCostruzioni.remove(costruzione);
		listaConfini.addAll(confiniPezzo);
		listaConfini.remove(confine);
		
		if (!listaConfini.isEmpty())
		{
			this.mappaCostruzioni.put(nuovaCostruzione, listaConfini);
		}

	}

	private Map<ConfineTessera, Costruzione>		mappaConfini;

	private Map<Costruzione, List<ConfineTessera>>	mappaCostruzioni;

	private Set<Costruzione>						costruzioniCompletate;
}
