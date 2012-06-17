package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to represent the neighborhood of a card in CLI mode Just a
 * graphical tool
 * 
 * @author edoardopasi & dave
 * 
 */
public class Vicinato
{
	/**
	 * Default constructor. set up all the neighborhood variables.
	 * 
	 * @param nord
	 *            true if there is a neighbor at the north of this card
	 * @param sud
	 *            true if there is a neighbor at the south of this card
	 * @param ovest
	 *            true if there is a neighbor at the west of this card
	 * @param est
	 *            true if there is a neighbor at the est of this card
	 */
	public Vicinato(Boolean nord, Boolean sud, Boolean ovest, Boolean est)
	{
		this.mappaVicinato = new HashMap<PuntoCardinale, Boolean>();
		this.mappaVicinato.put(PuntoCardinale.nord, nord);
		this.mappaVicinato.put(PuntoCardinale.sud, sud);
		this.mappaVicinato.put(PuntoCardinale.ovest, ovest);
		this.mappaVicinato.put(PuntoCardinale.est, est);
	}

	/**
	 * Used to make a neighbourhood with either no one or a full usefull in the
	 * CLI representation
	 * 
	 * @param haVicini
	 */
	public Vicinato(Boolean haVicini)
	{
		this(haVicini, haVicini, haVicini, haVicini);
	}

	/**
	 * 
	 * @param puntoCardinale
	 * @return true if there is something at the given cardinal point, false
	 *         otherwise
	 */
	public Boolean haVicinoA(PuntoCardinale puntoCardinale)
	{
		return this.mappaVicinato.get(puntoCardinale);
	}

	private Map<PuntoCardinale, Boolean>	mappaVicinato;
}
