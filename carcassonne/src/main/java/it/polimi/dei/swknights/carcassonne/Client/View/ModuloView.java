package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.GestoreFasi;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.CostruzioneCompletataHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.FinePartitaHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.InizioGiocoHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.MossaNonValidaHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdatePositionHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdateRotationHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdateTurnoHandler;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;

import java.awt.Color;
import java.util.Map;

public abstract class ModuloView extends AbstractView
{
	public ModuloView()
	{
		super();
		this.scenario = new ScenarioDiGioco();
		this.statoPartita = new StatoPartita();
		this.gestoreFasi = new GestoreFasi();
		this.attivaHanlders();
	}

	public abstract void attendiInput();

	public abstract void ridaiSegnaliniDiTessere(Map<AdapterTessera, Coordinate> tessereAggiornate);

	public abstract void mettiPrimaTessera(AdapterTessera tessIniziale);

	public abstract void notificaFinePartita();

	public abstract void notificaMossaNonValida();

	public abstract void aggiornaColoreCorrente(Color colore);

	public abstract void cambiaEMostraTesseraCorrente(AdapterTessera tessera);

	public abstract void aggiornaMappa();

	public abstract void posizionaTessera(Coordinate coordinatePosizione);

	public abstract void muoviViewA(PuntoCardinale puntoCardinale, int quantita);

	public void aggiornaTurno(Color colGiocatoreCorrente, AdapterTessera tesseraNuova)
	{
		this.cambiaEMostraTesseraCorrente(tesseraNuova);
		this.aggiornaColoreCorrente(colGiocatoreCorrente);
		this.gestoreFasi.cominciaTurno();
	}

	public void setPartitaCominciata()
	{
		this.statoPartita.setPartitaCominciata(true);
	}

	public GestoreFasi getGestoreFasi()
	{
		return this.gestoreFasi;
	}

	protected Color getColoreGiocatore()
	{
		return this.coloreGiocatore;
	}

	protected void setColore(Color coloreGiocatore)
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

	protected StatoPartita				statoPartita;

	protected GestoreFasi				gestoreFasi;

	private final ScenarioDiGioco		scenario;

	private Coordinate					coordinataNordOvest;

	private AdapterTessera				tesseraCorrente;

	private Color						coloreGiocatore;

}
