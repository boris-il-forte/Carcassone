package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

/**
 * This class is the generic factory of the Factory Pattern: it gives a schema for
 * the actual card factory
 * @author Edo e Dave
 *
 */

import java.util.List;

public abstract class FactoryTessere
{

	protected List<Tessera> mazzo;

	public Tessera getTessera()
	{
		int lastIndex = this.mazzo.size();
		return this.mazzo.remove(lastIndex);
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
	protected abstract void acquisisciMazzoDaFile(String pathFileTessere);

}