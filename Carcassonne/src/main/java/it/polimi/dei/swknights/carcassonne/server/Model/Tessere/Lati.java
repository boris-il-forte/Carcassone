package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
/**
 * That class encapsulate the four elements of a card
 * @author Edo & Dave
 *
 */
public class Lati
{

	public Lati(Elemento nord, Elemento sud, Elemento ovest, Elemento est)
	{
		this.nord = nord;
		this.sud = sud;
		this.ovest = ovest;
		this.est = est;
	}

	/**
	 * gets the element on the specified cardinal point
	 * @param puntoCardinale  cardinal point
	 * @return the element on that cardinal point
	 */
	public Elemento getElementoInDirezione(PuntoCardinale puntoCardinale)
	{
		Elemento[] elementi =
		{ this.nord, this.sud, this.ovest, this.est };
		return elementi[puntoCardinale.ordinal()];
	}
	/**
	 * gets the element on the opposite direction of the specified point
	 * @param puntoCardinale  cardinal point
	 * @return the element on that cardinal point
	 */
    public Elemento getElementoInDirezioneOpposta(PuntoCardinale puntoCardinale)
    {
    	puntoCardinale = puntoOpposto(puntoCardinale);
        return getElementoInDirezione(puntoCardinale);
        		
    }

    private PuntoCardinale puntoOpposto(PuntoCardinale puntoCardinale)
    {
    	switch(puntoCardinale)
    	{
    	    case nord:
    	    	return PuntoCardinale.sud;
    	    case sud:
    	    	return PuntoCardinale.nord;
    	    case ovest:
    	    	return PuntoCardinale.est;
    	    case est:
    	    	return PuntoCardinale.ovest;
    	    default:
    	    	return PuntoCardinale.nord;
    	    	
    	}
    	
    }
    /**
     * rotate 90° clockwise the 4 elements of the card.
     */
	public void ruota()
	{
		Elemento nordTmp = this.nord;
		this.nord = this.ovest;
		this.ovest = this.sud;
		this.sud = this.est;
		this.est = nordTmp;
	
	}

	private Elemento nord;
	
	private Elemento sud;
	
	private Elemento ovest;
	
	private Elemento est;

}
