package it.polimi.dei.swknights.carcassonne.Parser;

import javax.management.BadAttributeValueExpException;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;

/**
 * Class that makes the basic parse of the tile string
 * 
 * @author dave
 * 
 */

public class Parser
{
	/**
	 * Constructor that make the basic parse of the string
	 * 
	 * @param stringToParse
	 *            the string to parse
	 * @throws InvalidStringToParseException
	 *             if the string is not a valid string.
	 */
	public Parser(String stringToParse) throws InvalidStringToParseException
	{
		this.parsedData = new String[Token.DATA_TOKENS];
		this.parseString(stringToParse);
	}

	/**
	 * method to get parsed data
	 * 
	 * @param puntoCardinale
	 *            the data you want to query
	 * @return parsed data at puntoCardinale
	 */

	public char getData(PuntoCardinale puntoCardinale)
	{
		int index = this.getIndex(puntoCardinale);
		return this.parsedData[index].charAt(FIRST_CHAR);
	}

	/**
	 * method to get parsed data
	 * 
	 * @param agoBussola
	 *            the data you want to query
	 * @return parsed data at agoBussola
	 */

	public char getData(Bussola agoBussola)
	{
		int index = this.getIndex(agoBussola);
		return this.parsedData[index].charAt(FIRST_CHAR);
	}
	
	public Boolean getDataBool(Bussola agoBussola) throws BadAttributeValueExpException
	{
		char siglaLink = this.getData(agoBussola);
		switch (siglaLink)
		{
			case '1':
				return true;
			case '0':
				return false;
			default:
				throw new BadAttributeValueExpException(siglaLink);
		}
	}

	protected final int getIndex(PuntoCardinale puntoCardinale)
	{
		return puntoCardinale.toInt();
	}

	protected final int getIndex(Bussola agoBussola)
	{
		final int base = PuntoCardinale.NUMERO_DIREZIONI;
		return base + agoBussola.toInt();
	}

	private void parseString(String stringToParse) throws InvalidStringToParseException
	{
		String pezzi[] = stringToParse.split(" ");
		if (pezzi.length < Token.DATA_TOKENS + 1)
		{
			for (String pezzo : pezzi)
			{
				String dati[] = pezzo.split("=");
				Token token = Token.valueOf(dati[TOKEN]);
				parsedData[token.ordinal()] = dati[DATA];
			}
		} else
			throw new InvalidStringToParseException(stringToParse);
	}

	
	
	protected String			parsedData[];

	private final static int	TOKEN		= 0;

	private final static int	DATA		= 1;
	
	private final static int 	FIRST_CHAR  = 0;

	private enum Token
	{
		N, S, W, E, NS, NE, NW, WE, SE, SW;

		public static int	DATA_TOKENS	= 10;
	};
}
