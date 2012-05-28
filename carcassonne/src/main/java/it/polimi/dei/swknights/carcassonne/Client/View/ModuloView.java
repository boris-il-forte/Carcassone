package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.UpdatePositionHandler;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.ControllerHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.PassHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.PlaceHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.RuotaHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.TileHandler;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.Map;

public abstract class ModuloView implements View
{
	public ModuloView()
	{
		this.listeners = new ArrayList<Controller>();
		this.scenario = new ScenarioDiGioco();
		this.visitorHandlers =  this.attivaHandler();
	}
		
		public void riceviInput(ControllerEvent event)
		{
			for (ViewHandler visitorHandler : this.visitorHandlers)
			{
				event.accept(visitorHandler);
			}
		}
		
		
		private List<ViewHandler> attivaHandler()
		{
			List<ViewHandler> handlerList = new ArrayList<ViewHandler>();
			handlerList.add(new UpdatePositionHandler());
			handlerList.add(new PlaceHandler(this, this.model));
			handlerList.add(new TileHandler(this, this.model));
			handlerList.add(new PassHandler());
			return handlerList;
		}

	public void run()
	{
		System.out.println("sono la view vera. che bello. ah no... sono astratta!");
	}

	public synchronized void riceviModificheModel(EventObject event)
	{
		System.out.println("sono la view, ho ricevuto " + event.toString());

		if (event instanceof ControllerEvent)
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

		}

		this.attendiInput();
	}

	public void addListener(EventListener eventListener)
	{
		if (eventListener instanceof Controller)
		{
			this.listeners.add((Controller) eventListener);
		}
	}

	public void removeListener(EventListener eventListener)
	{
		this.listeners.remove(eventListener);
	}

	public void fire(ViewEvent event)
	{
		for (Controller listener : listeners)
		{
			listener.riceviInput(event);
		}
	}

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

	private List<Controller>		listeners;

	private Coordinate				coordinataNordOvest;

	private AdapterTessera			tesseraCorrente;

	private Color					coloreGiocatore;
	
	private List<ViewHandler>     visitorHandlers;

}
