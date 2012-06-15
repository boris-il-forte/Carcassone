package it.polimi.dei.swknights.carcassonne.Model.Giocatore;

import java.awt.Color;

import org.junit.Test;
import static org.junit.Assert.*;

import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Segnalino;

public class SegnalinoTest
{

	@Test
	public void segnalino()
	{
		Segnalino s = new Segnalino(Color.yellow);
		assertTrue("nooo il giallo non è giallo!",
				s.getColore() == Color.yellow);
		Segnalino s2 = new Segnalino(Color.pink);
		//purtroppo si può creare un segnlino rosa... va beh
	}
	
	
}
