package it.polimi.dei.swknights.carcassonne.modoInizio;

import it.polimi.dei.swknights.carcassonne.Events.View;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class Inizio
{
	Executor superStarDestroyer = Executors.newCachedThreadPool();
	View view;
	public abstract void inizia();
}
