package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;

import java.util.HashMap;
import java.util.Map;

public class Vicinato
{
	public Vicinato(boolean nord, boolean sud, boolean est, boolean ovest)
	{
		this.mappaVicinato = new HashMap<PuntoCardinale, Boolean>();
		this.mappaVicinato.put(PuntoCardinale.nord, nord);
		this.mappaVicinato.put(PuntoCardinale.sud, sud);
		this.mappaVicinato.put(PuntoCardinale.ovest, ovest);
		this.mappaVicinato.put(PuntoCardinale.est, est);
	}
	
	public Vicinato(boolean haVicini)
	{
		this(haVicini,haVicini,haVicini,haVicini);
	}

	public Boolean haVicinoA(PuntoCardinale puntoCardinale)
	{
		return this.mappaVicinato.get(puntoCardinale);
	}
	
	private Map<PuntoCardinale, Boolean>	mappaVicinato;
}
