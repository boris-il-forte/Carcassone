package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.Events.Game.ComandoView;

import java.util.EventObject;

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

	protected abstract void setComando();

	protected ComandoView	comando;
	
	private static final long	serialVersionUID	= -5387280202389373513L;

}
