package it.polimi.dei.swknights.carcassonne.Events;

public interface EventSource {

  public void addListener( EventListener);

  public void removeListener( EventListener);

}