package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;

import java.util.HashMap;
import java.util.Map;

public class Vicinato
{
	public Vicinato(Boolean nord, Boolean sud, Boolean ovest, Boolean est)
	{
		this.mappaVicinato = new HashMap<PuntoCardinale, Boolean>();
		this.mappaVicinato.put(PuntoCardinale.nord, nord);
		this.mappaVicinato.put(PuntoCardinale.sud, sud);
		this.mappaVicinato.put(PuntoCardinale.ovest, ovest);
		this.mappaVicinato.put(PuntoCardinale.est, est);
	}
	
	public Vicinato(Boolean haVicini)
	{
		this(haVicini,haVicini,haVicini,haVicini);
	}

	public Boolean haVicinoA(PuntoCardinale puntoCardinale)
	{
		return this.mappaVicinato.get(puntoCardinale);
	}
	
	private Map<PuntoCardinale, Boolean>	mappaVicinato;
}
