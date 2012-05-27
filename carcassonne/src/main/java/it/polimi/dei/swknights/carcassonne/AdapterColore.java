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
		this.mappaColori.put(Color.RED, "R Rosso");
		this.mappaColori.put(Color.BLUE, "B Blu");
		this.mappaColori.put(Color.GREEN, "G Verde");
		this.mappaColori.put(Color.YELLOW, "Y Giallo");
		this.mappaColori.put(Color.BLACK, "K Nero");
	}

	@Override
	/**
	 * returns  R, B, G, Y or K according to the Color
	 */
	public String toString()
	{
		String[] stringhe = this.getStringhe();
		return stringhe[0];
	}
	
	public String toName()
	{
		String[] stringhe = this.getStringhe();
		return stringhe[1];
	}
	
	private String[] getStringhe()
	{
		return this.mappaColori.get(this.color).split(" ");
	}

	private Color	color;
	
	private final Map<Color, String>	mappaColori	= new HashMap<Color, String>();

}
