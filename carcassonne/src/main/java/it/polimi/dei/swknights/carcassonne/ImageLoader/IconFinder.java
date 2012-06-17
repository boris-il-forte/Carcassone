package it.polimi.dei.swknights.carcassonne.ImageLoader;

import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;
import it.polimi.dei.swknights.carcassonne.Parser.ExtraParser;
import it.polimi.dei.swknights.carcassonne.Util.Bussola;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

/**
 * Class used to find icons by string
 * @author dave
 *
 */
public class IconFinder
{
	/**
	 * Default constructor
	 * @param stringToFind the string to find
	 */
	public IconFinder(String stringToFind)
	{
		this.string = new StringBuilder();
		this.trovaStringa(stringToFind);
	}

	/**
	 * Getter method
	 * @return the label of the marker
	 */
	public String getSegnalino()
	{
		return this.segnalino;
	}

	/**
	 * Return the string found by the finder algorithm
	 */
	@Override
	public String toString()
	{
		return this.string.toString();
	}

	private void trovaStringa(String stringToFind)
	{
		try
		{
			ExtraParser parser = new ExtraParser(stringToFind);
			for (PuntoCardinale puntoCardinale : PuntoCardinale.values())
			{
				String stringaParsata = parser.getData(puntoCardinale);
				this.string.append(stringaParsata);
				String extraData = parser.getExtraData(puntoCardinale);
				if (!extraData.equals(""))
				{
					this.segnalino = extraData;
				}
			}
			for (Bussola agoBussola : Bussola.values())
			{
				String data = parser.getData(agoBussola);
				this.string.append(data);
			}
		}
		catch (InvalidStringToParseException e)
		{
			this.string = new StringBuilder("");
		}

	}

	private StringBuilder	string;

	private String			segnalino	= "";
}
