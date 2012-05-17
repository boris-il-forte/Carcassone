package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

import java.awt.Color;
import java.util.Map;

/**
 * Event to be triggered when the game finishes that is when no more cards are available
 * @author edoardopasi & dave
 *
 */

public class FinePartitaEvent extends ControllerEvent
{

	public FinePartitaEvent(Object source, Map<Color, Integer> mappaPunteggi)
	{
		super(source);
		this.setComando(MessaggiController.end);
		this.mappaPunteggi = mappaPunteggi;
	}

	public Map<Color, Integer> getMappaPunteggi()
	{
		return mappaPunteggi;
	}

	private Map<Color, Integer>	mappaPunteggi;
	private static final long	serialVersionUID	= 7228959705104329672L;

}