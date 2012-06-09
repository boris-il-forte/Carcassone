package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Model;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class AbstractConnessioneController implements Controller, Model
{

	public AbstractConnessioneController()
	{
		this.listeners = new ArrayList<View>();
	}
	
	public void request()
	{

	}

	public void run()
	{
		// TODO Auto-generated method stub

	}

	public void riceviInput(ViewEvent event)
	{
		// TODO Auto-generated method stub

	}

	public void addListener(EventListener eventListener)
	{
		if ( eventListener instanceof View)
		{
			this.listeners.add((View) eventListener);
		}

	}

	public void removeListener(EventListener eventListener)
	{
		
		this.listeners.remove(eventListener);
	}
	
	
	public void fire(ControllerEvent event)
	{
		
		for(View listener: this.listeners )
		{
			listener.riceviModificheModel(event);
		}
		
	}
	
	
	protected List<View>  listeners;

}
