package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.AzioneGioco;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.ControllerHandler;

import java.util.EventObject;

/**
 * Used to give a schema to the various view events
 * 
 * @author edoardopasi
 * 
 */
public abstract class ViewEvent extends EventObject
{
	public ViewEvent(Object source)
	{
		super(source);
	}

	@Override
	public String toString()
	{
		return this.comando.toString();
	}

	protected final void setComando(AzioneGioco comando)
	{
		this.comando = comando;
	}

	public abstract void accept(ControllerHandler handler);

	private AzioneGioco			comando;

	private static final long	serialVersionUID	= -5387280202389373513L;

}
