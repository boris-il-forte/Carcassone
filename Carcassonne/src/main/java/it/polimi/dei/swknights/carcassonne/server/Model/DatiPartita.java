package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.exceptions.finitiColoriDisponibiliException;
import it.polimi.dei.swknights.carcassonne.server.Model.Giocatore.FactoryGiocatore;
import it.polimi.dei.swknights.carcassonne.server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.FactoryTessere;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.FactoryTessereNormali;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class DatiPartita
{

	public DatiPartita()
	{
		this.factoryGiocatori = new FactoryGiocatore();
		this.inizializzaPilaTessere();
		this.inizializzaGiocatori();
	}


	public AreaDiGioco getAreaDiGioco()
	{
		return areaDiGioco;
	}

	public Giocatore getGiocatore(Color colore)
	{
		
		for(Giocatore giocatore : this.giocatori)
		{
			if(giocatore.getColore() == colore) return giocatore;
			
		}
		
		return null; //TODO: chiedere cosa fare a sangiorgio
		
	}
	public Giocatore getGiocatoreCorrente()
	{
		return this.giocatoreCorrente;
	}
	
	public void nextTurno()
	{
		this.giocatoreCorrente = this.giocatori.poll();
		this.giocatori.add(this.giocatoreCorrente);
	}

	public Tessera getTessera() throws PartitaFinitaException
	{
		int index = pilaTessere.size();
		if (index > 0)
		{
			index--;
			Tessera tessera = this.pilaTessere.get(index);
			return tessera;
		}
		else throw new PartitaFinitaException();

	}

	public void addGiocatore() throws finitiColoriDisponibiliException
	{
	    Giocatore playerToAdd ;
	    playerToAdd = this.factoryGiocatori.getGiocatore();
	    this.giocatori.add(playerToAdd);
	}
	
	private void inizializzaGiocatori()
	{
		this.giocatori = new ArrayDeque<Giocatore>();
		try
		{
			this.addGiocatore();
			this.giocatoreCorrente = this.getGiocatore(Color.RED);
		} 
		catch (finitiColoriDisponibiliException e)
		{  //non dovrebbe mai avvenire perchè un colore c'è per forza all'inizio
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}

	private void inizializzaAreaDiGioco()
	{
		this.areaDiGioco = new AreaDiGioco();
		//TODO: mettere la tessera magica al centro
	}
	
	private void inizializzaPilaTessere()
	{
		this.pilaTessere = new ArrayList<Tessera>();
		FactoryTessere factory = new FactoryTessereNormali();
		while (factory.tesseraDisponibile() == true)
		{
			this.pilaTessere.add(factory.getTessera());
		}
		Collections.shuffle(this.pilaTessere);
	}

	private AreaDiGioco areaDiGioco;

	private List<Tessera> pilaTessere;

	private Giocatore giocatoreCorrente;

	private Queue<Giocatore> giocatori;
	
	private final FactoryGiocatore factoryGiocatori;

}