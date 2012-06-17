package it.polimi.dei.swknights.carcassonne.Fasi;


/**
 * Phase Handler, gives simple methods (getters basically) to know weather the
 * player can do some action.
 * 
 * @author dave
 * 
 */

public class GestoreFasi
{
	/**
	 * Default constructor. Initialize the phase on "game preparation"
	 */
	public GestoreFasi()
	{
		this.faseCorrente = FaseTurno.PreparazioneGioco;
	}

	/**
	 * set the phase to begin turn
	 */
	public void cominciaTurno()
	{
		this.faseCorrente = FaseTurno.Inizio;
	}

	/**
	 * Set the phase to end game
	 */
	public void finePartita()
	{
		this.faseCorrente = FaseTurno.FinePartita;
	}

	/**
	 * Set the phase to the next
	 */
	public void nextFase()
	{
		this.faseCorrente = this.faseCorrente.next();
	}

	/**
	 * Observer method
	 * @return true if the game is not ended
	 */
	public boolean partitaOk()
	{
		return !this.faseCorrente.equals(FaseTurno.FinePartita);
	}

	/**
	 * Observer method
	 * @return true if the player can place cards
	 */
	public boolean posizionaOk()
	{
		return this.faseCorrente.equals(FaseTurno.Inizio);
	}

	/**
	 * Observer method
	 * @return true if the player can rotate the card
	 */
	public boolean ruotaOk()
	{
		return this.posizionaOk();
	}

	/**
	 * Observer method
	 * @return true if the player can either pass or place a marker
	 */
	public boolean fineTurnoOk()
	{
		return this.faseCorrente.equals(FaseTurno.Media);
	}

	/**
	 * Observer method
	 * @return true if the player can write a command
	 */
	public boolean inputOk()
	{
		return this.faseCorrente.equals(FaseTurno.Inizio) || this.faseCorrente.equals(FaseTurno.Media);
	}

	/**
	 * Observer method
	 * @return true if the game has begun
	 */
	public boolean partitaCominciata()
	{
		return !this.faseCorrente.equals(FaseTurno.PreparazioneGioco);
	}

	/**
	 * getter method
	 * @return the current phase
	 */
	public FaseTurno getCurrentFase()
	{
		return this.faseCorrente;
	}

	private FaseTurno	faseCorrente;

}
