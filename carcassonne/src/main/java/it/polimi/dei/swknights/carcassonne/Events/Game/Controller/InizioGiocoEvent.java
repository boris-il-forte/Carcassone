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

	/**
	 * Constructor used if you have the card object 
	 * 
	 * @param source
	 *            the event's source
	 * @param tesseraIniziale
	 *            the starting tile
	 * @param giocatore
	 *            the color of the player
	 * @param numeroGiocatori
	 *            the number of the players
	 * @param idPartita
	 *            the game id
	 */
	public InizioGiocoEvent(Object source, Tessera tesseraIniziale, Color giocatore, Integer numeroGiocatori,
			String idPartita)
	{
		super(source);
		AdapterTessera adapterTessera = new AdapterTesseraObject(tesseraIniziale);
		this.setData(giocatore, adapterTessera, idPartita, numeroGiocatori);

	}

	/**
	 * Constructor used if you have the card string
	 * @param source
	 *            the event's source
	 * @param tesseraIniziale
	 *            the starting tile
	 * @param giocatore
	 *            the color of the player
	 * @param numeroGiocatori
	 *            the number of the players
	 * @param idPartita
	 *            the game id
	 */
	public InizioGiocoEvent(Object source, String tesseraIniziale, Color giocatore, Integer numeroGiocatori,
			String idPartita)
	{
		super(source);
		AdapterTessera adapterTessera = new AdapterTesseraString(tesseraIniziale);
		this.setData(giocatore, adapterTessera, idPartita, numeroGiocatori);
	}

	/**
	 * Constructor used if you have the card adapter
	 * @param source
	 *            the event's source
	 * @param tesseraIniziale
	 *            the starting tile
	 * @param giocatore
	 *            the color of the player
	 * @param numeroGiocatori
	 *            the number of the players
	 * @param idPartita
	 *            the game id
	 */
	public InizioGiocoEvent(Object source, AdapterTessera adapterTessera, Color giocatore,
			int numeroGiocatori, String idPartita)
	{
		super(source);
		this.setData(giocatore, adapterTessera, idPartita, numeroGiocatori);
	}

	/**
	 * accept method for visitor's pattern
	 */
	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);
	}

	/**
	 * Getter method
	 * @return the starting tile
	 */
	public AdapterTessera getTesseraIniziale()
	{
		return this.tesseraIniziale;
	}

	/**
	 * Getetr method
	 * @return the game id
	 */
	public String getIdPartita()
	{
		return this.idPartita;
	}

	/**
	 * Getter method
	 * @return the number of players
	 */
	public int getNumGiocatori()
	{
		return this.numeroGiocatori;
	}

	/**
	 * Getetr method
	 * @return the player's color
	 */
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
