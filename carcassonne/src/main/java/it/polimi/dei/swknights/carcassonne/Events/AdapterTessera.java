package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;

public abstract class AdapterTessera
{
	public abstract String toProtocolString();
	
	public String toCliString(PuntoCardinale puntoCardinale)
	{
		String stringToParse = this.parseString(this.toProtocolString());
		stringToParse = this.parseString(stringToParse);
		stringToParse = this.selectString(stringToParse, puntoCardinale);
		return stringToParse;
	}
	
	private String parseString(String string)
	{
		//TODO: parser
		return string;
	}
	
	private String selectString(String string, PuntoCardinale puntoCardinale)
	{
		//TODO parser v2
		return string;
	}
}