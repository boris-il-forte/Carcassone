package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;

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
		this.tessera = tessera.clone();
	}

	@Override
	public String toProtocolString()
	{
		return this.tessera.toString();
	}
	
	private static final long	serialVersionUID	= -3083803275769710214L;

	private Tessera	tessera;
}
