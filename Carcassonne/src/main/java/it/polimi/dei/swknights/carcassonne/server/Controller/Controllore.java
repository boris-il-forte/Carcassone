package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.Events.ViewListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import it.polimi.dei.swknights.carcassonne.Events.EventSource;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;

public class Controllore implements ViewListener, EventSource
{
	public Controllore()
	{
		this.listeners = new ArrayList<ViewListener>();
		this.partita = new DatiPartita();
		this.contaPunti = new ContaPunti(this.partita.getAreaDiGioco());
	}
	
	public void addListener(EventListener eventListener)
	{
		ViewListener viewListener;
		// TODO this.blocco instanceof Male! xD
		if (eventListener instanceof ViewListener) 
		{
			viewListener =(ViewListener) eventListener;
			this.listeners.add(viewListener);
		}
	}

	public void removeListener(EventListener eventListener)
	{
		listeners.remove(eventListener);
	}

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
