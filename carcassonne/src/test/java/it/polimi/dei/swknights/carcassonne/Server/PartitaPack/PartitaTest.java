package it.polimi.dei.swknights.carcassonne.Server.PartitaPack;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.dei.swknights.carcassonne.Server.Partita;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

public class PartitaTest
{
	Partita partita;
	
	@Test
	public void parita()
	{
		partita = new Partita();
		partita.addPlayer();
		partita.addPlayer();
		partita.cominciaPartita();
		ProxyView proxyV = partita.getProxyView();
		assertTrue("", proxyV != null);
	}

}
