package it.polimi.dei.swknights.carcassonne.Fasi;

/**
 * This enum describes the possible phases of the turn and associate a simple
 * explanation to give to the user.
 * 
 * @author edoardopasi & dave
 * 
 */
public enum FaseTurno {
	Inizio("Place card or rotate"), Media("Tile or pass"), Attesa("wait server response..."), PreparazioneGioco(
			"loading, please wait"), FinePartita("Partita Finita");

	private FaseTurno(String messaggio)
	{
		this.messaggioUtente = messaggio;
	}

	/**
	 * Returns the phase default message
	 */
	@Override
	public String toString()
	{
		return this.messaggioUtente;
	}

	/**
	 * change to the next phase
	 * @return
	 */
	public FaseTurno next()
	{
		switch (this)
		{
			case Inizio:
				return Media;
	
			case Attesa:
				return Inizio;
	
			case FinePartita:
				return FinePartita;
	
			default:
				return Attesa;
		}
	}

	private String	messaggioUtente;

}
