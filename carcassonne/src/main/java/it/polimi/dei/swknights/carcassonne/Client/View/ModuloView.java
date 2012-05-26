package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
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
	}
	
	public void run()
	{
		System.out.println("sono la view vera. che bello. ah no... sono astratta!");
	}
	
	public void riceviModificheModel(EventObject event)
	{
		
		if(event instanceof ControllerEvent)
		{
			
			if(event instanceof InizioGiocoEvent)
			{
				
				InizioGiocoEvent ige = (InizioGiocoEvent) event;
				
				Color coloreIniziale = ige.getGiocatore();
				AdapterTessera tessIniziale= ige.getTesseraIniziale();
				this.mettiEMostraPrimaTessera(tessIniziale);
				this.aggiornaColoreCorrente(coloreIniziale);
				
			}
			if( event instanceof MossaNonValidaEvent)
			{
				this.notificaMossaNonValida();
			}
			if( event instanceof FinePartitaEvent)
			{
				this.notificaFinePartita();
			}
			if (event instanceof UpdateTurnoEvent)
			{
				UpdateTurnoEvent ute = (UpdateTurnoEvent)event;
				Color colGiocatoreCorrente = ute.getGiocatoreCorrente();
				AdapterTessera tesseraNuova =  ute.getTessera();
				
				this.cambiaEMostraTesseraCorrente(tesseraNuova);
				this.aggiornaColoreCorrente(colGiocatoreCorrente);
			}
			if (event instanceof UpdateRotationEvent)
			{
				UpdateRotationEvent ure = (UpdateRotationEvent)event;
				AdapterTessera tesseraNuova = ure.getTessera();
				this.cambiaEMostraTesseraCorrente(tesseraNuova);
			}
			if(event instanceof UpdatePositionEvent)
			{
				//TODO: capire cos'è
				UpdatePositionEvent upe = (UpdatePositionEvent)event;
				//questo non ho capito cosa fa di preciso (non è quello del fascio di info??)
			}
			if(event instanceof CostruzioneCompletataEvent)
			{
				 CostruzioneCompletataEvent cce = (CostruzioneCompletataEvent)event;				 
				 Map<AdapterTessera, Coordinate> tessereAggiornate = cce.getTessereAggiornate();
			     this.ridaiSegnaliniDiTessere(tessereAggiornate);
			}
			
			
		}
		
		
	}
	
	protected abstract void ridaiSegnaliniDiTessere(Map<AdapterTessera, Coordinate> tessereAggiornate);
	
	protected abstract void mettiEMostraPrimaTessera(AdapterTessera tessIniziale);

	protected abstract void notificaFinePartita();
	
	protected abstract void notificaMossaNonValida();
	
	protected abstract void aggiornaColoreCorrente(Color colore);
	
	protected abstract void cambiaEMostraTesseraCorrente(AdapterTessera tessera);
	
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
	
	
	protected abstract void aggiornaMappa();

	protected abstract void posizionaTessera(Coordinate coordinatePosizione);

	protected abstract void muoviViewA(PuntoCardinale puntoCardinale, int quantita);

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

	protected final Coordinate centroScenario = new Coordinate(0, 0);
	
	private final ScenarioDiGioco	scenario;

	private List<Controller>		listeners;

	private Coordinate				coordinataNordOvest;

	private AdapterTessera			tesseraCorrente;

	private Color					coloreGiocatore;

}
