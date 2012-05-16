package it.polimi.dei.swknights.carcassonne.Parser;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;

public class Parser
{
	public Parser(String stringToParse) throws InvalidStringToParseException
	{
		this.parsedData = new String[Token.DATA_TOKENS];
		this.parseString(stringToParse);
	}
	
	public String getData(PuntoCardinale puntoCardinale)
	{
		int index = this.getIndex(puntoCardinale);
		return this.parsedData[index];
	}
	
	public String getData(Bussola agoBussola)
	{
		int index  = this.getIndex(agoBussola);
		return this.parsedData[index];
	}
	
	public String getType()
	{
		if(parsedToken == Token.Null)
		{
			return "";
		}
		else return parsedToken.toString();
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
		if(pezzi.length < Token.DATA_TOKENS +1)
		{
			for(String pezzo : pezzi)
			{
				String dati[] = pezzo.split("=");
				Token token = Token.valueOf(dati[TOKEN]);
				if(dati.length > 1)
				{
					parsedData[token.ordinal()] = dati[DATA];
				}
				else
				{
					parsedToken = token;
				}
			}
		}
		else throw new InvalidStringToParseException(stringToParse);
	}
	
	protected String parsedData[];
	
	private Token parsedToken = Token.Null;
	
	private final static int TOKEN = 0;
	
	private final static int DATA = 0;
	
	private enum Token 
	{
		N, S, W, E, NS, NE, NW, WE, SE, SW, M, U, Null;
		
		public static int DATA_TOKENS = 10;
	};
}
