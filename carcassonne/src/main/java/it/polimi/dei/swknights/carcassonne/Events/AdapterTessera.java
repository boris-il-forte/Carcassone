package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;
import it.polimi.dei.swknights.carcassonne.Parser.ExtraParser;

/**
 * The aim of this class is provide an Adapter that basically limits the getters
 * on the Card Is usefull to pass all the needed informations of Card via events
 * but no more then that
 * 
 * @author edoardopasi & dave
 * 
 */
public abstract class AdapterTessera
{
	public abstract String toProtocolString();

	/**
	 * 
	 * @param puntoCardinale
	 * @return a string describing the card in suitable way for the Command Line
	 *         Interface
	 */
	public String toCliString(PuntoCardinale puntoCardinale)
	{
		return this.parseString(this.toProtocolString());

	}

	private String parseString(String string)
	{
		ExtraParser parser;
		try
		{
			parser = new ExtraParser(string);
			return parser.toString();
		}
		catch (InvalidStringToParseException e)
		{
			return "";
		}
	}
}
