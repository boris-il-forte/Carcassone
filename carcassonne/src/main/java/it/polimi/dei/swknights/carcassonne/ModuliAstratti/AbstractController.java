package it.polimi.dei.swknights.carcassonne.ModuliAstratti;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

public abstract class AbstractController implements Controller
{
	public abstract void riceviInput(ViewEvent event);

}
