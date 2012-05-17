package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.FinitiColoriDisponibiliException;
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

/**
 * This class is the representation of the game data, it allows basic operations
 * involving players; handle the turns
 * 
 * @author Edo & Dave
 * 
 */

public class DatiPartita
{

	public DatiPartita()
	{
		this.factoryGiocatori = new FactoryGiocatore();
		this.inizializzaPilaTessere();
		this.inizializzaGiocatori();
		this.inizializzaAreaDiGioco();
	}

	public AreaDiGioco getAreaDiGioco()
	{
		return areaDiGioco;
	}

	/**
	 * Return the player of the given color
	 * 
	 * @param colore
	 *            : the color of the player to be found
	 * @return the player of that color
	 */

	public Giocatore getGiocatore(Color colore)
	{

		for (Giocatore giocatore : this.giocatori)
		{
			if (giocatore.getColore() == colore)
				return giocatore;

		}

		throw new IllegalArgumentException(colore.toString());

	}

	public Giocatore getGiocatoreCorrente()
	{
		return this.giocatoreCorrente;
	}

	/**
	 * changes the current turn to the next
	 */
	public void nextTurno()
	{
		this.giocatoreCorrente = this.giocatori.poll();
		this.giocatori.add(this.giocatoreCorrente);
	}

	/**
	 * gets a card from the deck if there is any available
	 * 
	 * @return the card on the top of the desk
	 * @throws PartitaFinitaException
	 *             if the cards are finished, and so the game ends
	 */
	public Tessera getTessera() throws PartitaFinitaException
	{
		int index = pilaTessere.size();
		if (index > 0)
		{
			index--;
			return this.pilaTessere.get(index);
		} else
			throw new PartitaFinitaException();

	}

	/**
	 * Add a player to the current group of players
	 * 
	 * @throws FinitiColoriDisponibiliException
	 */
	public void addGiocatore() throws FinitiColoriDisponibiliException
	{
		Giocatore playerToAdd;
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
		catch (FinitiColoriDisponibiliException e)
		{ // non dovrebbe mai avvenire perchè un colore c'è per forza all'inizio
			e.printStackTrace();
			System.exit(-1);
		}

	}

	private void inizializzaAreaDiGioco()
	{
		this.areaDiGioco = new AreaDiGioco();
		// TODO: mettere la tessera magica al centro
	}

	private void inizializzaPilaTessere()
	{
		this.pilaTessere = new ArrayList<Tessera>();
		FactoryTessere factory = new FactoryTessereNormali();
		while (factory.tesseraDisponibile())
		{
			this.pilaTessere.add(factory.getTessera());
		}
		Collections.shuffle(this.pilaTessere);
	}

	private AreaDiGioco				areaDiGioco;

	private List<Tessera>			pilaTessere;

	private Giocatore				giocatoreCorrente;

	private Queue<Giocatore>		giocatori;

	private final FactoryGiocatore	factoryGiocatori;

	public List<Giocatore> getListaGiocatori()
	{
		List<Giocatore> giocatori = new ArrayList<Giocatore>();
		giocatori.addAll(this.giocatori);
		return giocatori;
	}

}