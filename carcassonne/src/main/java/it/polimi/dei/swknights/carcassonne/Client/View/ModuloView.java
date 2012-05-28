package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;

import java.awt.Color;
import java.util.Map;

public abstract class ModuloView extends AbstractView
{
	public ModuloView()
	{
		super();
		this.scenario = new ScenarioDiGioco();
	}

/*
 * if (event instanceof ControllerEvent)
		{

			if (event instanceof InizioGiocoEvent)
			{

				InizioGiocoEvent ige = (InizioGiocoEvent) event;

				Color coloreIniziale = ige.getGiocatore();
				AdapterTessera tessIniziale = ige.getTesseraIniziale();
				this.faseTurno = FasiTurno.PreparazioneGioco;
				this.mettiEMostraPrimaTessera(tessIniziale);
				this.aggiornaColoreCorrente(coloreIniziale);

			}
			if (event instanceof MossaNonValidaEvent)
			{
				this.notificaMossaNonValida();
			}
			if (event instanceof FinePartitaEvent)
			{
				this.notificaFinePartita();
			}
			if (event instanceof UpdateTurnoEvent)
			{
				this.aggiornaMappa();

				UpdateTurnoEvent ute = (UpdateTurnoEvent) event;
				Color colGiocatoreCorrente = ute.getGiocatoreCorrente();
				AdapterTessera tesseraNuova = ute.getTessera();

				this.faseTurno = FasiTurno.Inizio;
				this.cambiaEMostraTesseraCorrente(tesseraNuova);
				this.aggiornaColoreCorrente(colGiocatoreCorrente);

			}
			if (event instanceof UpdateRotationEvent)
			{
				this.aggiornaMappa();
				UpdateRotationEvent ure = (UpdateRotationEvent) event;
				AdapterTessera tesseraNuova = ure.getTessera();
				this.cambiaEMostraTesseraCorrente(tesseraNuova);
			}
			if (event instanceof UpdatePositionEvent)
			{

				UpdatePositionEvent upe = (UpdatePositionEvent) event;
				Coordinate coord = upe.getCoordinate();
				this.posizionaTessera(coord);
				this.aggiornaMappa();
			}
			if (event instanceof CostruzioneCompletataEvent)
			{
				CostruzioneCompletataEvent cce = (CostruzioneCompletataEvent) event;
				Map<AdapterTessera, Coordinate> tessereAggiornate = cce.getTessereAggiornate();
				this.ridaiSegnaliniDiTessere(tessereAggiornate);
			}
 */

	public abstract void attendiInput();

	public abstract void ridaiSegnaliniDiTessere(Map<AdapterTessera, Coordinate> tessereAggiornate);

	public abstract void mettiEMostraPrimaTessera(AdapterTessera tessIniziale);

	public abstract void notificaFinePartita();

	public abstract void notificaMossaNonValida();

	public abstract void aggiornaColoreCorrente(Color colore);

	public abstract void cambiaEMostraTesseraCorrente(AdapterTessera tessera);

	public abstract void aggiornaMappa();

	public abstract void posizionaTessera(Coordinate coordinatePosizione);

	public abstract void muoviViewA(PuntoCardinale puntoCardinale, int quantita);

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

	protected FasiTurno getFaseTurno()
	{
		return this.faseTurno;
	}

	protected void setFaseTurno(FasiTurno fase)
	{
		this.faseTurno = fase;
	}

	protected final Coordinate		centroScenario	= new Coordinate(0, 0);

	public FasiTurno				faseTurno;

	private final ScenarioDiGioco	scenario;

	private Coordinate				coordinataNordOvest;

	private AdapterTessera			tesseraCorrente;

	private Color					coloreGiocatore;

}
