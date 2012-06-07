package it.polimi.dei.swknights.carcassonne.Events;

import java.util.EventListener;

/**
 * Basic schema for an EventSource: it has to have an addListener and a
 * removeListener
 * 
 * @author edoardopasi
 * 
 */
public interface EventSource
{

	void addListener(EventListener eventListener);

	void removeListener(EventListener eventListener);

}
