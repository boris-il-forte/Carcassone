package it.polimi.dei.swknights.carcassonne.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.management.BadAttributeValueExpException;

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
				String dataToParse[] = parsedData[i].split("+");
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
		List<PuntoCardinale> puntiDaProvare = new ArrayList<PuntoCardinale>();
		puntiDaProvare.addAll(Arrays.asList(PuntoCardinale.values()));
		ListIterator<PuntoCardinale> iteraPunto = puntiDaProvare.listIterator();
		while(iteraPunto.hasNext())
		{
			PuntoCardinale punto = iteraPunto.next();
			switch (this.getDataChar(punto))
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
			numerazione[punto.toInt()] = counter;
			PuntoCardinale old = punto;
			while(iteraPunto.hasNext())
			{
				PuntoCardinale punto2 = iteraPunto.next();
				if (this.areConnected(punto, punto2))
				{
					
					numerazione[punto2.toInt()] = counter;
					iteraPunto.remove();
				}
			}
			iteraPunto = this.riavvolgiIterator(puntiDaProvare, old);
		}
		return numerazione;
	}

	private ListIterator<PuntoCardinale> riavvolgiIterator(List<PuntoCardinale> puntiDaProvare, PuntoCardinale old)
	{
		ListIterator<PuntoCardinale> iteraPunto =  puntiDaProvare.listIterator();
		PuntoCardinale next;
		do
		{
			next = iteraPunto.next();
		}
		while (!next.equals(old));
		return iteraPunto;
	}

	private boolean areConnected(PuntoCardinale puntoCardinale1, PuntoCardinale puntoCardinale2)
	{
		if(puntoCardinale1.equals(puntoCardinale2)) 
		{
			return true;
		}
		Bussola agoBussola = Bussola.componi(puntoCardinale1, puntoCardinale2);
		try
		{
			return this.getDataBool(agoBussola);
		}
		catch (BadAttributeValueExpException e)
		{
			return false;
		}
	}

	private String	extraData[];

}
