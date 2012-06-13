package it.polimi.dei.swknights.carcassonne.ModoInizio;

import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class Inizio
{
	public abstract void inizia();

	protected Executor		superStarDestroyer	= Executors.newCachedThreadPool();

	protected PrintWriter	printer				= new PrintWriter(System.out);

}
