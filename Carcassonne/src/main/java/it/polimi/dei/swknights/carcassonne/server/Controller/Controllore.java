package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Events.ViewListener;

import java.util.EventListener;
import java.util.List;
import it.polimi.dei.swknights.carcassonne.Events.EventSource;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;

public class Controllore implements ViewListener, EventSource {

  private DatiPartita partita;

  private ContaPunti contaPunti;

  private Tessera tesseraCorrente;

  private List<ViewListener> listeners;

  public Integer newAttr;

    public List  myDatiPartita;
    public List  myTessera;
    public List  myContaPunti;
    public List  SiAppoggiaA;
      public List  myPartita;
	public void addListener(EventListener eventListener) {
		// TODO Auto-generated method stub
		
	}
	public void removeListener(EventListener eventListener) {
		// TODO Auto-generated method stub
		
	}
	public void riceviInput() {
		// TODO Auto-generated method stub
		
	}

}