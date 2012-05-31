package it.polimi.dei.swknights.carcassonne.Util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The aim of this class is give basic tools for handling Carcassonne Colors
 * 
 * @author dave & edo
 * 
 */
public class ColoriGioco
{
	/**
	 * 
	 * @return an ArrayList of the game Color
	 */
	public static List<Color> getListaColori()
	{
		return new ArrayList<Color>(mappaColori.keySet());

	}

	/**
	 * returns R, B, G, Y or K according to the Color
	 */
	public static String getSigla(Color colore)
	{
		String[] stringhe = getStringhe(colore);
		return stringhe[0];
	}

	/**
	 * 
	 * @param colore
	 * @return the full name of the color e.g. RED
	 */
	public static String getName(Color colore)
	{
		String[] stringhe = getStringhe(colore);
		return stringhe[1];
	}

	private static Map<Color, String> inizializzaColori()
	{
		Map<Color, String> mappa = new LinkedHashMap<Color, String>();
		mappa.put(Color.RED, "R Rosso");
		mappa.put(Color.BLUE, "B Blu");
		mappa.put(Color.GREEN, "G Verde");
		mappa.put(Color.YELLOW, "Y Giallo");
		mappa.put(Color.BLACK, "K Nero");

		return mappa;
	}

	private static String[] getStringhe(Color color)
	{
		return mappaColori.get(color).split(" ");
	}

	private static final Map<Color, String>	mappaColori	= inizializzaColori();

}
