package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.Events.EventSource;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.ControllerHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.PlaceHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.RuotaHandler;
import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;
import it.polimi.dei.swknights.carcassonne.server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that implements theController of the MVC pattern Contains all the
 * methods to manage event handling and the high level game logic.
 * 
 * @author dave
 * 
 */

public class ModuloController implements Controller, EventSource
{
	/**
	 * Default Constructor. Initialize data structures
	 * 
	 */
	public ModuloController()
	{
		this.listeners = new ArrayList<View>();
		this.partita = new DatiPartita();
		this.contaPunti = new ContatoreCartografo(this.partita.getAreaDiGioco());
		this.visitorHandlers = this.attivaHandler();
		this.areaGioco = partita.getAreaDiGioco();

	}
	
	public void cominciaGioco()
	{
		
		//set up vari?
		this.primaMossaPartita();
	}

	
	public Tessera getTesseraCorrente()
	{
		return this.tesseraCorrente;
	}

	public ContatoreCartografo getContapunti()
	{
		return this.contaPunti;
	}

	public Color getGiocatoreCorrente()
	{
		Giocatore giocatore = this.partita.getGiocatoreCorrente();
		return giocatore.getColore();
	}

	/**
	 * Add Listener to the listerner list
	 * 
	 * @param listener
	 *            to be added, if it is not a ViewListener, it would not be
	 *            added to the list
	 * @see it.polimi.dei.swknights.carcassonne.Events.EventSource#addListener(java.util.EventListener)
	 */

	public void addListener(EventListener eventListener)
	{
		View controllerListener;
		// TODO this.blocco instanceof Male! xD
		if (eventListener instanceof View)
		{
			controllerListener = (View) eventListener;
			this.listeners.add(controllerListener);
		}
	}

	/**
	 * Remove listener to the listener list
	 * 
	 * @param listener
	 *            to be removed from the listener list
	 * @see it.polimi.dei.swknights.carcassonne.Events.EventSource#removeListener(java.util.EventListener)
	 */

	public void removeListener(EventListener eventListener)
	{
		listeners.remove(eventListener);
	}

	/**
	 * Method that should be called when firing an event to the Controller
	 * 
	 * @see it.polimi.dei.swknights.carcassonne.Events.Controller#riceviInput()
	 */
	public void riceviInput(ViewEvent event)
	{
		for (ControllerHandler visitorHandler : this.visitorHandlers)
		{
			event.accept(visitorHandler);
		}
	}

	public void run()
	{
		try
		{
			while (true)
			{
				this.cominciaTurno();
				this.attendiPosizionamentoTessera();
			}

		}
		catch (PartitaFinitaException e)
		{
			this.fire(new FinePartitaEvent(this, this.getMappaPunteggi()));

		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void fire(EventObject event)
	{
		System.out.println("sono controller: lancio event:" + event.toString());
		for (View listener : this.listeners)
		{
			listener.riceviModificheModel(event);
		}
	}

	public void cominciaTurno() throws PartitaFinitaException
	{
		// Inizia il turno
		Giocatore giocatoreCorrente = this.partita.getGiocatoreCorrente();
		this.estraiTessera();
		this.fire(new UpdateTurnoEvent(this, giocatoreCorrente.getColore(), this.tesseraCorrente));
	}

	protected ContatoreCartografo getContaPunti()
	{
		return this.contaPunti;
	}

	private void primaMossaPartita()
	{
		try
		{
			this.estraiTessera();
		}
		catch (PartitaFinitaException e)
		{
			e.printStackTrace();
		}
		this.areaGioco.addTessera(this.COORD_PRIMA_TESSERA, this.tesseraCorrente);
	    //dico alla gui!
		this.fire(new PlaceEvent(this, COORD_PRIMA_TESSERA));
	}

	private List<ControllerHandler> attivaHandler()
	{
		List<ControllerHandler> handlerList = new ArrayList<ControllerHandler>();
		handlerList.add(new RuotaHandler(this));
		handlerList.add(new PlaceHandler(this, this.partita.getAreaDiGioco()));
		return handlerList;
	}

	synchronized private void attendiPosizionamentoTessera() throws InterruptedException
	{
		while (!this.tesseraPosizionata)
		{
			wait();
		}
	}

	private void estraiTessera() throws PartitaFinitaException
	{
		tesseraCorrente = this.partita.getTessera();
	}

	private Map<Color, Integer> getMappaPunteggi()
	{
		Map<Color, Integer> mapPunteggi = new HashMap<Color, Integer>();
		List<Giocatore> giocatori = this.partita.getListaGiocatori();
		for (Giocatore giocatore : giocatori)
		{
			mapPunteggi.put(giocatore.getColore(), giocatore.getPunti());
		}
		return mapPunteggi;
	}

	private boolean	tesseraPosizionata;

	private List<View>	listeners;

	private List<ControllerHandler>		visitorHandlers;

	private Tessera						tesseraCorrente;

	private ContatoreCartografo			contaPunti;

	private DatiPartita					partita;
	
	private final AreaDiGioco					areaGioco;
	
	private final Coordinate COORD_PRIMA_TESSERA = new Coordinate(0, 0);

}
