package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraString;
import it.polimi.dei.swknights.carcassonne.Events.DecoraTessera;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Color;

/**
 * Abstract schema for events to be triggered when a generic update on model
 * happens.
 * 
 * @author edoardopasi & dave
 * 
 */
public abstract class UpdateEvent extends ControllerEvent
{
	/**
	 * Constructor to be called when you have the card object
	 * 
	 * @param tessera
	 *            the updated card
	 * @param coordinate
	 *            the updated coordinates
	 * @param giocatore
	 *            the current player
	 * @param source
	 *            the event source
	 */
	public UpdateEvent(Tessera tessera, Coordinate coordinate, Color giocatore, Object source)
	{
		super(source);
		AdapterTessera adapter = new AdapterTesseraObject(tessera);
		this.setData(adapter, coordinate, giocatore);
	}

	/**
	 * Constructor to be called when you have the card string representation
	 * 
	 * @param tessera
	 *            the updated card
	 * @param coordinate
	 *            the updated coordinates
	 * @param giocatore
	 *            the current player
	 * @param source
	 *            the event source
	 */
	public UpdateEvent(String tessera, Coordinate coordinate, Color giocatore, Object source)
	{
		super(source);
		AdapterTessera adapter = new AdapterTesseraString(tessera);
		this.setData(adapter, coordinate, giocatore);
	}

	/**
	 * Getter for the card
	 * 
	 * @return the event's card representation
	 */
	public AdapterTessera getTessera()
	{
		return this.dati.getTessera();
	}

	/**
	 * 
	 * @return a {@link DecoraTessera} to notify the caller what is changed
	 *         after the user command
	 */
	protected DecoraTessera getDati()
	{
		return this.dati;
	}

	private void setData(AdapterTessera tessera, Coordinate coordinate, Color giocatore)
	{
		this.dati = new DecoraTessera(tessera, coordinate, giocatore);
	}

	private DecoraTessera		dati;

	private static final long	serialVersionUID	= -4838659490031728270L;

}
