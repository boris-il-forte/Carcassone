package it.polimi.dei.swknights.carcassonne.server.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

/**
 * This class provide an algorithm to calculate Constructions victory points
 * the algorithm is a O(n) algorithm, were n is the number of the cards of the structure
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
		Map<Costruzione, List<Confine>> mapCostruzioni = this.getMappaCostruzioni(coordinateTessera);
		for (Costruzione costruzione : mapCostruzioni.keySet())
		{
			List<Confine> nuoviConfini = this.calcolaNuoviConfini(costruzione);
			List<Confine> confinanti = mapCostruzioni.get(costruzione);
			costruzione = this.getCostruzioneAggregata(confinanti, costruzione);
			this.aggiungiNuoviConfini(nuoviConfini, costruzione);
		}
		// TODO manca controllo costruzione finita! qui? penso di no!
	}

	private Costruzione getCostruzioneAggregata(List<Confine> listaConfini, Costruzione costruzione)
	{
		for (Confine confine : listaConfini)
		{
			Costruzione costruzioneConfinante = this.mappaConfiniCostruzione.get(confine);
			costruzioneConfinante.joinCostruzioni(costruzione);
			this.mappaConfiniCostruzione.remove(confine);
			costruzione = costruzioneConfinante;
		}
		return costruzione;
	}

	private void aggiungiNuoviConfini(List<Confine> nuoviConfini, Costruzione costruzione)
	{
		for (Confine nuovoConfine : nuoviConfini)
		{
			this.mappaConfiniCostruzione.put(nuovoConfine, costruzione);
		}
	}

	private List<Confine> calcolaNuoviConfini(Costruzione costruzione)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private Tessera getTessera(Coordinate coordinate)
	{
		try
		{
			Tessera tessera = areaDiGioco.getTessera(coordinate);
			return tessera;
		}
		catch (TesseraNonTrovataException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private List<Confine> calcolaConfinanti(Costruzione costruzione)
	{
		List<Confine> confini = new ArrayList<Confine>();
		// TODO da finire
		return confini;
	}

	private List<Costruzione> getCostruzioniTessera(Tessera tessera)
	{
		List<Costruzione> costruzioni = new ArrayList<Costruzione>();
		// TODO da finire
		return costruzioni;
	}

	private Map<Costruzione, List<Confine>> getMappaCostruzioni(Coordinate coordinateTessera)
	{
		Map<Costruzione, List<Confine>> mapCostruzioni = new HashMap<Costruzione, List<Confine>>();
		Tessera tessera = this.getTessera(coordinateTessera);
		List<Costruzione> costruzioni = this.getCostruzioniTessera(tessera);
		for (Costruzione costruzione : costruzioni)
		{
			List<Confine> confinanti = this.calcolaConfinanti(costruzione);
			mapCostruzioni.put(costruzione, confinanti);
		}
		return mapCostruzioni;
	}

	private AreaDiGioco					areaDiGioco;

	private Map<Confine, Costruzione>	mappaConfiniCostruzione;

}
