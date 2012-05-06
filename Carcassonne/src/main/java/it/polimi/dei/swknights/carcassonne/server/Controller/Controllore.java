package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Events.ViewListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import it.polimi.dei.swknights.carcassonne.Events.EventSource;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;

/**
 * Class that implements theController of the MVC pattern Contains all the
 * methods to manage event handling and the high level game logic.
 * 
 * @author dave
 * 
 */

public class Controllore implements ViewListener, EventSource
{
	/**
	 * Default Constructor. Initialize data structures
	 * 
	 */
	public Controllore()
	{
		this.listeners = new ArrayList<ViewListener>();
		this.partita = new DatiPartita();
		this.contaPunti = new ContaPunti(this.partita.getAreaDiGioco());
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
		ViewListener viewListener;
		// TODO this.blocco instanceof Male! xD
		if (eventListener instanceof ViewListener)
		{
			viewListener = (ViewListener) eventListener;
			this.listeners.add(viewListener);
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
	 * @see it.polimi.dei.swknights.carcassonne.Events.ViewListener#riceviInput()
	 */

	public void riceviInput()
	{
		// TODO Auto-generated method stub

	}

	private void estraiTessera() throws PartitaFinitaException
	{
		tesseraCorrente = this.partita.getTessera();
	}

	private List<ViewListener>	listeners;

	private Tessera				tesseraCorrente;

	private ContaPunti			contaPunti;

	private DatiPartita			partita;

}
