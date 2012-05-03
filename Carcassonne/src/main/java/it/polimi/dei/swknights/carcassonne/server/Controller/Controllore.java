package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Events.ViewListener;
import java.util.Vector;
import it.polimi.dei.swknights.carcassonne.Events.EventSource;
import Integer;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;

public class Controllore implements ViewListener, EventSource {

  private DatiPartita partita;

  private ContaPunti contaPunti;

  private Tessera tesseraCorrente;

  private List<ViewListener> listeners;

  public Integer newAttr;

    public Vector  myDatiPartita;
    public Vector  myTessera;
    public Vector  myContaPunti;
    public Vector  Si appoggia a;
      public Vector  myPartita;

}