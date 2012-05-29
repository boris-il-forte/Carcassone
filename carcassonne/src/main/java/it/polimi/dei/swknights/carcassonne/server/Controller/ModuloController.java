package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.ControllerHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.PassHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.PlaceHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.RuotaHandler;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.TileHandler;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

import java.util.ArrayList;
import java.util.List;

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
	public ModuloController(ModuloModel model, ModuloView view)
	{
		this.model = model;
		this.view = view;
		this.contaPunti = new ContatoreCartografo(this.model);
		this.visitorHandlers = this.attivaHandler();

	}

	public void run()
	{
		this.iniziaGioco();
		try
		{
			this.attendiPosizionamentoTessera();
			while (true)
			{
				this.primaMossaTurno();
				this.attendiPosizionamentoTessera();
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

	public void iniziaGioco()
	{
		System.out.println("INIZIA GIOCO");
		this.primaMossaPartita();
	}

	/*
	 * public Tessera getTesseraCorrente() { return this.tesseraCorrente; }
	 */

	public ContatoreCartografo getContapunti()
	{
		return this.contaPunti;
	}

	public boolean ruotaOk()
	{
		// TODO controllare se puoi ruotare
		return true;
	}

	private void primaMossaPartita()
	{
		try
		{
			this.model.iniziaGioco(NUMBER_OF_PLAYER);
		}
		catch (PartitaFinitaException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}

	private void primaMossaTurno()
	{
		System.out.println("INIZIO TURNO");
		try
		{
			this.model.cominciaTurno();
		}
		catch (PartitaFinitaException e)
		{
			// TODO: incompleto... mancano i punteggi ulteriori accumulati!
			this.model.notificaFinePartita();
		}
	}

	private List<ControllerHandler> attivaHandler()
	{
		List<ControllerHandler> handlerList = new ArrayList<ControllerHandler>();
		System.out.println(" this . view = " + this.view.toString());
		handlerList.add(new RuotaHandler(this, this.model, this.view));
		handlerList.add(new PlaceHandler(this, this.model, this.view));
		handlerList.add(new TileHandler(this, this.model, this.view));
		handlerList.add(new PassHandler(this, this.model, this.view));
		return handlerList;
	}

	synchronized private void attendiPosizionamentoTessera() throws InterruptedException
	{
		while (!this.tesseraPosizionata)
		{
			System.out.println("WAIT");
			wait();
		}
	}

	private boolean					tesseraPosizionata;

	private List<ControllerHandler>	visitorHandlers;

	private ContatoreCartografo		contaPunti;

	private final ModuloModel		model;
	private final ModuloView		view;

	private static final int		NUMBER_OF_PLAYER	= 2;	// TODO: ask
																// user.

}
