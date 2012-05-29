package it.polimi.dei.swknights.carcassonne.Client.View;
/**
 * This enum describes the possible phases of the turn and associate a simple explanation
 *  to give to the user.
 * @author edoardopasi & dave
 *
 */
public enum FasiTurno {
	Inizio("Place card or rotate"), Media("Tile or pass"), Attesa("wait server response..."), PreparazioneGioco("loading, please wait");

	private FasiTurno(String messaggio)
	{
		this.messaggioUtente = messaggio;
	}

	@Override
	public String toString()
	{
		return this.messaggioUtente;
	}

	/**
	 * return the next turn phase
	 * @return
	 */
	// TODO: controllare
	public FasiTurno nextPhase()
	{
		switch (this)
		{
			case PreparazioneGioco:
				return Inizio;
			case Inizio:
				return Media;
			case Media:
				return Attesa;
			case Attesa:
				return Inizio;

			default:
				return Attesa;
		}
	}

	private String	messaggioUtente;

}