package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Color;
import java.util.Map;

/**
 * Event to be triggered when the game finishes that is when no more cards are
 * available
 * 
 * @author edoardopasi & dave
 * 
 */

public class FinePartitaEvent extends ControllerEvent
{

	public FinePartitaEvent(Object source, Punteggi punteggi)
	{
		super(source);
		this.setComando(MessaggiController.end);
		this.punteggi = punteggi;
	}
	/**
	 * Map that associates to each Color-Player a score
	 * @return
	 */
	public Punteggi getPunteggi()
	{
		return punteggi;
	}

	private Punteggi punteggi;
	private static final long	serialVersionUID	= 7228959705104329672L;
	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);
		
	}

}
