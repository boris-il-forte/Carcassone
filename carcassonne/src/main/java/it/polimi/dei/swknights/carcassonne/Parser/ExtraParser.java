package it.polimi.dei.swknights.carcassonne.Parser;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;

public class ExtraParser extends Parser
{

	public ExtraParser(String stringToParse) throws InvalidStringToParseException
	{
		super(stringToParse);
		this.extraData = new String[this.parsedData.length];
		this.extraParse();
	}
	
	public String getExtraData(PuntoCardinale puntoCardinale)
	{
		int index = this.getIndex(puntoCardinale);
		return extraData[index];
	}
	
	public String getExtraData(Bussola agoBussola)
	{
		int index = this.getIndex(agoBussola);
		return extraData[index];
	}
	
	private void extraParse()
	{
		for(int i=0; i < this.parsedData.length; i++)
		{
			final int normalDataIndex = 0;
			final int extraDataIndex = 1;
			if(this.parsedData[i].length() > 1)
			{
				String dataToParse[] = parsedData[i].split(",");
				this.parsedData[i] = dataToParse[normalDataIndex];
				this.extraData[i] = dataToParse[extraDataIndex];
			}
		}
	}
	
	private String extraData[];  
	
}
