package it.polimi.dei.swknights.carcassonne;

import java.awt.Color;
import java.util.Map;
import java.util.HashMap;

/**
 * The aim of this class is, mainly, to provide a better implementation of the
 * default Color.toString method offered by awt
 * 
 * @author edoardopasi & dave
 * 
 */

public class AdapterColore
{
	public AdapterColore(Color color)
	{
		this.color = color;
		this.mappaColori.put(Color.RED, "R");
		this.mappaColori.put(Color.BLUE, "B");
		this.mappaColori.put(Color.GREEN, "G");
		this.mappaColori.put(Color.YELLOW, "Y");
		this.mappaColori.put(Color.BLACK, "K");
	}

	private final Map<Color, String>	mappaColori	= new HashMap<Color, String>();

	@Override
	/**
	 * returns  R, B, G, Y or K according to the Color
	 */
	public String toString()
	{
		return mappaColori.get(this.color);
	}

	private Color	color;

}
