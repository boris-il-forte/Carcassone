package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraString;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;

import java.awt.Color;

/**
 * Event to be triggered at the beginning of the game
 * 
 * @author edoardopasi
 * 
 */
public class InizioGiocoEvent extends ControllerEvent
{

	public InizioGiocoEvent(Object source, Tessera tesseraIniziale, Color giocatore, Integer numeroGiocatori,
			String idPartita)
	{
		super(source);
		AdapterTessera adapterTessera = new AdapterTesseraObject(tesseraIniziale);
		this.setData(giocatore, adapterTessera, idPartita, numeroGiocatori);

	}

	public InizioGiocoEvent(Object source, String tesseraIniziale, Color giocatore, Integer numeroGiocatori,
			String idPartita)
	{
		super(source);
		AdapterTessera adapterTessera = new AdapterTesseraString(tesseraIniziale);
		this.setData(giocatore, adapterTessera, idPartita, numeroGiocatori);
	}

	public InizioGiocoEvent(Object source, AdapterTessera adapterTessera, Color giocatore,
			int numeroGiocatori, String idPartita)
	{
		super(source);
		this.setData(giocatore, adapterTessera, idPartita, numeroGiocatori);
	}

	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);
	}

	public AdapterTessera getTesseraIniziale()
	{
		return this.tesseraIniziale;
	}

	public String getIdPartita()
	{
		return this.idPartita;
	}

	public int getNumGiocatori()
	{
		return this.numeroGiocatori;
	}

	public Color getGiocatore()
	{
		return this.giocatore;
	}

	private void setData(Color giocatore, AdapterTessera tesseraIniziale, String idPartita,
			int numeroGiocatori)
	{
		this.giocatore = giocatore;
		this.tesseraIniziale = tesseraIniziale;
		this.idPartita = idPartita;
		this.numeroGiocatori = numeroGiocatori;
	}

	private AdapterTessera		tesseraIniziale;

	private Color				giocatore;

	private Integer				numeroGiocatori;

	private String				idPartita;

	private static final long	serialVersionUID	= -5256224715700170795L;

}
