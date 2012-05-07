package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

/**
 * This class is the generic factory of the Factory Pattern: it gives a schema for
 * the actual card factory
 * @author Edo e Dave
 *
 */

import java.io.FileInputStream;
import java.util.List;

public abstract class FactoryTessere {

	private FileInputStream fileSoegente;

	private List<Tessera> listaTessere;

	public Tessera getTessera() {
		return null;
	}

	/**
	 * Establish whether is possible or not, get a new card from the deck
	 * When no more cards are available, the game ends
	 * @return
	 */
	public boolean tesseraDisponibile() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Create all the needed cards, reads from the file containing their description
	 * and place each card in the deck
	 * @param pathFileTessere: path of the cards file
	 */
	protected abstract void acquisisciMazzoDaFile(String pathFileTessere);

}