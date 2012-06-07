package it.polimi.dei.swknights.carcassonne.Server.Model.Tessere;

/**
 * This class is the generic factory of the Factory Pattern: it gives a schema for
 * the actual card factory
 * @author Edo e Dave
 *
 */

import java.util.ArrayList;
import java.util.List;

public abstract class FactoryTessere
{

	public FactoryTessere()
	{
		this.mazzo = new ArrayList<Tessera>();
	}

	public Tessera getTessera()
	{
		int lastIndex = this.mazzo.size() - 1;
		return this.mazzo.remove(lastIndex);
	}

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
