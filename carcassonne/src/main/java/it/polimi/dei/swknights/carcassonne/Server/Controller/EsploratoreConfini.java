package it.polimi.dei.swknights.carcassonne.Server.Controller;

import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * The aim of this class is get the neighbours of the last placed card. More
 * formally its aim is get the 0 to 4 "points of contact" of the card, either if
 * they are empyt (nothing or grass), either if they are not (eg street or city)
 * 
 * @author mainly Dave and Edo
 * 
 */
public class EsploratoreConfini
{
	public EsploratoreConfini(Coordinate coordinateTessera, ModuloModel model)
	{
		this.confini = new HashMap<Costruzione, List<ConfineTessera>>();
		this.confinanti = new HashMap<Costruzione, List<ConfineTessera>>();
		this.getEmptyAndFullNeighbours(coordinateTessera, model);
	}

	/**
	 * gets the empty neighbour of the card
	 * 
	 * @return a map that associates a building with its empty neighbours
	 */
	public Map<Costruzione, List<ConfineTessera>> getViciniVuoti()
	{
		return this.confini;
	}

	/**
	 * gets the ectual neighbours of the card (e.g. a part of city)
	 * 
	 * @return
	 */
	public Map<Costruzione, List<ConfineTessera>> getConfinantiScoperti()
	{
		return this.confinanti;
	}

	/**
	 * gets costructions and free space around the card
	 * 
	 * @return a set of costructions, some of them could be null if the
	 *         neighbour is empty or is grass
	 */
	public Set<Costruzione> getCostruzioni()
	{
		Set<Costruzione> semiSetCostruzioni1 = new HashSet<Costruzione>(this.confini.keySet());
		Set<Costruzione> semiSetCostruzioni2 = new HashSet<Costruzione>(this.confinanti.keySet());
		semiSetCostruzioni1.addAll(semiSetCostruzioni2);
		return semiSetCostruzioni1;
	}
	
	/**
	 * Getter method, retrives the information produced by the algorithm
	 * @return the map of all cardinal points of each construction
	 */

	public Map<Costruzione, List<PuntoCardinale>> getMappaCostruzioni()
	{
		final boolean scambia = true;
		Map<Costruzione, List<PuntoCardinale>> mappaCostruzioni = new HashMap<Costruzione, List<PuntoCardinale>>();
		Set<Costruzione> costruzioni = this.getCostruzioni();
		for (Costruzione costruzione : costruzioni)
		{
			List<PuntoCardinale> listaPunti = new ArrayList<PuntoCardinale>();
			List<ConfineTessera> confiniCostruzione = this.confini.get(costruzione);
			List<ConfineTessera> confinantiCostruzione = this.confinanti.get(costruzione);
			listaPunti.addAll(this.getPunticardinaliDi(confiniCostruzione, !scambia));
			listaPunti.addAll(this.getPunticardinaliDi(confinantiCostruzione, scambia));
			mappaCostruzioni.put(costruzione, listaPunti);
		}
		return mappaCostruzioni;
	}

	private List<PuntoCardinale> getPunticardinaliDi(List<ConfineTessera> confini, boolean scambia)
	{
		List<PuntoCardinale> lista = new ArrayList<PuntoCardinale>();
		for (ConfineTessera confine : confini)
		{
			PuntoCardinale punto = confine.getPuntoCardinale();
			punto = scambia ? punto.opposto() : punto;
			lista.add(punto);
		}
		return lista;
	}

	/**
	 * gets empty neighbours: free space around the card where nobody placed
	 * nothing and full neighbours: taken space around the card where someone
	 * put another card
	 * 
	 * @param coordinateTessera
	 * @param model
	 * @see ConfineTessera
	 */

	// confini = vuoti confinanti = pieni
	private void getEmptyAndFullNeighbours(Coordinate coordinateTessera, ModuloModel model)
	{
		Tessera tessera;
		try
		{

			tessera = model.getTessera(coordinateTessera);
			Map<Costruzione, List<PuntoCardinale>> mapCostruzioni;
			/*
			 * costruzioni aggregate della tessera con relative liste di punti
			 * cardinali (1 solo per nn aggreg)
			 */
			mapCostruzioni = tessera.getMapCostruzioniPunti();
			for (Entry<Costruzione, List<PuntoCardinale>> entryCostruzione : mapCostruzioni.entrySet())
			// per ogni costruzione aggregata della tessera guarda i vicini
			{
				List<ConfineTessera> listaConfinante = new ArrayList<ConfineTessera>();
				List<ConfineTessera> listaConfini = new ArrayList<ConfineTessera>();
				/*
				 * la costruzione aggregata nella tessera 1 o + confini es. una
				 * città a NE ha 2 confini: N e E
				 */
				for (PuntoCardinale puntoCardinale : mapCostruzioni.get(entryCostruzione.getKey()))
				{
					Coordinate coordinateConfinante = coordinateTessera.getCoordinateA(puntoCardinale);
					try
					{ // vicini pieni
						Tessera tesseraConfinante = model.getTessera(coordinateConfinante);
						ConfineTessera confinante = tesseraConfinante.getConfineA(puntoCardinale.opposto());
						listaConfinante.add(confinante);
					}
					catch (TesseraNonTrovataException e)
					{ // vicini vuoti

						ConfineTessera confine = tessera.getConfineA(puntoCardinale);
						listaConfini.add(confine);
					}
				}
				this.confinanti.put(entryCostruzione.getKey(), listaConfinante);
				this.confini.put(entryCostruzione.getKey(), listaConfini);
			}

		}
		catch (TesseraNonTrovataException e1)
		{
			throw new AssertionError(e1);
		}
	}

	private Map<Costruzione, List<ConfineTessera>>	confinanti;

	private Map<Costruzione, List<ConfineTessera>>	confini;

}
