package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;
import it.polimi.dei.swknights.carcassonne.Parser.ExtraParser;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;

/**
 * A lightweight version of AdapterTessera thought to be used with RMI
 * communication
 * 
 * @author edoardopasi & dave
 * 
 */
public class AdapterTesseraString extends AdapterTessera
{
	public AdapterTesseraString(String stringaTessera)
	{
		this.stringaTessera = stringaTessera;
	}

	@Override
	public String toProtocolString()
	{
		return this.stringaTessera;
	}
	
	@Override
	public Color getColorSegnalino()
	{
		ExtraParser parser;
		try
		{
			parser = new ExtraParser(this.stringaTessera);
			for(PuntoCardinale punto : PuntoCardinale.values())
			{
				String stringa = parser.getExtraData(punto);
				if(!stringa.equals(""))
				{
					return ColoriGioco.getColor(stringa);
				}
			}
		}
		catch(InvalidStringToParseException e)
		{
		}
		return null;
	}

	private String	stringaTessera;
	
	private static final long	serialVersionUID	= 1666429877105397L;
}
