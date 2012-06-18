package it.polimi.dei.swknights.carcassonne.Server.Model;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.ColoreNonPresenteException;
import it.polimi.dei.swknights.carcassonne.Exceptions.FinitiColoriDisponibiliException;
import it.polimi.dei.swknights.carcassonne.Exceptions.MossaNonValidaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Segnalino;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class gives the implementation of the Model of the MVC pattern It gives
 * all important methods to handle the data and basic low level function of the
 * game
 * 
 * @author edoardopasi & dave
 * 
 */

public class ModuloModel extends AbstractModel
{
	/**
	 * Default constructor. Initialize the data structures
	 */

	public ModuloModel(String idPartita)
	{
		super();
		this.datiPartita = new DatiPartita();
		this.idPartita = idPartita;
	}

	/**
	 * convenience constructor to create a model without a name
	 */
	public ModuloModel()
	{
		this("");
	}

	/**
	 * Rotate the current card
	 */
	public void ruotaTessera()
	{
		this.tesseraCorrente.ruota();
		this.fire(new UpdateRotationEvent(this.tesseraCorrente, this));
	}

	/**
	 * Add a marker to the current tile
	 * 
	 * @param puntoCardinale
	 *            a ardinal point where to place the marker
	 * @return the added marker
	 * @throws SegnaliniFinitiException
	 *             if current player has no more available markers
	 */
	public Segnalino addSegnalinoTesseraCorrente(PuntoCardinale puntoCardinale)
			throws SegnaliniFinitiException
	{
		Segnalino segnalino = this.getGiocatoreCorrente().getSegnalino();
		this.tesseraCorrente.setSegnalino(segnalino, puntoCardinale);
		this.fire(new UpdatePositionEvent(this.tesseraCorrente, this.coordinateTesseraCorrente, this));
		return segnalino;
	}

	/**
	 * Getter method
	 * 
	 * @return the current card
	 */
	public Tessera getTesseraCorrente()
	{
		return this.tesseraCorrente;
	}

	/**
	 * Basic operation to set up first things of the game: place the first card
	 * and creates players.
	 * 
	 * @param numeroGiocatori
	 *            : how many people are playing
	 * @throws PartitaFinitaException
	 */
	public void iniziaGioco() throws PartitaFinitaException
	{
		try
		{
			Tessera primaTessera = this.datiPartita.pescaPrimaTessera();
			this.datiPartita.getAreaDiGioco().addTessera(new Coordinate(0, 0), primaTessera);
			this.fire(new InizioGiocoEvent(this, primaTessera, null, this.getListaGiocatori().size(), this
					.getIdPartita()));
			this.getTesseraDaMazzo();
			this.cominciaTurno();

		}
		catch (MossaNonValidaException e)
		{
			throw new AssertionError(e);
		}
	}

	/**
	 * Begins the turn: change to current color and draw a card from deck
	 * 
	 * @throws PartitaFinitaException
	 */
	public void cominciaTurno() throws PartitaFinitaException
	{
		Color coloreGiocatore = this.getColoreGiocatoreCorrente();
		this.fire(new UpdateTurnoEvent(this, coloreGiocatore, this.tesseraCorrente));
	}

	/**
	 * pass to next turn
	 */
	public void nextTurno()
	{
		this.datiPartita.nextTurno();
	}

	/**
	 * Getter method
	 * 
	 * @param tessera
	 *            the card we want to ask about
	 * @return the coordinates of the given card
	 */
	public Coordinate getCoordinateTessera(Tessera tessera)
	{
		return this.datiPartita.getCoordinateTessera(tessera);
	}

	/**
	 * Getter method
	 * 
	 * @return the set of the empty coordinates where a card can be placed
	 */
	public Set<Coordinate> getSetVuote()
	{
		return this.datiPartita.getSetCoordinateVuote();
	}

	/**
	 * place the current card on the given coordinates
	 * 
	 * @param coordinate
	 *            where place the card
	 * @throws MossaNonValidaException
	 */
	public void posizionaTesseraCorrente(Coordinate coordinate) throws MossaNonValidaException
	{
		this.posizionaTessera(this.getTesseraCorrente(), coordinate);
	}

