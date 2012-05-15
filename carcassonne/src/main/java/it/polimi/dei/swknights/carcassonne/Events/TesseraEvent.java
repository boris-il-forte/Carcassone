package it.polimi.dei.swknights.carcassonne.Events;

import java.awt.Color;
import java.util.EventObject;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class TesseraEvent extends EventObject
{
	public TesseraEvent(Tessera tessera, Coordinate coordinate, Color giocatore, Object source)
	{
		super(source);
		AdapterTessera adapter = new AdapterTesseraObject(tessera);
		this.setData(adapter, coordinate, giocatore);
	}
	
	public TesseraEvent(String tessera,Coordinate coordinate, Color giocatore, Object source)
	{
		super(source);
		AdapterTessera adapter = new AdapterTesseraString(tessera);
		this.setData(adapter, coordinate, giocatore);
	}
	
	public DecoraTessera getData()
	{
		return this.dati;
	}
	
	private void setData(AdapterTessera tessera, Coordinate coordinate, Color giocatore)
	{
		this.dati = new DecoraTessera(tessera, coordinate, giocatore);
	}

	private DecoraTessera	dati;
	private static final long	serialVersionUID	= -2683878724332527385L;
}
