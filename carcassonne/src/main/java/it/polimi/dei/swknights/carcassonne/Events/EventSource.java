package it.polimi.dei.swknights.carcassonne.Events;

import java.util.EventListener;

public interface EventSource
{

	public void addListener(EventListener eventListener);

	public void removeListener(EventListener eventListener);

}
