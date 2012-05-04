package it.polimi.dei.swknights.carcassonne.server;

import java.util.List;

import it.polimi.dei.swknights.carcassonne.server.Controller.Controllore;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;
import it.polimi.dei.swknights.carcassonne.server.ViewProxy.View;

public class Partita {

  public DatiPartita model;

  public Integer idPartita;

  public Controllore controller;

  public View view;

  public Integer newAttr;

    /**
   * 
   * @element-type CarcassonneServer
   */
    public List  myDatiPartita;
    public List  myControllore;

    public List  myView;

}