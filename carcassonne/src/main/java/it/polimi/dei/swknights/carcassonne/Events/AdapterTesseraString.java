package it.polimi.dei.swknights.carcassonne.Events;

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
}