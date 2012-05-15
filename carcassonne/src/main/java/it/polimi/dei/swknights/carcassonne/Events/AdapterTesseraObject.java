package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;


public class AdapterTesseraObject extends AdapterTessera
{
	public AdapterTesseraObject(Tessera tessera)
	{
		this.tessera = tessera; //TODO clona! (per eventi immutabili)
	}
	
	@Override
	public String toProtocolString()
	{
		return this.tessera.toString();
	}

	private Tessera tessera;
}