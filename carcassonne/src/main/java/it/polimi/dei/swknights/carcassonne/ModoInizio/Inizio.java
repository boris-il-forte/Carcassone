package it.polimi.dei.swknights.carcassonne.ModoInizio;

import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Starter abstract class
 * @author dave
 *
 */
public abstract class Inizio
{
	/**
	 * Method used to start something
	 */
	public abstract void inizia();
	
	protected void execute(Runnable aWing)
	{
		this.superStarDestroyer.execute(aWing);
	}

	protected void print(String string)
	{
		this.printer.println(string);
		this.printer.flush();
	}

	protected void flush()
	{
		this.printer.flush();
	}

	private Executor	superStarDestroyer	= Executors.newCachedThreadPool();

	private PrintWriter	printer				= new PrintWriter(System.out);

}
