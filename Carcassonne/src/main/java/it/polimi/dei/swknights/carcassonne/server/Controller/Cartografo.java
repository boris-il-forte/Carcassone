package it.polimi.dei.swknights.carcassonne.server.Controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class Cartografo
{
	public Cartografo(Coordinate coordinateTessera, AreaDiGioco cartaGeografica)
	{
		this.confini = new ArrayList<Confine>();
		this.confinanti = new ArrayList<Confine>();
		this.calcolaConfiniEConfinanti(coordinateTessera, cartaGeografica);
	}

	private void calcolaConfiniEConfinanti(Coordinate coordinateTessera, AreaDiGioco cartaGeografica)
	{
		for (PuntoCardinale puntoCardinale : PuntoCardinale.values())
		{
			Coordinate coordinateConfinante = coordinateTessera.getCoordinateA(puntoCardinale);
			try
			{
				Tessera tesseraConfinante = cartaGeografica.getTessera(coordinateConfinante);
				Confine confinante = tesseraConfinante.getConfine(puntoCardinale.opposto());
				this.confinanti.add(confinante);
			}
			catch (TesseraNonTrovataException e)
			{
				Confine confine = coordinateTessera.getConfine(puntoCardinale);
				this.confini.add(confine);
			}
		}
	}

	private ArrayList<Confine>	confinanti;

	private ArrayList<Confine>	confini;
}
