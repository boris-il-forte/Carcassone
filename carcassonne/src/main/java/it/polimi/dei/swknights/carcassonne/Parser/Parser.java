package it.polimi.dei.swknights.carcassonne.Parser;

import java.util.Arrays;
import java.util.List;
import javax.management.BadAttributeValueExpException;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;
import it.polimi.dei.swknights.carcassonne.Util.Bussola;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

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
		for (int i = 0; i < this.parsedData.length; i++)
		{
			this.parsedData[i] = "";
		}
		this.parseString(stringToParse);
	}

	/**
	 * method to get parsed data
	 * 
	 * @param puntoCardinale
	 *            the data you want to query
	 * @return parsed data at puntoCardinale
	 */

	public String getData(PuntoCardinale puntoCardinale)
	{
		int index = this.getIndex(puntoCardinale);
		return this.parsedData[index];
	}

	// TODO javadoc

	public char getDataChar(PuntoCardinale puntoCardinale)
	{
		return this.getData(puntoCardinale).charAt(FIRST_CHAR);
	}

	/**
	 * method to get parsed data
	 * 
	 * @param agoBussola
	 *            the data you want to query
	 * @return parsed data at agoBussola
	 */

	public char getDataChar(Bussola agoBussola)
	{
		return this.getData(agoBussola).charAt(FIRST_CHAR);
	}

	public String getDataType()
	{
		return this.typeData;
	}

	// TODO javadoc
	public String getData(Bussola agoBussola)
	{
		int index = this.getIndex(agoBussola);
		return this.parsedData[index];
	}

	public Boolean getDataBool(Bussola agoBussola) throws BadAttributeValueExpException
	{
		char siglaLink = this.getDataChar(agoBussola);
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

		try
		{
			controlloNumeroTokens(stringToParse, pezzi.length);
			parsingPerToken(pezzi);
		}
		catch (IllegalArgumentException e)
		{
			throw new InvalidStringToParseException(stringToParse, e);
		}

	}

	private void controlloNumeroTokens(String stringToParse, int length) throws InvalidStringToParseException
	{
		if (!(length == Token.DATA_TOKENS + 1) && !(length == Token.DATA_TOKENS)) {
			throw new InvalidStringToParseException(stringToParse);
		}
	}

	private void parsingPerToken(String pezzi[]) throws InvalidStringToParseException,
			IllegalArgumentException
	{
		List<String> listPezzi = Arrays.asList(pezzi);
		for (String pezzo : listPezzi)
		{
			String dati[] = pezzo.split("=");
			try
			{
				Token token = parseToken(dati[TOKEN]);
				parsedData[token.ordinal()] = dati[DATA];
			}
			catch (IllegalArgumentException e)
			{
				String dataExtra[] = dati[TOKEN].split("=");
				typeTokenParsed(dataExtra[TOKEN]);
				typeData = pezzo;
			}
		}
	}

	private Token parseToken(String stringToken) throws InvalidStringToParseException
	{
		Token token = Token.valueOf(stringToken);
		if (this.parsedData[token.ordinal()].equals(""))
		{
			return token;
		}
		else
		{
			throw new InvalidStringToParseException("token già parsato");
		}
	}

	private void typeTokenParsed(String stringToken) throws InvalidStringToParseException,
			IllegalArgumentException
	{
		TypeToken.valueOf(stringToken);
		if (!this.typeData .equals("")) {
			throw new InvalidStringToParseException("TypeToken già parsato");
		}
	}

	protected String			parsedData[];

	protected String			typeData	= "";

	private static final int	TOKEN		= 0;

	private static final int	DATA		= 1;

	private static final int	FIRST_CHAR	= 0;

	private enum Token {
		N, S, W, E, NS, NE, NW, WE, SE, SW;

		public static final int	DATA_TOKENS	= 10;
	};

	private enum TypeToken {
		U, M;
	}
}
