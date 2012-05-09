package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class provide an algorithm to calculate Constructions victory points
 * the algorithm is a O(n) algorithm in the worst case, were n is the number of the cards of the structure
 * each iteration cost O(1) as the card has only 4 possible sides
 * the single iteration is called n times, with n the number of card inserted in the whole game
 * in each iteration the algorithm calculate all the constructions in the card.
 */

public class ContaPunti
{
	/**
	 * Default constructor for Contapunti
	 * 
	 * @param areaDiGioco
	 *            game area necessary to get model data
	 */
	
	public ContaPunti(AreaDiGioco areaDiGioco)
	{
		this.areaDiGioco = areaDiGioco;
		this.mappaConfiniCostruzione = new HashMap<Confine, Costruzione>();
	}

	/**
	 * This method updates algorithm's data for each new card inserted
	 * 
	 * @param coordinateTessera
	 *            The coordinates of the last card put on the table
	 */

	public void riceviCoordinateTessera(Coordinate coordinateTessera)
	{
		//TODO da ripensare in funzione del cartografo! fa un task comune a entrambi i metodi...
		Cartografo cartografo = new Cartografo(coordinateTessera, this.areaDiGioco);
		Set<Costruzione> costruzioni = cartografo.getCostruzioni();
		Map<Costruzione, List<Confine>> mapConfinanti = cartografo.getConfinantiCalcolati();
		Map<Costruzione, List<Confine>> mapConfini = cartografo.getConfiniCalcolati();
		
		for (Costruzione costruzione : costruzioni)
		{
			List<Confine> nuoviConfini = mapConfini.get(costruzione);
			List<Confine> confinanti = mapConfinanti.get(costruzione);
			costruzione = this.getCostruzioneAggregata(confinanti, costruzione);
			this.aggiungiNuoviConfini(nuoviConfini, costruzione);
		}
		// TODO manca controllo costruzione finita! (due righe) qui? penso di no!
	}

	private Costruzione getCostruzioneAggregata(List<Confine> listaConfinanti, Costruzione nuovoPezzoCostruzione)
	{
		for (Confine confinante : listaConfinanti)
		{
			Costruzione costruzioneConfinante = this.mappaConfiniCostruzione.get(confinante);
			costruzioneConfinante.joinCostruzioni(nuovoPezzoCostruzione);
			this.mappaConfiniCostruzione.remove(confinante);
			nuovoPezzoCostruzione = costruzioneConfinante;
			
		}
		return nuovoPezzoCostruzione;
	}

	private void aggiungiNuoviConfini(List<Confine> nuoviConfini, Costruzione costruzione)
	{
		for (Confine nuovoConfine : nuoviConfini)
		{
			this.mappaConfiniCostruzione.put(nuovoConfine, costruzione);
		}
	}

	private AreaDiGioco					areaDiGioco;

	private Map<Confine, Costruzione>	mappaConfiniCostruzione;

}
