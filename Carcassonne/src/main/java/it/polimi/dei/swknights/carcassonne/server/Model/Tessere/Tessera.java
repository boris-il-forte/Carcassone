package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;

import java.util.List;

public abstract class Tessera {

  protected final Elemento nord;

  protected final Elemento sud;

  protected final Elemento est;

  protected final Elemento ovest;

  protected Link link;

  public Tessera( Elemento nord, Elemento sud, Elemento ovest, Elemento est)
  {
	  this.nord = nord;
	  this.sud = sud;
	  this.ovest = ovest;
	  this.est = est;
  }

 
  



  
    
}