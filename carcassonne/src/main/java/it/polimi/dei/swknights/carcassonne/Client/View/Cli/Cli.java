package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * Command Line Interface
 * 
 * @author edoardopasi & dave
 * 
 */
public class Cli extends ModuloView
{

	/**
	 * Default constructor. initializes the command line interface
	 */
	public Cli()
	{
		super();
		this.out = new PrintWriter(System.out);
		this.in = new Scanner(System.in);
		this.setCoordinataNordOvest(new Coordinate(-LARGHEZZA / 2, -ALTEZZA / 2));
		this.parser = new ParserComandi(this);
		this.informaUser = new AvvisiUser(this.out);
		this.setCoordinateRelativeSE(new Coordinate(LARGHEZZA, ALTEZZA));

	}

	/**
	 * Run method. Runs the CLI.
	 */
	@Override
	public void run()
	{

		try
		{
			this.out.println("Aspetto che la partita abbia inizio...");
			this.out.flush();
			this.aspettaInizio(); // aspetta che la partita cominci...
			do
			{
				this.attendiInput(); // attende e gestisce l'input
				this.attendiRispostaController(); // attente il ritorno della
													// fase inizio
			} while (this.getGestoreFasi().partitaOk());
		}

		catch (InterruptedException e)
		{
		}

	}

	/**
	 * Method used to refresh the map
	 */
	@Override
	public void aggiornaMappa()
	{
		ScenarioDiGioco scenario = this.getScenario();
		Stampante stampante = this.inizializzaStampante();
		Coordinate base = this.getCoordinateNordOvest();
		List<EntryTessera> listaTessere = scenario.getEntryList(base,
				base.getCoordinateA(this.getCoordinateRelativeSE()));
		stampante.addListTessera(listaTessere);
		String mappa = stampante.toString();
		this.out.print(mappa);
		this.out.flush();

	}

	/**
	 * Method used to show the current score
	 */
	@Override
	public synchronized void visualizzaPunteggi(Punteggi punteggio)
	{
		this.informaUser.notificaPunteggi(punteggio);
	}

	/**
	 * Method to set the number of the players
	 */
	@Override
	public void setNumeroPlayer(int numGiocatori)
	{
		super.setNumeroPlayer(numGiocatori);
		this.out.println("Giocano " + this.getNumeroPlayer() + " giocatori");
	}

	/**
	 * Wait for a user input eg can wait for the "rotate" command
	 */

	public void attendiInput()
	{
		final int tempoAttesaInput = 300;
		try
		{
			Thread.sleep(tempoAttesaInput);
			if (this.getGestoreFasi().inputOk() && this.turnoCorretto())
			{

				this.informaUser.setPhase(this.getGestoreFasi().getCurrentFase());
				this.getInput();
			}
		}
		catch (InterruptedException e)
		{
		}
		
	}

	/**
	 * method used to set up the first card
	 */
	@Override
	public void mettiPrimaTessera(AdapterTessera tessIniziale)
	{
		this.informaUser.setPhase(this.getGestoreFasi().getCurrentFase());
		super.mettiPrimaTessera(tessIniziale);
	}

	/**
	 * method that notify the end of the game
	 */
	@Override
	public void notificaFinePartita(String vincitore)
	{
		this.getGestoreFasi().finePartita();
		this.informaUser.notificaFinePartita();
	}

	/**
	 * method used when an invalid move is made.
	 */
	@Override
	public void notificaMossaNonValida()
	{

		this.informaUser.notificaMossaNonValida();

	}

	/**
	 * method thath shows current player's color
	 */
	@Override
	public void visualizzaColoreCorrente()
	{
		this.informaUser.setColore(this.getColoreGiocatoreCorrente());

	}

	/**
	 * Method thath shows the current tile to the player
	 */
	@Override
	public void visualizzaTesseraCorrente(AdapterTessera tessera)
	{

		this.setTesseraCorrente(tessera);
		this.informaUser.setTesseraCorrente(tessera);
		this.informaUser.mostraTesseraCorrente();

	}

	/**
	 * Executed in response of x,y place the card only if the command is given
	 * in a appropriate game phase
	 * 
	 * @param coordinate
	 * @return true if the card is placed
	 */

	boolean provaPosizionareTessera(Coordinate coordinate)
	{
		if (this.getGestoreFasi().posizionaOk())
		{
			this.fire(new PlaceEvent(this, coordinate));
			return true;
		}
		else
		{
			return false;
		}

	}

	boolean provaRuotareTessera()
	{
		if (this.getGestoreFasi().ruotaOk())
		{
			this.fire(new RotateEvent(this));
			return true;
		}
		else
		{
			return false;
		}
	}

	boolean provaNonMettereSegnalino()
	{
		if (this.getGestoreFasi().fineTurnoOk())
		{
			this.fire(new PassEvent(this));
			return true;
		}
		else
		{
			return false;
		}
	}

	boolean getTurnoGiusto()
	{
		return this.turnoCorretto();
	}

	boolean provaPosizionareSengalino(String stringComando)
	{
		if (this.getGestoreFasi().fineTurnoOk())
		{
			String elementi[] = this.getTesseraCorrente().toCliString().split(" ");
			for (PuntoCardinale punto : PuntoCardinale.values())
			{
				if (elementi[punto.toInt()].equalsIgnoreCase(stringComando))
				{

					this.fire(new TileEvent(this, this.getColoreGiocatoreCorrente(), punto));
					return true;
				}
			}
		}
		return false;
	}

	private synchronized void aspettaInizio() throws InterruptedException
	{
		while (!this.getGestoreFasi().partitaCominciata())
		{
			this.wait();
		}
	}

	private synchronized void attendiRispostaController() throws InterruptedException
	{
		while (!this.getGestoreFasi().inputOk())
		{
			this.wait();
		}

	}

	private void getInput()
	{

		boolean valido;
		do
		{
			// in flush ??
			this.informaUser.chiediComando();

			String comando = this.in.nextLine();
			valido = this.parser.eseguiComando(comando);
		} while (!valido);

	}

	private Stampante inizializzaStampante()
	{
		Coordinate min = this.getCoordinateNordOvest();
		Coordinate max = this.getCoordinateNordOvest().getCoordinateA(this.getCoordinateRelativeSE());
		DatiMappa datiMappa = new DatiMappa(min, max);
		return new Stampante(datiMappa);
	}

	private Scanner				in;

	private PrintWriter			out;

	private ParserComandi		parser;

	private final AvvisiUser	informaUser;

	private static final int	ALTEZZA		= 5;

	private static final int	LARGHEZZA	= 10;

}
