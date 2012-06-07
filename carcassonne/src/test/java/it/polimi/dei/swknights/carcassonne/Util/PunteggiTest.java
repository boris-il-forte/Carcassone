package it.polimi.dei.swknights.carcassonne.Util;

import static org.junit.Assert.fail;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class PunteggiTest
{

	private Punteggi	punti;

	@Before
	public void setUp()
	{
		this.punti = new Punteggi();
	}

	@Test
	public void addPunteggi()
	{

		this.punti.addPunteggi(Color.BLACK, 10);

		try
		{
			this.punti.addPunteggi(Color.BLACK, -10);
		}
		catch (IllegalArgumentException e)
		{
			// do nothing
		}
		int pBlue = this.punti.get(Color.BLACK);

		if (pBlue != 10)
		{
			fail(" il blue dovrebbe avere 10 punti!");
		}

		this.punti.addPunteggi(Color.RED, 0);

		int pRed = this.punti.get(Color.RED);

		if (pRed != 0)
		{
			fail("il red dovrebbe avere 0 punti !");
		}

	}

	@Test
	public void addPunteggioOggetto()
	{
		Punteggi puntiParziali = new Punteggi();
		puntiParziali.addPunteggi(Color.BLACK, 10);

		this.punti.addPunteggi(Color.RED, 18);
		this.punti.addPunteggi(puntiParziali);

		if (this.punti.get(Color.BLACK) != 10)
		{
			fail("il black dovrebbe avere 10 punti");
		}
		if (this.punti.get(Color.RED) != 18)
		{
			fail("il rosso dovrebbe avere 18 punti");
		}

	}

}
