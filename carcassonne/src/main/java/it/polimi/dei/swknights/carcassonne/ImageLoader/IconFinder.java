package it.polimi.dei.swknights.carcassonne.ImageLoader;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;
import it.polimi.dei.swknights.carcassonne.Parser.ExtraParser;
import it.polimi.dei.swknights.carcassonne.Util.Bussola;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

public class IconFinder
{
	public IconFinder(String stringToFind)
	{
		this.string = new StringBuilder();
		this.trovaStringa(stringToFind);		
	}
	
	@Override
	public String toString()
	{
		Debug.print("stringa in uscita a IconFinder: " + string);
		return this.string.toString();
	}
	
	private void trovaStringa(String stringToFind)
	{
		Debug.print("stringa in ingresso a IconFinder: " + stringToFind);
		try
		{
			ExtraParser parser = new ExtraParser(stringToFind);
			for(PuntoCardinale puntoCardinale : PuntoCardinale.values())
			{
				String string = parser.getData(puntoCardinale);
				this.string.append(string);
			}
			for(Bussola agoBussola : Bussola.values())
			{
				String string = parser.getData(agoBussola);
				this.string.append(string);
			}
		}
		catch (InvalidStringToParseException e)
		{
			this.string = new StringBuilder("");
		}
		
	}

	private StringBuilder string;
}
