package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.FinitiColoriDisponibiliException;
import it.polimi.dei.swknights.carcassonne.Exceptions.MossaNonValidaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class gives the implementation of the Model of the MVC pattern
 * It gives all important methods to handle the data and basic low level function
 * of the game
 * @author edoardopasi & dave
 *
 */

public class ModuloModel extends AbstractModel
{
	/**
	 * Default constructor. Initialize the data structures
	 */

	public ModuloModel()
	{
		super();
		this.datiPartita = new DatiPartita();
	}

	public void ruotaTessera()
	{
		this.tesseraCorrente.ruota();
		Color coloreGiocatoreCorrente = this.getColoreGiocatoreCorrente();

		this.fire(new UpdateRotationEvent(this.tesseraCorrente, coloreGiocatoreCorrente, this));
	}

	public void addSegnalinoTesseraCorrente(PuntoCardinale puntoCardinale) throws SegnaliniFinitiException
	{
		Segnalino segnalino = this.getGiocatoreCorrente().getSegnalino();
		this.tesseraCorrente.setSegnalino(segnalino, puntoCardinale);
		this.fire(new UpdatePositionEvent(this.tesseraCorrente, this.coordinateTesseraCorrente, this
				.getColoreGiocatoreCorrente(), this));
	}

	public Tessera getTesseraCorrente()
	{
		return this.tesseraCorrente;
	}
	/**
	 * Creates the requested number of players
	 * @param numeroGiocatori
	 */
	public void creaGiocatori(int numeroGiocatori)
	{
		try
		{
			for (int i = 1; i < numeroGiocatori; i++)
			{
				this.datiPartita.addGiocatore();
			}
		}
		catch (FinitiColoriDisponibiliException e)
		{
			throw new IllegalArgumentException("hai chiesto troppi giocatori");
		}

	}
	/**
	 * Basic operation to set up first things of the game:
	 * place the first card and creates players.
	 * @param numeroGiocatori: how many people are playing
	 * @throws PartitaFinitaException
	 */
	public void iniziaGioco(int numeroGiocatori) throws PartitaFinitaException
	{
		try
		{
			Tessera primaTessera = this.datiPartita.pescaPrimaTessera();
			AdapterTessera tessera = new AdapterTesseraObject(primaTessera);
			this.creaGiocatori(numeroGiocatori);

			this.datiPartita.getAreaDiGioco().addTessera(new Coordinate(0, 0), primaTessera);

			this.fire(new InizioGiocoEvent(this, tessera, this.getGiocatoreCorrente().getColore()));
			this.cominciaTurno();
		}
		catch (MossaNonValidaException e)
		{
			System.exit(0);
			return;
		}
	}
	/**
	 * Begins the turn:
	 * change to current color and draw a card from deck
	 * @throws PartitaFinitaException
	 */
	public void cominciaTurno() throws PartitaFinitaException
	{
		Color coloreGiocatore = this.getColoreGiocatoreCorrente();
		this.getTesseraDaMazzo();
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
	 * place the current card on the given coordinates
	 * @param coordinate where place the card
	 * @throws MossaNonValidaException
	 */
	public void posizionaTesseraCorrente(Coordinate coordinate) throws MossaNonValidaException
	{
		this.posizionaTessera(tesseraCorrente, coordinate);
	}
	/**
	 * Used to a generic card place a card in generic coordinates
	 * @param tessera  the card
	 * @param coordinate  the coordinates
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
	 * gets the current card
	 * @param coordinate
	 * @return
	 * @throws TesseraNonTrovataException
	 */
	public Tessera getTessera(Coordinate coordinate) throws TesseraNonTrovataException
	{
		return this.datiPartita.getAreaDiGioco().getTessera(coordinate);
	}

	public void getTesseraDaMazzo() throws PartitaFinitaException
	{
		this.tesseraCorrente = this.datiPartita.pescaTesseraDalMazzo();
	}

	public void notificaFinePartita(Punteggi punteggi)
	{
		this.datiPartita.aggiornaPunteggioGiocatori(punteggi);
		this.fire(new FinePartitaEvent(this, this.getMappaPunteggi()));
	}

	public void notificaCostruzioneCompletata(List<Tessera> tessere, Punteggi punteggi)
	{
		//TODO: manca da segnalare alla view!
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

	private Map<Color, Integer> getMappaPunteggi()
	{
		Map<Color, Integer> mapPunteggi = new HashMap<Color, Integer>();
		List<Giocatore> giocatori = this.getListaGiocatori();
		for (Giocatore giocatore : giocatori)
		{
			mapPunteggi.put(giocatore.getColore(), giocatore.getPunti());
		}
		return mapPunteggi;
	}

	private DatiPartita	datiPartita;

	private Tessera		tesseraCorrente;

	private Coordinate	coordinateTesseraCorrente;

}
