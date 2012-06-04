package it.polimi.dei.swknights.carcassonne.ModoInizio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class Inizio
{
	Executor superStarDestroyer = Executors.newCachedThreadPool();

	public abstract void inizia();
}
