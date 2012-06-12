package it.polimi.dei.swknights.carcassonne.Events;

/**
 * A lightweight version of AdapterTessera thought to be used with RMI
 * communication
 * 
 * @author edoardopasi & dave
 * 
 */
public class AdapterTesseraString extends AdapterTessera
{
	public AdapterTesseraString(String stringaTessera)
	{
		this.stringaTessera = stringaTessera;
	}

	@Override
	public String toProtocolString()
	{
		return this.stringaTessera;
	}

	private String	stringaTessera;
	
	private static final long	serialVersionUID	= 1666429877105397L;
}
