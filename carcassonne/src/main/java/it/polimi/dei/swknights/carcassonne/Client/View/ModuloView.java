package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.Client.View.Gui.Gui;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.CostruzioneCompletataHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.FinePartitaHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.InizioGiocoHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.MossaNonValidaHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdatePositionHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdateRotationHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdateTurnoHandler;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Fasi.GestoreFasi;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;
import java.util.Map;
/**
 * This class gives a schema for the View
 * @see: {@link Cli} {@link Gui}
 * @author edoardopasi & dave
 *
 */

public abstract class ModuloView extends AbstractView
{
	public ModuloView()
	{
		super();
		this.scenario = new ScenarioDiGioco();
		this.gestoreFasi = new GestoreFasi();
		this.attivaHanlders();
	}
	
	public abstract void visualizzaPunteggi(Punteggi punteggio);
	
	/**
	 * Wait for a user input
	 * eg in the Cli can wait for the "rotate" command 
	 */
	public abstract void attendiInput();
	/**
	 * After a costruction is completed, each controller of that construction
	 * has his/her controlling markers back;
	 * this method  returns a map of each Card with no more marker on it
	 * @param tessereAggiornate  map of the Cards interested by the completion of the 
	 * construction
	 */
	public abstract void ridaiSegnaliniDiTessere(Map<AdapterTessera, Coordinate> tessereAggiornate);
	/**
	 * Used in the beginning of the game, place the first card
	 * @param tessIniziale the first card
	 */
	public abstract void mettiPrimaTessera(AdapterTessera tessIniziale);

	/**
	 * Notify the user the end of the game
	 */
	public abstract void notificaFinePartita();
	
	/**
	 * Notify the user a non-valid move
	 * eg 2,1 if 2,1 is not a free position
	 * eg a  non-valid command like potate
	 */
	public abstract void notificaMossaNonValida();

	public abstract void visualizzaColoreCorrente();

	public abstract void cambiaEMostraTesseraCorrente(AdapterTessera tessera);
	/**
	 * A sort of Refresh of the map, to be called after a place card for instance 
	 */
	public abstract void aggiornaMappa();
	/**
	 * Place a card in the given position, just graphical: does not implies
	 * any change in the model
	 * @param coordinatePosizione
	 */
	public abstract void posizionaTessera(Coordinate coordinatePosizione);

	public abstract void muoviViewA(PuntoCardinale puntoCardinale, int quantita);
	/**
	 * Change player and show the new drawn Card
	 * @param colGiocatoreCorrente
	 * @param tesseraNuova
	 */
	public void aggiornaTurno(Color colGiocatoreCorrente, AdapterTessera tesseraNuova)
	{
		this.cambiaEMostraTesseraCorrente(tesseraNuova);
		this.visualizzaColoreCorrente();
		this.gestoreFasi.cominciaTurno();
	}

	public GestoreFasi getGestoreFasi()
	{
		return this.gestoreFasi;
	}

	protected Color getColoreGiocatore()
	{
		return this.coloreGiocatore;
	}

	public void setColore(Color coloreGiocatore)
	{
		this.coloreGiocatore = coloreGiocatore;
	}

	protected void setCoordinataNordOvest(Coordinate coordinataNordOvest)
	{
		this.coordinataNordOvest = coordinataNordOvest;
	}

	protected AdapterTessera getTesseraCorrente()
	{
		return tesseraCorrente;
	}

	protected void setTesseraCorrente(AdapterTessera tesseraCorrente)
	{
		this.tesseraCorrente = tesseraCorrente;
	}

	protected ScenarioDiGioco getScenario()
	{
		return this.scenario;
	}

	protected Coordinate getCoordinataNordOvest()
	{
		return coordinataNordOvest;
	}

	private void attivaHanlders()
	{
		this.addVisitorHandler(new InizioGiocoHandler(this));
		this.addVisitorHandler(new UpdateTurnoHandler(this));
		this.addVisitorHandler(new UpdateRotationHandler(this));
		this.addVisitorHandler(new UpdatePositionHandler(this));
		this.addVisitorHandler(new MossaNonValidaHandler(this));
		this.addVisitorHandler(new CostruzioneCompletataHandler(this));
		this.addVisitorHandler(new FinePartitaHandler(this));
	}

	protected static final Coordinate	centroScenario	= new Coordinate(0, 0);

	protected GestoreFasi				gestoreFasi;

	private final ScenarioDiGioco		scenario;

	private Coordinate					coordinataNordOvest;

	private AdapterTessera				tesseraCorrente;

	private Color						coloreGiocatore;

}
