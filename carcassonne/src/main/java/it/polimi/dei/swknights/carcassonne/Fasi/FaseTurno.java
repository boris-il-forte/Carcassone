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

	@Override
	public String toString()
	{
		return this.messaggioUtente;
	}

	private String	messaggioUtente;

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

}