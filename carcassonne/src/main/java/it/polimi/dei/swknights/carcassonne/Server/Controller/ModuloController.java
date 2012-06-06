package it.polimi.dei.swknights.carcassonne.Server.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Fasi.GestoreFasi;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.AbstractController;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.ControllerHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.PassHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.PlaceHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.RuotaHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.TileHandler;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;
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

public class ModuloController extends AbstractController
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
	 * @see it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller#riceviInput()
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
	
	
	public boolean tuttoVicinatoDAccordo(Coordinate coordinate)
	{
		return this.tuttoVicinatoDAccordo(coordinate, this.model.getTesseraCorrente());
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
			do
			{
				this.model.getTesseraDaMazzo();
			}while(this.tesseraEstrattaValida());
			this.model.cominciaTurno();
		}
		catch (PartitaFinitaException e)
		{
			Punteggi punteggi = this.contaPunti.getPunteggioFinale();
			this.model.notificaFinePartita(punteggi);
		}
	}

	private boolean tesseraEstrattaValida()
	{
		Tessera tesseraEstratta = this.model.getTesseraCorrente();
		Tessera tesseraCopia = tesseraEstratta.clone();
		Set<Coordinate> setVuote = this.contaPunti.getSetVuote();
		for(Coordinate coordinate : setVuote)
		{
			if(this.posizionabile(coordinate,tesseraCopia))
			{
				return true;
			}
		}
		return false;
			
	}

	private boolean posizionabile(Coordinate coordinate, Tessera tesseraCopia)
	{
		for(int i=0 ; i< PuntoCardinale.NUMERO_DIREZIONI; i++)
		{
			if(this.tuttoVicinatoDAccordo(coordinate))
			{
				return true;
			}
			tesseraCopia.ruota();
		}
		return false;
	}

	private boolean tuttoVicinatoDAccordo(Coordinate coordinate, Tessera tessera)
	{
		Tessera tesseraVicino = null;
		int viciniVuoti = 0;
		for (PuntoCardinale punto : PuntoCardinale.values())
		{
			try
			{
				tesseraVicino = this.model.getTessera(coordinate.getCoordinateA(punto));
				if (!tessera.buonVicino(tesseraVicino, punto)) { return false; }
	
			}
			catch (TesseraNonTrovataException e)
			{
				viciniVuoti++;
				continue;
			}
		}
	
		if (viciniVuoti == PuntoCardinale.NUMERO_DIREZIONI) { return false; }
		return true;
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
