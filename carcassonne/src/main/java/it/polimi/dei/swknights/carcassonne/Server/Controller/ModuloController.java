package it.polimi.dei.swknights.carcassonne.Server.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Fasi.GestoreFasi;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.AbstractController;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.ModuloControllerHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.PassHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.PlaceHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.RuotaHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers.TileHandler;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Segnalino;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class that implements theController of the MVC pattern Contains all the
 * hods to manage event handling and the high level game logic.
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

	/**
	 * Runs the controller main loop
	 */

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
		}
	}

	/**
	 * Method that should be called when firing an event to the Controller
	 * 
	 * @see it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller#riceviInput()
	 */
	@Override
	public void riceviInput(ViewEvent event)
	{
		for (ModuloControllerHandler visitorHandler : this.visitorHandlers)
		{
			event.accept(visitorHandler);
		}
	}

	/**
	 * Method called when the tile is sucessfully placed
	 */

	public synchronized void comunicaPosizionamentoTessera()
	{
		this.gestoreFasi.nextFase();
	}

	/**
	 * Add a marker to the construction he belogs to
	 * 
	 * @param segnalino
	 *            the marker to be added
	 * @param puntoCardinale
	 *            the cardinal points where the marked has been placed on the
	 *            tile
	 */

	public void addSegnalinoTessera(Segnalino segnalino, PuntoCardinale puntoCardinale)
	{
		this.contaPunti.addSegnalino(segnalino, puntoCardinale);
	}

	/**
	 * Method used to control whereas the current tile can be placed
	 * 
	 * @param coordinate
	 *            the coordinates where the player wuold like to place the tile
	 * @return true if the card can be placed
	 */

	public boolean tuttoVicinatoDAccordo(Coordinate coordinate)
	{
		return this.tuttoVicinatoDAccordo(coordinate, this.model.getTesseraCorrente());
	}

	/**
	 * Controls if the construction is not controlled by anyone
	 * 
	 * @param punto
	 *            the side of the current tile where the player want to place
	 *            his marker
	 * @return true if the marker can be placed
	 */

	public boolean costruzioneLibera(PuntoCardinale punto)
	{
		Map<PuntoCardinale, Costruzione> mappaCostruzioni;
		mappaCostruzioni = this.contaPunti.getMapCostruzioniUltimaTessera();
		Costruzione costruzione = mappaCostruzioni.get(punto);
		if (costruzione != null) { return costruzione.controllataDa().size() == 0; }
		return false;

	}

	/**
	 * getter method
	 * 
	 * @return the cartographer object embedded in this
	 */

	public ContatoreCartografo getContapunti()
	{
		return this.contaPunti;
	}

	/**
	 * getter method
	 * 
	 * @return the Phase Manager embedded in this
	 */

	public GestoreFasi getGestoreFasi()
	{
		return this.gestoreFasi;
	}

	private void nextTurno() throws InterruptedException
	{
		this.attendiPosizionamentoTessera();
		if (this.contaPunti.areCostruzioniCompletate())
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
		for (Costruzione costruzione : costruzioniCompletate)
		{
			for (Tessera tessera : costruzione.getTessere())
			{
				if (costruzione.daTogliere(tessera.getSegnalino()))
				{
					listaTessere.add(tessera);
				}
			}
		}
		return listaTessere;
	}

	private void primaMossaPartita()
	{
		final Coordinate origine = new Coordinate(0, 0);
		try
		{
			this.model.iniziaGioco();
			this.contaPunti.riceviCoordinateTessera(origine);
			this.gestoreFasi.cominciaTurno();
		}
		catch (PartitaFinitaException e)
		{
			Thread.currentThread().interrupt();
		}
	}

	private void primaMossaTurno()
	{
		try
		{
			do
			{
				this.model.getTesseraDaMazzo();
			} while (!this.tesseraEstrattaValida());
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
		Set<Coordinate> setVuote = this.model.getSetVuote();
		for (Coordinate coordinate : setVuote)
		{
			if (this.posizionabile(coordinate, tesseraCopia)) { return true; }
		}
		return false;

	}

	private boolean posizionabile(Coordinate coordinate, Tessera tesseraCopia)
	{
		for (int i = 0; i < PuntoCardinale.NUMERO_DIREZIONI; i++)
		{
			if (this.tuttoVicinatoDAccordo(coordinate, tesseraCopia)) { return true; }
			tesseraCopia.ruota();
		}
		return false;
	}

	private boolean tuttoVicinatoDAccordo(Coordinate coordinate, Tessera tessera)
	{
		Tessera tesseraVicino;
		if (!this.model.getSetVuote().contains(coordinate)) { return false; }
		for (PuntoCardinale punto : PuntoCardinale.values())
		{
			try
			{
				tesseraVicino = this.model.getTessera(coordinate.getCoordinateA(punto));
				if (!tessera.buonVicino(tesseraVicino, punto)) { return false; }

			}
			catch (TesseraNonTrovataException e)
			{
				continue;
			}
		}

		return true;
	}

	private List<ModuloControllerHandler> attivaHandler()
	{
		List<ModuloControllerHandler> handlerList = new ArrayList<ModuloControllerHandler>();
		handlerList.add(new RuotaHandler(this, this.model));
		handlerList.add(new PlaceHandler(this, this.model));
		handlerList.add(new TileHandler(this, this.model));
		handlerList.add(new PassHandler(this, this.model));
		return handlerList;
	}

	private synchronized void attendiPosizionamentoTessera() throws InterruptedException
	{
		while (this.gestoreFasi.inputOk())
		{
			this.wait();
		}
	}

	private List<ModuloControllerHandler>	visitorHandlers;

	private ContatoreCartografo				contaPunti;

	private final GestoreFasi				gestoreFasi;

	private final ModuloModel				model;

}
