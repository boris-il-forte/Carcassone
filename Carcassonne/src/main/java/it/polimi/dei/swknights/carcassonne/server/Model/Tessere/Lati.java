package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;

public class Lati
{

	public Lati(Elemento nord, Elemento sud, Elemento ovest, Elemento est)
	{
		this.nord = nord;
		this.sud = sud;
		this.ovest = ovest;
		this.est = est;
	}

	public Elemento getElementoInDirezione(PuntoCardinale puntoCardinale)
	{
		Elemento[] elementi =
		{ this.nord, this.sud, this.ovest, this.est };
		return elementi[puntoCardinale.ordinal()];
	}
	
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
