package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraString;
import it.polimi.dei.swknights.carcassonne.Events.DecoraTessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;
import java.awt.Color;

public abstract class UpdateEvent extends ControllerEvent
{
	public UpdateEvent(Tessera tessera, Coordinate coordinate, Color giocatore, Object source)
	{
		super(source);
		AdapterTessera adapter = new AdapterTesseraObject(tessera);
		this.setData(adapter, coordinate, giocatore);
	}

	public UpdateEvent(String tessera, Coordinate coordinate, Color giocatore, Object source)
	{
		super(source);
		AdapterTessera adapter = new AdapterTesseraString(tessera);
		this.setData(adapter, coordinate, giocatore);
	}

	public AdapterTessera getTessera()
	{
		return this.dati.getTessera();
	}

	private void setData(AdapterTessera tessera, Coordinate coordinate, Color giocatore)
	{
		this.dati = new DecoraTessera(tessera, coordinate, giocatore);
	}
	/**
	 * 
	 * @return a {@link DecoraTessera} to notify the caller what is changed 
	 * after the user command
	 */
	protected DecoraTessera getDati()
	{
		return this.dati;
	}

	private DecoraTessera		dati;

	private static final long	serialVersionUID	= -4838659490031728270L;

}
