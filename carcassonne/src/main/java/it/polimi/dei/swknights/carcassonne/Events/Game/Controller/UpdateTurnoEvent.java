package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;

import java.awt.Color;

public class UpdateTurnoEvent extends UpdateEvent
{

	/**
	 * Constructor to be called when you have the card object
	 * 
	 * @param source
	 *            the event source
	 * @param color
	 *            the current player's color
	 * @param tessera
	 *            the new card to be placed
	 */
	public UpdateTurnoEvent(Object source, Color color, Tessera tessera)
	{
		super(tessera, null, color, source);
		this.color = color;
	}

	/**
	 * Constructor to be called when you have the card string representation
	 * 
	 * @param source
	 *            the event source
	 * @param color
	 *            the current player's color
	 * @param tessera
	 *            the new card to be placed
	 */
	public UpdateTurnoEvent(Object source, Color color, String tessera)
	{
		super(tessera, null, color, source);
		this.color = color;
	}

	/**
	 * Getter method
	 * @return the current player's color
	 */
	public Color getGiocatoreCorrente()
	{
		return this.color;
	}

	/**
	 * accept method for visitor's pattern
	 */
	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);

	}

	private final Color			color;

	private static final long	serialVersionUID	= -8277130187657287093L;

}
