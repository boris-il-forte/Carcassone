package it.polimi.dei.swknights.carcassonne.GestoreFasi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Fasi.FaseTurno;
import it.polimi.dei.swknights.carcassonne.Fasi.GestoreFasi;

public class GestoreFasiTest
{
	GestoreFasi gestore;
	
	@Before
	public void init()
	{
		gestore = new GestoreFasi();
	}
	
	@Test
	public void cominciaTurno()
	{
		gestore.cominciaTurno();
		assertTrue("non è all'inizio ", gestore.getCurrentFase()== FaseTurno.Inizio);
	}
	
	@Test
	public void finePartita()
	{
		gestore.finePartita();
		assertTrue("non è all'inizio ", gestore.getCurrentFase()== FaseTurno.FinePartita);
	}
	
	
	@Test
	public void fineTurno()
	{
		this.gestore.cominciaTurno();
		this.gestore.nextFase();
		Debug.print("" + this.gestore.getCurrentFase().toString());
		assertTrue(gestore.getCurrentFase().equals(FaseTurno.Media));
		assertTrue(gestore.fineTurnoOk());
		
	}
	
	@Test
	public void inputOkRuotaOKPassOK()
	{
		this.gestore.cominciaTurno();
		assertTrue(" input ok " ,  this.gestore.inputOk() && this.gestore.ruotaOk() 
				&& this.gestore.posizionaOk());
		
	}
	
	@Test
	public void partitaCominciata() throws Exception
	{
		this.gestore.cominciaTurno();
		assertTrue(" non è iniziata!", 	this.gestore.partitaCominciata() && this.gestore.partitaOk());
		this.gestore.finePartita();
		assertTrue(" non è finita !", this.gestore.partitaOk() == false);
	}
	

}
