package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Gui.Gui;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;

import java.io.IOException;
import java.net.Socket;

public class IniziaGuiOnLine extends Inizio
{
	@Override
	public void inizia()
	{
		try
		{
			this.printer.println("gui on line");
			View view = new Gui(); // 1)
			Socket socket = CarcassonneSocket.dammiSocket(); // 2)
			ProxyController controller = new ProxyController(socket); // 2
			view.addListener(controller); // 2)
			this.superStarDestroyer.execute(view); // 4)
			this.superStarDestroyer.execute(controller);
		}
		catch (IOException e)
		{

		}
	}
}
