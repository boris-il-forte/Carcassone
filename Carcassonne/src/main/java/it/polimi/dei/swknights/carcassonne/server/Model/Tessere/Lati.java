package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
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
		return elementi[puntoCardinale.toInt()];
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
	
	public Map<Costruzione, PuntoCardinale> getListaCostruzioniPrimitive(Tessera tessera)
	{
		Map<Costruzione, PuntoCardinale> listaCostruzioni = new HashMap<Costruzione, PuntoCardinale>();
		Elemento[] elementi = 
			{ this.nord, this.sud, this.est, this.ovest };
		PuntoCardinale[] punti =
			{ PuntoCardinale.nord, PuntoCardinale.sud, PuntoCardinale.est, PuntoCardinale.ovest};
		int indicePuntiCardinali=0;
		for(Elemento elemento : elementi)
		{
			Costruzione costruzione = elemento.getCostruzione(tessera);
			if(costruzione != null) listaCostruzioni.put(costruzione, punti[indicePuntiCardinali]);
			indicePuntiCardinali++;
		}
		return listaCostruzioni;
	}

	private Elemento nord;
	
	private Elemento sud;
	
	private Elemento ovest;
	
	private Elemento est;

}
