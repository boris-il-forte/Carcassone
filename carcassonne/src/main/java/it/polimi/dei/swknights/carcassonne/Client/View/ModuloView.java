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
import java.util.Map.Entry;

/**
 * This class gives a schema for the View
 * 
 * @see: {@link Cli} {@link Gui}
 * @author edoardopasi & dave
 * 
 */

public abstract class ModuloView extends AbstractView
{
	public ModuloView(Coordinate coordinateRelativeSE)
	{
		super();
		this.coordinateRelativeSE = coordinateRelativeSE;
		this.scenario = new ScenarioDiGioco();
		this.gestoreFasi = new GestoreFasi();
		this.attivaHanlders();
	}

	public abstract void visualizzaPunteggi(Punteggi punteggio);

	/**
	 * Notify the user the end of the game
	 */
	public abstract void notificaFinePartita();

	/**
	 * Notify the user a non-valid move eg 2,1 if 2,1 is not a free position eg
	 * a non-valid command like potate
	 */
	public abstract void notificaMossaNonValida();

	/**
	 * shows the current player's color
	 */
	public abstract void visualizzaColoreCorrente();

	/**
	 * Shows the current tile
	 * 
	 * @param tessera
	 *            the current tile to show
	 */
	public abstract void visualizzaTesseraCorrente(AdapterTessera tessera);

	/**
	 * A sort of Refresh of the map, to be called after a place card for
	 * instance
	 */
	public abstract void aggiornaMappa();

	/**
	 * Used in the beginning of the game, place the first card
	 * 
	 * @param tessIniziale
	 *            the first card
	 */
	public void mettiPrimaTessera(AdapterTessera tessIniziale)
	{
		this.setTesseraCorrente(tessIniziale);
		this.posizionaTessera(centroScenario);
	}

	/**
	 * After a costruction is completed, each controller of that construction
	 * has his/her controlling markers back; this method returns a map of each
	 * Card with no more marker on it
	 * 
	 * @param tessereAggiornate
	 *            map of the Cards interested by the completion of the
	 *            construction
	 */
	public void ridaiSegnaliniDiTessere(Map<AdapterTessera, Coordinate> tessereCostruzioneFinita)
	{
		ScenarioDiGioco scenario = this.getScenario();
		for (Entry<AdapterTessera, Coordinate> entryAdapterCoord : tessereCostruzioneFinita.entrySet())
		{
			Coordinate coord = entryAdapterCoord.getValue();
			AdapterTessera tessera = entryAdapterCoord.getKey();
			scenario.setTessera(coord, tessera);

		}
		this.aggiornaMappa();
	}

	/**
	 * Place a card in the given position, just graphical: does not implies any
	 * change in the model
	 * 
	 * @param coordinatePosizione
	 */

	public void posizionaTessera(Coordinate coordinatePosizione)
	{
		this.getScenario().setTessera(coordinatePosizione, this.getTesseraCorrente());
		this.getGestoreFasi().nextFase();
	}

	public void muoviViewA(Coordinate coordinate)
	{
		if (this.nelBoundingBox(coordinate))
		{
			this.setCoordinataNordOvest(coordinate);
		}
		this.aggiornaMappa();
	}

	public void muoviViewA(PuntoCardinale puntoCardinale, int quantita)
	{
		Coordinate coordinate;
		do
		{
			coordinate = this.getCoordinataNordOvest().getCoordinateA(puntoCardinale);
		} while (coordinate.getX() < quantita && coordinate.getY() < quantita && nelBoundingBox(coordinate));
		this.setCoordinataNordOvest(coordinate);
		this.aggiornaMappa();
	}

	/**
	 * Change player and show the new drawn Card
	 * 
	 * @param colGiocatoreCorrente
	 * @param tesseraNuova
	 */
	public void aggiornaTurno(Color colGiocatoreCorrente, AdapterTessera tesseraNuova)
	{
		this.visualizzaTesseraCorrente(tesseraNuova);
		this.visualizzaColoreCorrente();
		this.gestoreFasi.cominciaTurno();
	}

	public GestoreFasi getGestoreFasi()
	{
		return this.gestoreFasi;
	}

	public void setColore(Color coloreGiocatore)
	{
		this.coloreGiocatore = coloreGiocatore;
	}

	protected Color getColoreGiocatore()
	{
		return this.coloreGiocatore;
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

	protected void setCoordinataNordOvest(Coordinate coordinataNordOvest)
	{
		this.coordinataNordOvest = coordinataNordOvest;
	}

	protected Coordinate getCoordinataNordOvest()
	{
		return coordinataNordOvest;
	}

	protected Coordinate getCoordinateRelativeSE()
	{
		return coordinateRelativeSE;
	}

	private boolean nelBoundingBox(Coordinate coordinate)
	{
		ScenarioDiGioco scenario = this.getScenario();
		Coordinate min = scenario.getMin();
		Coordinate max = scenario.getMax();
		boolean isIn = min.getX() <= coordinate.getX();
		isIn = isIn && min.getY() <= coordinate.getY();
		isIn = isIn && max.getX() >= coordinate.getX();
		return isIn && max.getY() >= coordinate.getY();
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

	private GestoreFasi					gestoreFasi;

	private final ScenarioDiGioco		scenario;

	private AdapterTessera				tesseraCorrente;

	private Color						coloreGiocatore;

	private Coordinate					coordinataNordOvest;

	private final Coordinate			coordinateRelativeSE;

	protected static final Coordinate	centroScenario	= new Coordinate(0, 0);

}
