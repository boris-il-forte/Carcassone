package it.polimi.dei.swknights.carcassonne;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

public class DecoraColore
{
	public DecoraColore(Color color)
	{
		this.color = color;
		this.mappaColori.put(Color.RED, "R");
		this.mappaColori.put(Color.BLUE, "B");
		this.mappaColori.put(Color.GREEN, "V");
		this.mappaColori.put(Color.YELLOW, "G");
		this.mappaColori.put(Color.BLACK, "N");
	}
	
	
	
	private final Map<Color, String> mappaColori = new TreeMap<Color, String>();
	
	@Override
	public String toString()
	{
		return "("+mappaColori.get(this.color)+")";
	}
	
	private Color color;
	
}
