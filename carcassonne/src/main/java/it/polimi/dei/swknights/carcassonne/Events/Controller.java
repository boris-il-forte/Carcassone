package it.polimi.dei.swknights.carcassonne.Events;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import java.util.EventListener;

public interface Controller extends EventListener, Runnable
{
	void riceviInput(ViewEvent event);
}
