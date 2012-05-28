package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

/**
 * A version of AdapterTessera thought to be used with RMI communication
 * 
 * @author edoardopasi & dave
 * @see AdapterTessera
 */

public class AdapterTesseraObject extends AdapterTessera
{
	public AdapterTesseraObject(Tessera tessera)
	{
		this.tessera = (Tessera) tessera.clone();
	}

	@Override
	public String toProtocolString()
	{
		return this.tessera.toString();
	}

	private Tessera	tessera;
}
