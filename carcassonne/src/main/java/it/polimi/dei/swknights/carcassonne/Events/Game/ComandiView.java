package it.polimi.dei.swknights.carcassonne.Events.Game;

import it.polimi.dei.swknights.carcassonne.FaseTurno;

/**
 * Enum to group user command rotate: rotate the tile place: place the tile
 * pass: not to place the marker title: place the marker
 * 
 * @author edoardopasi & dave
 * 
 */
public enum ComandiView {
	rotate(FaseTurno.Inizio), place(FaseTurno.Media), pass(FaseTurno.Attesa), tile(FaseTurno.Attesa);
	
	private ComandiView(FaseTurno nextFase)
	{
		this.nextFase = nextFase;
	}
	
	public FaseTurno getNextFase()
	{
		return this.nextFase;
	}
	
	private FaseTurno	nextFase;

}
