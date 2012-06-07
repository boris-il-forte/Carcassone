package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;

/**
 * A basic schema for View events, Used mainly to implement the visitor pattern
 * 
 * @author edoardopasi
 * 
 */

public abstract class ModuloViewHandler extends ViewHandler
{
	public ModuloViewHandler(ModuloView view)
	{
		this.view = view;
	}

	public void sveglia()
	{
		synchronized (this.view)
		{
			this.view.notifyAll();
		}

	}

	protected ModuloView	view;
}

/*
 * Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
 * tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
 * quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
 * consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
 * cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
 * proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
 */

