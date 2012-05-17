package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import java.util.EventListener;

public interface ViewListener extends EventListener, Runnable
{
	public void riceviInput(ViewEvent event);
}