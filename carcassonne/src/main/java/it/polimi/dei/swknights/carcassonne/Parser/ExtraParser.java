package it.polimi.dei.swknights.carcassonne.Parser;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;

/**
 * Class used to parse extra arguments of each fragment of code
 * 
 * @author dave
 * 
 */

public class ExtraParser extends Parser
{
	/**
	 * Constructor that make an extra parse of the tile string
	 * 
	 * @param stringToParse
	 *            the string to be parsed
	 * @throws InvalidStringToParseException
	 *             if the parsed string is not valid
	 */
	public ExtraParser(String stringToParse) throws InvalidStringToParseException
	{
		super(stringToParse);
		this.extraData = new String[this.parsedData.length];
		this.extraParse();
	}

	/**
	 * Get extra data at puntoCardinale
	 * 
	 * @param puntoCardinale
	 *            the extra data you want to query
	 * @return the extra data of the token puntoCardinale. if no extra data is
	 *         present, return empty string.
	 */

	public String getExtraData(PuntoCardinale puntoCardinale)
	{
		int index = this.getIndex(puntoCardinale);
		return extraData[index];
	}

	/**
	 * Get extra data at agoBussola
	 * 
	 * @param agoBussola
	 *            the extra data you want to query
	 * @return the extra data of the token agoBussola. if no extra data is
	 *         present, return empty string.
	 */

	public String getExtraData(Bussola agoBussola)
	{
		int index = this.getIndex(agoBussola);
		return extraData[index];
	}

	private void extraParse()
	{
		for (int i = 0; i < this.parsedData.length; i++)
		{
			final int normalDataIndex = 0;
			final int extraDataIndex = 1;
			if (this.parsedData[i].length() > 1)
			{
				String dataToParse[] = parsedData[i].split(",");
				this.parsedData[i] = dataToParse[normalDataIndex];
				this.extraData[i] = dataToParse[extraDataIndex];
			}
		}
	}

	private String	extraData[];

}
