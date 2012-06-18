package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import java.awt.Color;

/**
 * Abstract class representing a generic connection to the view
 * @author dave
 *
 */
public abstract class ConnessioneView extends AbstractConnessioneView
{
	
	/**
	 * Default constructor
	 * @param numeroConnessione the number of this connection
	 */
	public ConnessioneView(int numeroConnessione)
	{
		this.numeroConnessione = numeroConnessione;
	}

	/**
	 * Getter method
	 * @return the color of the given connection
	 */
	public Color getColoreConnessione()
	{
		return ColoriGioco.getListaColori().get(this.numeroConnessione -1);
	}
	
	/**
	 * Getter method
	 * @return the number of this connection
	 */
	public int getNumeroConnessione()
	{
		return this.numeroConnessione;
	}
	
	/**
	 * Method used to get events from the model. useless, only used to mantain the same interface
	 */
	@Override
	public void riceviModificheModel(ControllerEvent event)
	{
	}

	private int	numeroConnessione;

}
