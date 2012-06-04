package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Fasi.GestoreFasi;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.ControllerHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.PassHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.PlaceHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.RuotaHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.TileHandler;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class that implements theController of the MVC pattern Contains all the
 * methods to manage event handling and the high level game logic.
 * 
 * @author dave
 * 
 */

public class ModuloController implements Controller
{
	/**
	 * Default Constructor. Initialize data structures
	 * 
	 */
	public ModuloController(ModuloModel model)
	{
		this.model = model;
		this.gestoreFasi = new GestoreFasi();
		this.contaPunti = new ContatoreCartografo(this.model);
		this.visitorHandlers = this.attivaHandler();
		
		 java.util.Date date= new java.util.Date();
		this.model.setIdPartita("GAME" + new Timestamp(date.getTime()) );
	}

	public void run()
	{
		
		this.primaMossaPartita();
		try
		{
			this.nextTurno();
			while (this.gestoreFasi.partitaOk())
			{
				this.primaMossaTurno();
				this.nextTurno();
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
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

	public synchronized void comunicaPosizionamentoTessera()
	{
		this.gestoreFasi.nextFase();
	}

	public void addSegnalinoTessera(Color colore, PuntoCardinale puntoCardinale)
	{
		this.contaPunti.addSegnalino(colore, puntoCardinale);
	}
	
	public boolean costruzioneLibera(PuntoCardinale punto)
	{
		Map<PuntoCardinale, Costruzione> mappaCostruzioni;
		mappaCostruzioni = this.contaPunti.getMapCostruzioniUltimaTessera();
		Costruzione costruzione = mappaCostruzioni.get(punto);
		if (costruzione.controllataDa().size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public ContatoreCartografo getContapunti()
	{
		return this.contaPunti;
	}

	public GestoreFasi getGestoreFasi()
	{
		return this.gestoreFasi;
	}

	private void nextTurno() throws InterruptedException
	{
		this.attendiPosizionamentoTessera();
		if(this.contaPunti.areCostruzioniCompletate())
		{
			this.comunicaCostruzioneCompletata();
		}
		this.model.nextTurno();
		this.gestoreFasi.cominciaTurno();
	}

	private void comunicaCostruzioneCompletata()
	{
		Set<Costruzione> costruzioniCompletate = this.contaPunti.getCostruzioniCompletate();
		Punteggi punteggi = this.contaPunti.getPunteggioTurno();
		List<Tessera> listaTessere = this.getListaTessereCostruzioni(costruzioniCompletate);
		this.model.notificaCostruzioneCompletata(listaTessere, punteggi);
	}

	private List<Tessera> getListaTessereCostruzioni(Set<Costruzione> costruzioniCompletate)
	{
		List<Tessera> listaTessere = new ArrayList<Tessera>();
		for(Costruzione costruzione : costruzioniCompletate)
		{
			for(Tessera tessera : costruzione.getTessere())
			{ 
				listaTessere.add(tessera);
			}
		}
		return listaTessere;
	}

	
	private void primaMossaPartita()
	{
		final Coordinate origine = new Coordinate(0, 0);
		try
		{
			this.model.iniziaGioco(NUMBER_OF_PLAYER);
			this.contaPunti.riceviCoordinateTessera(origine);
			this.gestoreFasi.cominciaTurno();
		
		}
		catch (PartitaFinitaException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}

	private void primaMossaTurno()
	{
		try
		{
			this.model.cominciaTurno();
		}
		catch (PartitaFinitaException e)
		{
			Punteggi punteggi = this.contaPunti.getPunteggioFinale();
			this.model.notificaFinePartita(punteggi);
		}
	}

	private List<ControllerHandler> attivaHandler()
	{
		List<ControllerHandler> handlerList = new ArrayList<ControllerHandler>();
		handlerList.add(new RuotaHandler(this, this.model));
		handlerList.add(new PlaceHandler(this, this.model));
		handlerList.add(new TileHandler(this, this.model));
		handlerList.add(new PassHandler(this, this.model));
		return handlerList;
	}

	synchronized private void attendiPosizionamentoTessera() throws InterruptedException
	{
		while (this.gestoreFasi.inputOk())
		{
			wait();
		}
	}

	
	
	private List<ControllerHandler>	visitorHandlers;

	private ContatoreCartografo		contaPunti;

	private final GestoreFasi		gestoreFasi;

	private final ModuloModel		model;

	private static final int		NUMBER_OF_PLAYER	= 2;	// TODO: ask
																// user.

}
