package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import java.awt.Color;

public abstract class ConnessioneView extends AbstractConnessioneView
{

	public ConnessioneView(int numeroConnessione)
	{
		this.numeroConnessione = numeroConnessione;
	}

	public Color getColoreConnessione()
	{
		return ColoriGioco.getListaColori().get(this.numeroConnessione -1);
	}
	
	public int getNumeroConnessione()
	{
		return this.numeroConnessione;
	}
	
	@Override
	public void riceviModificheModel(ControllerEvent event)
	{
	}

	private int	numeroConnessione;

}
