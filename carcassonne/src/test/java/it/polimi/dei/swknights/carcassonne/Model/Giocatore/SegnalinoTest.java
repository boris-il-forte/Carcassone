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
	}
}
