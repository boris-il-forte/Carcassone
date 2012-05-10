package it.polimi.dei.swknights.carcassonne.server.ViewProxy;

import java.util.List;
import it.polimi.dei.swknights.carcassonne.Events.ControllerListener;

/*
 */
public class ViewProxy extends View implements ControllerListener {

  public ConnessioneAView realSubject;

    public List  myConnessione;
    public List  myConnessioneAView;

  public void request() {
  }

}