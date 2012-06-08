package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

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
		this.punteggi = punteggi;
	}

	/**
	 * Map that associates to each Color-Player a score
	 * 
	 * @return
	 */
	public Punteggi getPunteggi()
	{
		return this.punteggi;
	}

	private Punteggi			punteggi;
	private static final long	serialVersionUID	= 7228959705104329672L;

	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);

	}

}
