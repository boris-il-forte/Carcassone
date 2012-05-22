package it.polimi.dei.swknights.carcassonne.Parser;

import java.util.Arrays;
import java.util.List;

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

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		int numerazioni[] = this.getNumerazioni();
		for (PuntoCardinale puntoCardinale : PuntoCardinale.values())
		{
			builder.append(getData(puntoCardinale));
			builder.append(numerazioni[puntoCardinale.toInt()]);
			String extradata = this.getExtraData(puntoCardinale);
			if (extradata.length() > 0)
			{
				builder.append("(" + extradata + ")");
			}
			builder.append(" ");
		}
		builder.append(this.getDataType());
		return builder.toString();
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
			else
			{
				this.extraData[i] = "";
			}
		}
	}

	private int[] getNumerazioni()
	{
		int[] numerazione = new int[PuntoCardinale.NUMERO_DIREZIONI];
		int counter;
		int stradaCounter = 0;
		int cittaCounter = 0;
		List<PuntoCardinale> puntiDaProvare = Arrays.asList(PuntoCardinale.values());
		for (PuntoCardinale punto1 : puntiDaProvare)
		{
			switch (this.getDataChar(punto1))
			{
				case 'S':
					counter = ++stradaCounter;
					break;
				case 'C':
					counter = ++cittaCounter;
					break;
				default:
					continue;
			}
			for (PuntoCardinale punto2 : puntiDaProvare)
			{
				if (this.isConnected(punto1, punto2))
				{
					puntiDaProvare.remove(punto2);
					numerazione[punto2.toInt()] = counter;
				}
			}
		}
		return null;
	}

	private boolean isConnected(PuntoCardinale puntoCardinale1, PuntoCardinale puntoCardinale2)
	{
		if (puntoCardinale1 == puntoCardinale2) {
			return true;
		}
		else
		{
			return this.areConnected(puntoCardinale1, puntoCardinale2);
		}
	}

	private boolean areConnected(PuntoCardinale puntoCardinale1, PuntoCardinale puntoCardinale2)
	{
		// TODO Auto-generated method stub
		return false;
	}

	private String	extraData[];

}
