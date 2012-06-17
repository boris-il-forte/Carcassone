package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Starter for the CLI offline
 * @author dave
 *
 */
public class IniziaCliOffline extends Inizio
{

	/**
	 * start the CLI offline
	 */
	@Override
	public void inizia()
	{
		ModuloModel model = new ModuloModel();
		Controller controller = new ModuloController(model);

		this.print("cli - modalità offline");
		View view = new Cli();

		view.addListener(controller);
		model.addListener(view);
		int numeroGiocatori = this.setGiocatori();
		for (int i = 0; i < numeroGiocatori ; i++)
		{
			model.addPlayer();
		}
		this.execute(view);
		this.execute(controller);
	}

	private int setGiocatori()
	{
		int numeroGiocatori = 0;
		do
		{
			Scanner scanner = new Scanner(System.in);
			this.print("Inserisci numero giocatori (max 5, min 2)");
			try
			{
				numeroGiocatori = scanner.nextInt();
			}
			catch (InputMismatchException e)
			{
				this.print("Input non valido");
			}
		} while (numeroGiocatori < MIN_PLAYER || numeroGiocatori > MAX_PLAYER);
		return numeroGiocatori;
	}

	private static final int	MIN_PLAYER	= 2;
	private static final int	MAX_PLAYER	= 5;
	


}
