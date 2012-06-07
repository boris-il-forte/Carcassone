package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Server.CarcassonneServer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class IniziaServer extends Inizio
{
	@Override
	public void inizia()
	{
		System.out.println("Server");
		CarcassonneServer server = new CarcassonneServer();
		Executor superStarDestroyer = Executors.newFixedThreadPool(1);
		superStarDestroyer.execute(server);
	}
}
