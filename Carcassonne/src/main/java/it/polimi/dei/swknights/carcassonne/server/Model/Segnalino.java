package it.polimi.dei.swknights.carcassonne.server.Model;

import java.awt.Color;

public class Segnalino
{
	public Segnalino(Color colore)
	{
		this.colore=colore;
	}
	
	public Color getColore()
	{
		return this.colore;
	}
	
	private Color colore;

}