package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.Client.View.Gui.Gui;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.ImageLoader.IconGetter;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.Icon;

class Carcassonne
{
	public static void main(String[] args)
	{
		Executor superStarDestroyer = Executors.newCachedThreadPool();
		String risposta = "";
		View view;
		IconGetter iconGetter = new IconGetter();
		Icon icon = iconGetter.getOriginalIcon("");
		JCarcassonneBegin begin = new JCarcassonneBegin(icon);
		
		
		do
		{
			risposta = begin.ShowDialog();
		} while (!risposta.equalsIgnoreCase("CLI") && !risposta.equalsIgnoreCase("GUI"));

		if (risposta.equalsIgnoreCase("CLI"))
		{
			view = new Cli();
		}
		else
		{
			view = new Gui();
		}

		ModuloModel model = new ModuloModel();
		Controller controller = new ModuloController(model);

		view.addListener(controller);
		model.addListener(view);
		superStarDestroyer.execute(view);
		superStarDestroyer.execute(controller);
	}
}
