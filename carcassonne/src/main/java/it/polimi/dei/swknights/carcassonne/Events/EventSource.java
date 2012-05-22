package it.polimi.dei.swknights.carcassonne.Events;

import java.util.EventListener;

public interface EventSource
{

	void addListener(EventListener eventListener);

	void removeListener(EventListener eventListener);

}
