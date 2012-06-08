package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;

import java.awt.Color;

/**
 * Event to be triggered at the beginning of the game
 * 
 * @author edoardopasi
 * 
 */
public class InizioGiocoEvent extends ControllerEvent
{

	public InizioGiocoEvent(Object source, AdapterTessera tesseraIniziale, Color giocatore,
			Integer numeroGiocatori, String idPartita)
	{
		super(source);
		this.giocatore = giocatore;
		this.tesseraIniziale = tesseraIniziale;
		this.idPartita = idPartita;
		this.numeroGiocatori = numeroGiocatori;

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

	private static final long		serialVersionUID	= -5256224715700170795L;

	private final AdapterTessera	tesseraIniziale;
	private final Color				giocatore;
	private final Integer			numeroGiocatori;
	private final String			idPartita;

	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);

	}

}
