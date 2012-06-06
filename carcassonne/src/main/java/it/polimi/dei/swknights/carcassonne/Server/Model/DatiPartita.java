package it.polimi.dei.swknights.carcassonne.Server.Model;

import it.polimi.dei.swknights.carcassonne.Exceptions.ColoreNonPresenteException;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.FinitiColoriDisponibiliException;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.FactoryGiocatore;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.FactoryTessere;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.FactoryTessereNormali;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Map.Entry;

/**
 * This class is the representation of the game data, it allows basic operations
 * involving players; handle the turns
 * 
 * @author Edo & Dave
 * 
 */

public final class DatiPartita
{

	public DatiPartita()
	{
		this.factoryGiocatori = new FactoryGiocatore();
		this.listFactoryTessere = new ArrayList<FactoryTessere>();
		this.listFactoryTessere.add(new FactoryTessereNormali());
		this.areaDiGioco = new AreaDiGioco();
		this.inizializzaPilaTessere();
		this.inizializzaGiocatori();
	}
	/**
	 * gets coordinates of a given card
	 * @param tessera
	 * @return the coordinate of that card
	 */
	public Coordinate getCoordinateTessera(Tessera tessera)
	{
		return this.areaDiGioco.getCoordinateTessera(tessera);
	}

	/**
	 * Getter for AreaDiGioco
	 * 
	 * @return the AreaDiGioco of this game
	 */

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
	 * @throws ColoreNonPresenteException 
	 */

	public Giocatore getGiocatore(Color colore) throws ColoreNonPresenteException
	{

		for (Giocatore giocatore : this.giocatori)
		{
			if (giocatore.getColore() == colore) { return giocatore; }

		}

		throw new ColoreNonPresenteException(ColoriGioco.getName(colore));

	}

	/**
	 * Get the current player
	 * 
	 * @return the player who's turn is
	 */

	public Giocatore getGiocatoreCorrente()
	{
		return this.giocatori.peek();
	}

	/**
	 * Get the Player's list
	 * 
	 * @return the current players list
	 */

	public List<Giocatore> getListaGiocatori()
	{
		List<Giocatore> giocatori = new ArrayList<Giocatore>();
		giocatori.addAll(this.giocatori);
		return giocatori;
	}

	/**
	 * changes the current turn to the next
	 */
	public void nextTurno()
	{
		Giocatore vecchio =this.giocatori.poll();
		this.giocatori.add(vecchio);
	}

	/**
	 * gets a card from the deck if there is any available
	 * 
	 * @return the card on the top of the desk
	 * @throws PartitaFinitaException
	 *             if the cards are finished, and so the game ends
	 */

	public Tessera pescaTesseraDalMazzo() throws PartitaFinitaException
	{
		int index = pilaTessere.size();
		if (index > 0)
		{
			index--;
			return this.pilaTessere.remove(index);
		}
		else
		{
			throw new PartitaFinitaException();
		}

	}
	
	/**
	 * 
	 * @return the first card, to be placed
	 */
	public Tessera pescaPrimaTessera()
	{
		return this.tesseraMagic;
	}

	public void aggiornaPunteggioGiocatori(Punteggi punteggi)
	{
		for(Entry<Color, Integer> entry : punteggi.entrySet())
		{
			try
			{
				Giocatore giocatore = this.getGiocatore(entry.getKey());
				giocatore.addPunti(entry.getValue());
			}
			catch(ColoreNonPresenteException e)
			{
				continue;
			}
		}
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
		}
		catch (FinitiColoriDisponibiliException e)
		{ // non dovrebbe mai avvenire perchè un colore c'è per forza all'inizio
			e.printStackTrace();
			System.exit(-1);
		}

	}

	private void inizializzaPilaTessere()
	{
		this.pilaTessere = new ArrayList<Tessera>();
		for(FactoryTessere factory : this.listFactoryTessere)
		{
			//TODO cambiare modo di assegnare percorso...
			factory.acquisisciMazzoDaFile("/Carcassonne.txt");
			while (factory.tesseraDisponibile())
			{
				this.pilaTessere.add(factory.getTessera());
			}
			Collections.shuffle(this.pilaTessere);
			this.setPrimaTessera(factory.getTesseraMagic());
		}

	}
	
	private void setPrimaTessera(Tessera primaTessera)
	{
		if(primaTessera!=null && this.tesseraMagic == null)
		{
			this.tesseraMagic = primaTessera;
		}
	}

	private AreaDiGioco					areaDiGioco;

	private Tessera						tesseraMagic;

	private List<Tessera>				pilaTessere;

	private Queue<Giocatore>			giocatori;

	private final List<FactoryTessere>	listFactoryTessere;

	private final FactoryGiocatore		factoryGiocatori;

}
