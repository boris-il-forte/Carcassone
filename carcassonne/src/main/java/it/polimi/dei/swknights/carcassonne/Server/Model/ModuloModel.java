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

	public ModuloModel()
	{
		this("");
	}

	public void ruotaTessera()
	{
		this.tesseraCorrente.ruota();
		Color coloreGiocatoreCorrente = this.getColoreGiocatoreCorrente();

		this.fire(new UpdateRotationEvent(this.tesseraCorrente, coloreGiocatoreCorrente, this));
	}

	public Segnalino addSegnalinoTesseraCorrente(PuntoCardinale puntoCardinale)
			throws SegnaliniFinitiException
	{
		Segnalino segnalino = this.getGiocatoreCorrente().getSegnalino();
		this.tesseraCorrente.setSegnalino(segnalino, puntoCardinale);
		this.fire(new UpdatePositionEvent(this.tesseraCorrente, this.coordinateTesseraCorrente, this
				.getColoreGiocatoreCorrente(), this));
		return segnalino;
	}

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
			AdapterTessera tessera = new AdapterTesseraObject(primaTessera);
			this.datiPartita.getAreaDiGioco().addTessera(new Coordinate(0, 0), primaTessera);
			this.fire(new InizioGiocoEvent(this, tessera, this.getGiocatoreCorrente().getColore(), this
					.getListaGiocatori().size(), this.getIdPartita()));
			this.getTesseraDaMazzo();
			this.cominciaTurno();

		}
		catch (MossaNonValidaException e)
		{
			System.exit(0);
			return;
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

	public Coordinate getCoordinateTessera(Tessera tessera)
	{
		return this.datiPartita.getCoordinateTessera(tessera);
	}

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
		this.posizionaTessera(this.tesseraCorrente, coordinate);
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

		AreaDiGioco areaDiGioco = this.datiPartita.getAreaDiGioco();
		Giocatore giocatore = this.datiPartita.getGiocatoreCorrente();
		areaDiGioco.addTessera(coordinate, tessera);
		this.coordinateTesseraCorrente = coordinate;
		this.fire(new UpdatePositionEvent(tessera, coordinate, giocatore.getColore(), this));

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

	public void notificaFinePartita(Punteggi punteggi)
	{
		this.datiPartita.aggiornaPunteggioGiocatori(punteggi);
		this.fire(new FinePartitaEvent(this, this.getPunteggi()));
	}

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

	public void addPlayer()
	{
		try
		{
			this.datiPartita.addGiocatore();
		}
		catch (FinitiColoriDisponibiliException e)
		{
			e.printStackTrace();
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
				e.printStackTrace();
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
