package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.CostruzioneCompletataHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.FinePartitaHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.InizioGiocoHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.MossaNonValidaHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdatePositionHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdateRotationHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdateTurnoHandler;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Fasi.GestoreFasi;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PunteggiSegnalini;
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

public abstract class ModuloView extends AbstractModuloView
{
	public ModuloView()
	{
		super();
		this.scenario = new ScenarioDiGioco();
		this.gestoreFasi = new GestoreFasi();
		this.attivaHanlders();
		this.inizializzaSegnalini();
	}

	public abstract void visualizzaPunteggi(Punteggi punteggio);

	/**
	 * Notify the user the end of the game
	 * 
	 * @param vincitori
	 *            the players ho win the game
	 */
	public abstract void notificaFinePartita(String vincitori);

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

	public void setNumeroPlayer(int numGiocatori)
	{
		this.numGiocatori = numGiocatori;
	}

	/**
	 * Used in the beginning of the game, place the first card
	 * 
	 * @param tessIniziale
	 *            the first card
	 */
	public void mettiPrimaTessera(AdapterTessera tessIniziale)
	{
		this.setTesseraCorrente(tessIniziale);
		this.posizionaTessera(this.getTesseraCorrente(), CENTRO_SCENARIO);
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
		for (Entry<AdapterTessera, Coordinate> entryAdapterCoord : tessereCostruzioneFinita.entrySet())
		{
			Coordinate coord = entryAdapterCoord.getValue();

			Color coloreSegnalino = this.scenario.getTessera(coord).getColorSegnalino();
			this.segnalini.addPunteggi(coloreSegnalino, 1);

			AdapterTessera tessera = entryAdapterCoord.getKey();
			this.scenario.setTessera(coord, tessera);

		}
		this.aggiornaMappa();
	}

	/**
	 * Place a card in the given position, just graphical: does not implies any
	 * change in the model
	 * 
	 * @param tessera
	 * 
	 * @param coordinatePosizione
	 */

	public void posizionaTessera(AdapterTessera tessera, Coordinate coordinatePosizione)
	{
		Color coloreSegnalino = tessera.getColorSegnalino();
		if (coloreSegnalino != null)
		{
			this.segnalini.diminuisci(coloreSegnalino);
		}
		this.getScenario().setTessera(coordinatePosizione, tessera);
		this.getGestoreFasi().nextFase();
	}

	public void muoviViewA(Coordinate coordinate)
	{
		if(!this.gestoreFasi.partitaCominciata()){return;}
		if (this.nelBoundingBox(coordinate))
		{
			this.setCoordinataNordOvest(coordinate);
		}
		this.aggiornaMappa();
	}

	public void muoviViewA(PuntoCardinale puntoCardinale, int quantita)
	{
		if(!this.gestoreFasi.partitaCominciata()){return;}
		Coordinate coordinate = this.coordinateNordOvest;
		Coordinate nuoveCoordinate = coordinate.getCoordinateA(puntoCardinale);

		for (int i = 0; i < quantita && this.nelBoundingBox(nuoveCoordinate); i++)
		{
			coordinate = nuoveCoordinate;
			nuoveCoordinate = nuoveCoordinate.getCoordinateA(puntoCardinale);
		}

		this.coordinateNordOvest = coordinate;

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
		this.visualizzaTesseraCorrente(tesseraNuova); // cli e view implementano
														// diversamente!
		this.visualizzaColoreCorrente();
		this.gestoreFasi.cominciaTurno();
	}

	public void setColore(Color colore)
	{
		this.myColore = colore;
	}

	public GestoreFasi getGestoreFasi()
	{
		return this.gestoreFasi;
	}

	public void setColoreCorrente(Color coloreGiocatore)
	{
		this.coloreGiocatoreCorrente = coloreGiocatore;
	}

	protected int getNumeroSegnalini(Color colore)
	{
		return this.segnalini.get(colore);
	}

	protected int getNumeroPlayer()
	{
		return this.numGiocatori;
	}

	protected boolean turnoCorretto()
	{
		if (this.myColore == null)
		{
			return true;
		}
		else
		{
			return this.myColore.equals(this.coloreGiocatoreCorrente);
		}
	}

	protected Color getColoreGiocatoreCorrente()
	{
		return this.coloreGiocatoreCorrente;
	}

	protected AdapterTessera getTesseraCorrente()
	{
		return this.tesseraCorrente;
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
		this.coordinateNordOvest = coordinataNordOvest;
	}

	protected Coordinate getCoordinateNordOvest()
	{
		return this.coordinateNordOvest;
	}

	protected void setCoordinateRelativeSE(Coordinate coordinateRelativeSE)
	{
		this.coordinateRelativeSE = coordinateRelativeSE;
	}

	protected Coordinate getCoordinateRelativeSE()
	{
		return this.coordinateRelativeSE;
	}

	protected boolean isIn(Coordinate coordinate, Coordinate min, Coordinate max)
	{
		return this.isIn(max, min, coordinate, coordinate);
	}

	protected boolean isIn(Coordinate nordOvest, Coordinate sudEst, Coordinate max, Coordinate min)
	{
		boolean fuoriInBasso = min.getY() < nordOvest.getY();
		boolean fuoriInAlto = max.getY() > sudEst.getY();
		boolean fuoriADestra = min.getX() < nordOvest.getX();
		boolean fuoriASinistra = max.getX() > sudEst.getX();

		return !(fuoriInBasso || fuoriInAlto || fuoriADestra || fuoriASinistra);
	}

	private void inizializzaSegnalini()
	{
		final int maxSegnalini = 7;
		this.segnalini = new PunteggiSegnalini();
		for (Color colore : ColoriGioco.getListaColori())
		{
			this.segnalini.addPunteggi(colore, maxSegnalini);
		}
	}

	private boolean nelBoundingBox(Coordinate coordinate)
	{
		Coordinate nordOvest = coordinate;
		Coordinate sudEst = coordinate.getCoordinateA(this.coordinateRelativeSE);
		Coordinate min = this.scenario.getMin();
		Coordinate max = this.scenario.getMax();
		return this.isIn(nordOvest, sudEst, min, max);
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

	private Color						myColore		= null;

	private int							numGiocatori;

	private PunteggiSegnalini			segnalini;

	private AdapterTessera				tesseraCorrente;

	private Color						coloreGiocatoreCorrente;

	private Coordinate					coordinateNordOvest;

	private Coordinate					coordinateRelativeSE;

	private final GestoreFasi			gestoreFasi;

	private final ScenarioDiGioco		scenario;

	protected static final Coordinate	CENTRO_SCENARIO	= new Coordinate(0, 0);

}
