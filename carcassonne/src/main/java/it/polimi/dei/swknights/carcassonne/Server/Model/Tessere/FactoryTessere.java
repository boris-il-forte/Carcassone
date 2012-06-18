package it.polimi.dei.swknights.carcassonne.Server.Model.Tessere;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the generic factory of the Factory Pattern: it gives a schema for
 * the actual card factory
 * @author Edo e Dave
 *
 */
public abstract class FactoryTessere
{

	/**
	 * Default constructor
	 */
	public FactoryTessere()
	{
		this.mazzo = new ArrayList<Tessera>();
	}

	/**
	 * Gettr method
	 * @return a card of the deck
	 */
	public Tessera getTessera()
	{
		int lastIndex = this.mazzo.size() - 1;
		return this.mazzo.remove(lastIndex);
	}

	/**
	 * Getter method
	 * @return the first game card
	 */
	public Tessera getTesseraMagic()
	{
		return this.tesseraMagic;
	}

	/**
	 * Establish whether is possible or not, get a new card from the deck When
	 * no more cards are available, the game ends
	 * 
	 * @return
	 */
	public boolean tesseraDisponibile()
	{
		return !(this.mazzo.isEmpty());
	}

	/**
	 * Create all the needed cards, reads from the file containing their
	 * description and place each card in the deck
	 * 
	 * @param pathFileTessere
	 *            : path of the cards file
	 */
	public abstract void acquisisciMazzoDaFile(String pathFileTessere);

	protected void setTesseraMagick(Tessera tesseraMagic)
	{
		this.tesseraMagic = tesseraMagic;
	}

	protected void aggiungiAlMazzo(Tessera tessera)
	{
		this.mazzo.add(tessera);
	}

	private Tessera			tesseraMagic;

	private List<Tessera>	mazzo;

}
