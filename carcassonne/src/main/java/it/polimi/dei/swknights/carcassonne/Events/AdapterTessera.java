package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
/**
 * The aim of this class is provide an Adapter that basically limits the getters on the Card
 * Is usefull to pass all the needed informations of Card via events but no more then that
 * @author edoardopasi & dave
 *
 */
public abstract class AdapterTessera
{
	public abstract String toProtocolString();
	/**
	 * 
	 * @param puntoCardinale
	 * @return a string describing the card in suitable way for the Command Line Interface
	 */
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