	/**
	 * Used to a generic card place a card in generic coordinates
	 * 
	 * @param tessera
	 *            the card
	 * @param coordinate
	 *            the coordinates
	 * @throws MossaNonValidaException
	 */
	public void posizionaTessera(Tessera tessera, Coordinate coordinate) throws MossaNonValidaException
	{
		if (tessera != null)
		{
			AreaDiGioco areaDiGioco = this.datiPartita.getAreaDiGioco();
			areaDiGioco.addTessera(coordinate, tessera);
			this.coordinateTesseraCorrente = coordinate;
			this.fire(new UpdatePositionEvent(tessera, coordinate, this));
		}
		else
		{
			throw new IllegalArgumentException(" tessera passata e' null ! ");
		}
	}

	/**
	 * gets the card at the given coordinates from {@link AreaDiGioco}
	 * 
	 * @param coordinate
	 * @return the card
	 * @throws TesseraNonTrovataException
	 */
	public Tessera getTessera(Coordinate coordinate) throws TesseraNonTrovataException
	{
		return this.datiPartita.getAreaDiGioco().getTessera(coordinate);
	}

	/**
	 * Draws a card from the deck and updates the current card with the drawn
	 * one
	 * 
	 * @throws PartitaFinitaException
	 */
	public void getTesseraDaMazzo() throws PartitaFinitaException
	{
		this.tesseraCorrente = this.datiPartita.pescaTesseraDalMazzo();
	}

	/**
	 * Notifies the end of the game
	 * 
	 * @param punteggi
	 *            the final score of all players
	 */
	public void notificaFinePartita(Punteggi punteggi)
	{
		this.datiPartita.aggiornaPunteggioGiocatori(punteggi);
		this.fire(new FinePartitaEvent(this, this.getPunteggi()));
	}

	/**
	 * notifies that a construction has been completed
	 * 
	 * @param tessere
	 *            the card to be updated (because they contains markers)
	 * @param punteggi
	 *            the score at the end of this turn
	 */
	public void notificaCostruzioneCompletata(List<Tessera> tessere, Punteggi punteggi)
	{
		this.datiPartita.aggiornaPunteggioGiocatori(punteggi);
		Map<AdapterTessera, Coordinate> mapTessere = new HashMap<AdapterTessera, Coordinate>();
		for (Tessera tessera : tessere)
		{
			this.removeSegnalino(tessera);

			Coordinate coordinate = this.datiPartita.getCoordinateTessera(tessera);
			mapTessere.put(new AdapterTesseraObject(tessera), coordinate);
		}

		this.fire(new CostruzioneCompletataEvent(this, mapTessere, this.getPunteggi()));
	}

	/**
	 * Add a player to the game
	 */
	public void addPlayer()
	{
		try
		{
			this.datiPartita.addGiocatore();
		}
		catch (FinitiColoriDisponibiliException e)
		{
		}
	}

	private void removeSegnalino(Tessera tessera)
	{
		Segnalino segnalino = tessera.removeSegnalino();
		if (segnalino != null)
		{
			try
			{
				Giocatore giocatore = this.datiPartita.getGiocatore(segnalino.getColore());
				giocatore.addSegnalino(segnalino);
			}
			catch (ColoreNonPresenteException e)
			{
			}
		}

	}

	private List<Giocatore> getListaGiocatori()
	{
		return this.datiPartita.getListaGiocatori();
	}

	private Color getColoreGiocatoreCorrente()
	{
		Giocatore giocatore = this.getGiocatoreCorrente();
		return giocatore.getColore();
	}

	private Giocatore getGiocatoreCorrente()
	{
		return this.datiPartita.getGiocatoreCorrente();
	}

	private String getIdPartita()
	{
		return this.idPartita;
	}

	private Punteggi getPunteggi()
	{
		Punteggi punteggi = new Punteggi();
		List<Giocatore> giocatori = this.getListaGiocatori();
		for (Giocatore giocatore : giocatori)
		{
			punteggi.addPunteggi(giocatore.getColore(), giocatore.getPunti());
		}
		return punteggi;
	}

	private DatiPartita		datiPartita;

	private Tessera			tesseraCorrente;

	private Coordinate		coordinateTesseraCorrente;

	private final String	idPartita;

}
