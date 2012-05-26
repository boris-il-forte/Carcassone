package it.polimi.dei.swknights.carcassonne.server.ViewProxy;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Client.View.View;
import it.polimi.dei.swknights.carcassonne.Client.View.View.fasiTurno;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;

import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

public class ViewConnessione implements it.polimi.dei.swknights.carcassonne.Events.View
{

	public List	myPartita;

	public void request()
	{

	}

	public View	view;

	public void setView(View view)
	{
		this.view = view;
	}

	public void riceviModificheModel(EventObject event)
	{

		if (event instanceof PlaceEvent)
		{
			PlaceEvent pe = (PlaceEvent) event;
			System.out.println("sono viewConness: place event" + event.toString());

			Coordinate destinazione = pe.getCoordinateDestinazione();
			this.view.posizionaTessera(destinazione);

		}
		else if (event instanceof InizioGiocoEvent)
		{
			InizioGiocoEvent ige = (InizioGiocoEvent) event;
			AdapterTessera tessera = ige.getTesseraIniziale();
			this.view.setTesseraCorrente(tessera);
			this.view.posizionaTessera(new Coordinate(0, 0));
			this.view.setFaseGioco(fasiTurno.Inzio);
		}

		if (event instanceof UpdateTurnoEvent)
		{
			UpdateTurnoEvent ute = (UpdateTurnoEvent) event;
			this.view.setColore(ute.getGiocatoreCorrente());
			this.view.setTesseraCorrente(ute.getTessera());
			this.view.setFaseGioco(fasiTurno.Inzio);

		}

	}

	public void run()
	{
		// TODO Auto-generated method stub

	}

	public void addListener(EventListener eventListener)
	{
		// TODO Auto-generated method stub

	}

	public void removeListener(EventListener eventListener)
	{
		// TODO Auto-generated method stub

	}

}
