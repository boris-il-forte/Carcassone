package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;

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
	 * @param areaDiGioco
	 *            game area necessary to get model data
	 */

	public ContatoreCartografo(AreaDiGioco areaDiGioco)
	{
		this.areaDiGioco = areaDiGioco;
		this.cartaGeografica = new CartaGeografica();
	}

	/**
	 * This method updates algorithm's data for each new card inserted
	 * 
	 * @param coordinateTessera
	 *            The coordinates of the last card put on the table
	 */

	public void riceviCoordinateTessera(Coordinate coordinateTessera)
	{
		EsploratoreConfini esploratore = new EsploratoreConfini(coordinateTessera, this.areaDiGioco);
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
			this.cartello.aggiorna(costruzioneAggregata, costruzione);
		}
	}

	public Map<PuntoCardinale, Costruzione> getMapCostruzioniUltimaTessera()
	{
		return this.cartello.getIndicazioni();
	}

	private Costruzione getCostruzioneAggregata(List<ConfineTessera> listaConfinanti,
			Costruzione nuovoPezzoCostruzione)
	{
		for (ConfineTessera confinante : listaConfinanti)
		{
			Costruzione costruzioneConfinante = this.cartaGeografica.getCostruzioneAggregata(
					nuovoPezzoCostruzione, confinante);
			nuovoPezzoCostruzione = costruzioneConfinante;

		}
		return nuovoPezzoCostruzione;
	}

	private void aggiungiNuoviConfini(List<ConfineTessera> nuoviConfini, Costruzione costruzione)
	{
		for (ConfineTessera nuovoConfine : nuoviConfini)
		{
			this.cartaGeografica.put(nuovoConfine, costruzione);
		}
	}

	private AreaDiGioco			areaDiGioco;

	private CartaGeografica		cartaGeografica;

	private CartelloStradale	cartello;

}
