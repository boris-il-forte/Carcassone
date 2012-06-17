package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import java.awt.Color;
import java.util.List;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

/**
 * Handler for game over event
 * @author dave
 *
 */
public class FinePartitaHandler extends ModuloViewHandler
{
	public FinePartitaHandler(ModuloView view)
	{
		super(view);
	}

	/**
	 * Just notify the view the end of the game
	 */
	@Override
	public void visit(FinePartitaEvent event)
	{
		this.view.visualizzaPunteggi(event.getPunteggi());
		List<Color> listaVincenti = event.getVincitore();
		StringBuilder stringaVittoria;
		if(listaVincenti.size()!= 1)
		{
			stringaVittoria = new StringBuilder();
			stringaVittoria.append(" Hanno vinto a pari merito: ");
			for(Color colore : listaVincenti)
			{
				String nomeColore = ColoriGioco.getName(colore);
				stringaVittoria.append(" " + nomeColore + " ");
			}
		}
		else
		{
			stringaVittoria = new StringBuilder(" Ha vinto il giocatore: ");
			Color colore = listaVincenti.get(0);
			String nomeColore = ColoriGioco.getName(colore);
			stringaVittoria.append(" " + nomeColore + " ");
		}
		this.view.notificaFinePartita(stringaVittoria.toString());
		this.sveglia();
	}

}
