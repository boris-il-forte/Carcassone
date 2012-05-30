package it.polimi.dei.swknights.carcassonne;

/**
 * This enum describes the possible phases of the turn and associate a simple
 * explanation to give to the user.
 * 
 * @author edoardopasi & dave
 * 
 */
public enum FaseTurno {
	Inizio("Place card or rotate"), Media("Tile or pass"), Attesa("wait server response..."), PreparazioneGioco(
			"loading, please wait");

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

}
