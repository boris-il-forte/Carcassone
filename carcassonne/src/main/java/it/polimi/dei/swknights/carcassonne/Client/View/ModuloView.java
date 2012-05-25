package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

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

	public abstract void aggiornaMappa();

	public abstract void posizionaTessera(Coordinate coordinatePosizione);

	public abstract void muoviViewA(PuntoCardinale puntoCardinale, int quantita);

	public Color getColoreGiocatore()
	{
		return this.coloreGiocatore;
	}
	
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

	public void fire(ViewEvent event)
	{
		for (Controller listener : listeners)
		{
			listener.riceviInput(event);
		}
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

}
