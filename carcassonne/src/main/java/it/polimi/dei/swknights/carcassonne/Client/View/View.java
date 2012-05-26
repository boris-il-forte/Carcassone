package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.EventSource;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public abstract class View implements EventSource
{
	public View()
	{
		this.listeners = new ArrayList<Controller>();
		this.scenario = new ScenarioDiGioco();
	}

	public void addListener(EventListener eventListener)
	{
		if (eventListener instanceof Controller)
		{
			this.listeners.add((Controller) eventListener);
		}
	}

	public Color getColoreGiocatore()
	{
		return this.coloreGiocatore;
	}

	public void removeListener(EventListener eventListener)
	{
		this.listeners.remove(eventListener);
	}

	public abstract void aggiornaMappa();

	public abstract void posizionaTessera(Coordinate coordinatePosizione);

	public void setColore(Color coloreGiocatore)
	{
		this.coloreGiocatore = coloreGiocatore;
	}

	public void setCoordinataNordOvest(Coordinate coordinataNordOvest)
	{
		this.coordinataNordOvest = coordinataNordOvest;
	}

	public AdapterTessera getTesseraCorrente()
	{
		return tesseraCorrente;
	}

	public void setTesseraCorrente(AdapterTessera tesseraCorrente)
	{
		this.tesseraCorrente = tesseraCorrente;
	}

	public abstract void muoviViewA(PuntoCardinale puntoCardinale, int quantita);

	public void fire(ViewEvent event)
	{
		for (Controller listener : listeners)
		{
			listener.riceviInput(event);
		}
	}

	public void setFaseGioco(fasiTurno fase)
	{
		this.currentPhase = fase;
	}

	protected ScenarioDiGioco getScenario()
	{
		return this.scenario;
	}

	protected Coordinate getCoordinataNordOvest()
	{
		return coordinataNordOvest;
	}

	private final ScenarioDiGioco	scenario;

	private List<Controller>		listeners;

	private Coordinate				coordinataNordOvest;

	private AdapterTessera			tesseraCorrente;

	private Color					coloreGiocatore;

	protected fasiTurno				currentPhase;

	public enum fasiTurno {
		Inzio("Place card or rotate"), Media("Tile or pass"), Attesa("wait server response...");

		private String	messaggioUtente;

		public String toString()
		{
			return this.messaggioUtente;
		}

		private fasiTurno(String messaggio)
		{
			this.messaggioUtente = messaggio;
		}

	}

}
