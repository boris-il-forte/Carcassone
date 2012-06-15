package it.polimi.dei.swknights.carcassonne.Server.Controller;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Segnalino;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class provide an algorithm to calculate Constructions victory points the
 * algorithm is a O(n) algorithm in the worst case, were n is the number of the
 * cards of the structure each iteration cost O(1) as the card has only 4
 * possible sides the single iteration is called n times, with n the number of
 * card inserted in the whole game in each iteration the algorithm calculate all
 * the constructions in the card. This class has the Cartographer suffix because
 * it also creates abstract structures to represent full streets and cities and
 * so has to deal with the map.
 */

public class ContatoreCartografo
{
	/**
	 * Default constructor for Contapunti
	 * 
	 * @param model
	 *            game area necessary to get model data
	 */

	public ContatoreCartografo(ModuloModel model)
	{
		this.model = model;
		this.cartaGeografica = new CartaGeografica();
	}

	/**
	 * Observer method
	 * 
	 * @return true if there are completed constructions
	 */

	public boolean areCostruzioniCompletate()
	{
		return this.cartaGeografica.areCostruzioniCompletate();
	}

	/**
	 * Getter method
	 * 
	 * @return the set of completed constructions
	 */

	public Set<Costruzione> getCostruzioniCompletate()
	{
		this.completate = this.cartaGeografica.getCostruzioniCompletate();
		return this.completate;
	}

	/**
	 * Getter method
	 * 
	 * @return the score made in this turn by the palyers
	 */

	public Punteggi getPunteggioTurno()
	{
		Punteggi punteggiAggregati = this.getPunteggiAggregati(this.completate, COSTRUZIONE_COMPLETATA);
		this.completate = null;
		return punteggiAggregati;
	}

	/**
	 * Getter method
	 * 
	 * @return the final turn score
	 */

	public Punteggi getPunteggioFinale()
	{
		Set<Costruzione> costruzioni = this.cartaGeografica.getCostruzioni();
		return this.getPunteggiAggregati(costruzioni, !COSTRUZIONE_COMPLETATA);
	}

	/**
	 * This method updates algorithm's data for each new card inserted
	 * 
	 * @param coordinateTessera
	 *            The coordinates of the last card put on the table
	 */

	public void riceviCoordinateTessera(Coordinate coordinateTessera)
	{
		EsploratoreConfini esploratore = new EsploratoreConfini(coordinateTessera, this.model);
		Set<Costruzione> costruzioni = esploratore.getCostruzioni();
		Map<Costruzione, List<ConfineTessera>> mapConfinanti = esploratore.getConfinantiScoperti();
		Map<Costruzione, List<ConfineTessera>> mapConfini = esploratore.getViciniVuoti();
		this.cartello = new CartelloStradale(esploratore.getMappaCostruzioni());
		for (Costruzione costruzione : costruzioni)
		{
			List<ConfineTessera> nuoviConfini = mapConfini.get(costruzione);
			List<ConfineTessera> confinanti = mapConfinanti.get(costruzione);
			Costruzione costruzioneAggregata = this.getCostruzioneAggregata(confinanti, costruzione);
			this.aggiungiNuoviConfini(nuoviConfini, costruzioneAggregata);
			this.cartaGeografica.aggiornaCompletate(costruzioneAggregata);
			this.cartello.aggiorna(costruzioneAggregata, costruzione);
		}
	}

	/**
	 * This method add a marker to the specified border of the last tile
	 * 
	 * @param colore
	 *            the color of the current player
	 * @param puntoCardinale
	 *            the border the player choose for adding the tile
	 */

	public void addSegnalino(Segnalino segnalino, PuntoCardinale puntoCardinale)
	{
		Debug.printMappaPuCo( this.getMapCostruzioniUltimaTessera());
		Map<PuntoCardinale, Costruzione > mapCostruzPunto = this.getMapCostruzioniUltimaTessera();
		Costruzione costruzione = mapCostruzPunto.get(puntoCardinale);
		costruzione.addSegnalino(segnalino);
	}

	/**
	 * Retrives the indications produced by CartelloStradale
	 * 
	 * @return a map thath binds the cardinal points to the tile constructions
	 * @see CartelloStradale
	 */
	public Map<PuntoCardinale, Costruzione> getMapCostruzioniUltimaTessera()
	{
		return this.cartello.getIndicazioni();
	}

	private Punteggi getPunteggiAggregati(Set<Costruzione> costruzioni, boolean costruzioneCompletata)
	{
		Punteggi punteggi = new Punteggi();
		for (Costruzione costruzione : costruzioni)
		{
			Punteggi punteggiParziali = costruzione.getPunteggi(costruzioneCompletata);
			punteggi.addPunteggi(punteggiParziali);
		}
		return punteggi;
	}

	private Costruzione getCostruzioneAggregata(List<ConfineTessera> listaConfinanti,
			Costruzione nuovoPezzoCostruzione)
	{
		Costruzione pezzo = nuovoPezzoCostruzione;
		for (ConfineTessera confinante : listaConfinanti)
		{
			Costruzione aggregata = this.cartaGeografica.getCostruzioneAggregata(pezzo, confinante);
			pezzo = aggregata;
		}
		return pezzo;
	}

	private void aggiungiNuoviConfini(List<ConfineTessera> nuoviConfini, Costruzione costruzione)
	{
		for (ConfineTessera nuovoConfine : nuoviConfini)
		{
			this.cartaGeografica.put(nuovoConfine, costruzione);
		}
	}

	private ModuloModel				model;

	private CartaGeografica			cartaGeografica;

	private CartelloStradale		cartello;

	private Set<Costruzione>		completate;

	private static final boolean	COSTRUZIONE_COMPLETATA	= true;

}
