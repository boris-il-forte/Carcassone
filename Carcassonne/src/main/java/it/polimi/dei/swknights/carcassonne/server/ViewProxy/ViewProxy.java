package it.polimi.dei.swknights.carcassonne.server.ViewProxy;

import java.util.Vector;
import it.polimi.dei.swknights.carcassonne.Events.ControllerListener;

/*
 */
public class ViewProxy extends View implements ControllerListener {

  public ConnessioneAView realSubject;

    public Vector  myConnessione;
    public Vector  myConnessioneAView;

  public void request() {
  }

}