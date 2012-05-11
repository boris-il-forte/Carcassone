package it.polimi.dei.swknights.carcassonne.server.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;
/**
 * The aim of this class is get the neighbours of the last placed card.
 * More formally its aim is get the 0 to 4 "points of contact" of the card, either
 * if they are empyt (nothing or grass), either if they are not (eg street or city)  
 * 
 * @author mainly Dave and Edo 
 *
 */
public class EsploratoreConfini
{
	public EsploratoreConfini(Coordinate coordinateTessera, AreaDiGioco cartaGeografica)
	{
		this.confini = new HashMap<Costruzione, List<ConfineTessera>>();
		this.confinanti = new HashMap<Costruzione, List<ConfineTessera>>();
		this.getEmptyAndFullNeighbours(coordinateTessera, cartaGeografica);
	}
	/**
	 * gets the empty neighbour of the card
	 * @return   a map that associates a building with its empty neighbours
	 */
	public Map<Costruzione, List<ConfineTessera>> getViciniVuoti()
	{
		return this.confini;
	}

	/**
	 * getss the ectual neighbours of the card (e.g. a part of city) 
	 * @return
	 */
	public Map<Costruzione, List<ConfineTessera>> getConfinantiScoperti()
	{
		return this.confinanti;
	}
	
	/**
	 * gets empty neighbours: free space around the card where nobody placed nothing
	 * and full neighbours: taken space around the card where someone put another card
	 * @param coordinateTessera
	 * @param cartaGeografica
	 * @see ConfineTessera
	 */
	
	//confini = vuoti   confinanti = pieni   (ovvio no?)
	private void getEmptyAndFullNeighbours(Coordinate coordinateTessera,
			AreaDiGioco cartaGeografica)
	{
		Tessera tessera;
		try
		{
			tessera = cartaGeografica.getTessera(coordinateTessera); //se c'è è confinante se no confine
			Map<Costruzione, List<PuntoCardinale>> mapCostruzioni;
			mapCostruzioni = tessera.getCostruzioni(); //costruzioni aggregate della tessera con relative liste di punti cardinali (1 solo per nn aggreg)
			for (Costruzione costruzione : mapCostruzioni.keySet())
				//per ogni costruzione aggregata della tessera guarda i vicini
			{
				List<ConfineTessera> listaConfinante = new ArrayList<ConfineTessera>();
				List<ConfineTessera> listaConfini = new ArrayList<ConfineTessera>();
				//la costruzione aggregata nella tessera 1 o + confini
				// es. una città a NE ha 2 confini: N e E
				for(PuntoCardinale puntoCardinale : mapCostruzioni.get(costruzione)) 
				{  
					Coordinate coordinateConfinante = coordinateTessera.getCoordinateA(puntoCardinale);
					try
					{ //vicini pieni
						Tessera tesseraConfinante = cartaGeografica.getTessera(coordinateConfinante);
						ConfineTessera confinante = tesseraConfinante.getVicino(puntoCardinale.opposto());
						listaConfinante.add(confinante);
					} 
					catch (TesseraNonTrovataException e)
					{ //vicini vuoti
	
						ConfineTessera confine = tessera.getVicino(puntoCardinale);
						listaConfini.add(confine);
					}
				}
				this.confinanti.put(costruzione, listaConfinante);
				this.confini.put(costruzione, listaConfini);
			}

		} catch (TesseraNonTrovataException e1)
		{
			e1.printStackTrace();
			System.exit(-1);
		}
	}

	private Map<Costruzione, List<ConfineTessera>> confinanti;

	private Map<Costruzione, List<ConfineTessera>> confini;

	/**
	 * gets costructions and free space around the card
	 * @return a set of costructions, some of them could be null
	 * if the neighbour is empty or is grass
	 */	
	public Set<Costruzione> getCostruzioni()
	{
		Set<Costruzione> semiSetCostruzioni1 = this.confini.keySet();
		Set<Costruzione> semiSetCostruzioni2 = this.confinanti.keySet();
		semiSetCostruzioni1.addAll(semiSetCostruzioni2);
		return semiSetCostruzioni1;
	}
}
