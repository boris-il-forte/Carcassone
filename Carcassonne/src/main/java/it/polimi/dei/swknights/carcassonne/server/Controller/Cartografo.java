package it.polimi.dei.swknights.carcassonne.server.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class Cartografo
{
	public Cartografo(Coordinate coordinateTessera, AreaDiGioco cartaGeografica)
	{
		this.confini = new HashMap<Costruzione, List<Confine>>();
		this.confinanti = new HashMap<Costruzione, List<Confine>>();
		this.calcolaConfiniEConfinanti(coordinateTessera, cartaGeografica);
	}

	public Map<Costruzione, List<Confine>> getConfiniCalcolati()
	{
		return this.confini;
	}

	public Map<Costruzione, List<Confine>> getConfinantiCalcolati()
	{
		return this.confinanti;
	}
	//confini = vuoti   confinanti = pieni   (ovvio no?)
	private void calcolaConfiniEConfinanti(Coordinate coordinateTessera,
			AreaDiGioco cartaGeografica)
	{
		Tessera tessera;
		try
		{
			tessera = cartaGeografica.getTessera(coordinateTessera); //se c'è è confinante se no confine
			Map<Costruzione, List<PuntoCardinale>> mapCostruzioni;
			mapCostruzioni = tessera.getCostruzioni(); //costruzioni aggregate della tessera con relative liste di punti cardinali (1 solo per nn aggreg)
			for (Costruzione costruzione : mapCostruzioni.keySet())
			{
				List<Confine> listaConfinante = new ArrayList<Confine>();
				List<Confine> listaConfini = new ArrayList<Confine>();
				for(PuntoCardinale puntoCardinale : mapCostruzioni.get(costruzione))
				{
					Coordinate coordinateConfinante = coordinateTessera.getCoordinateA(puntoCardinale);
					try
					{
						Tessera tesseraConfinante = cartaGeografica.getTessera(coordinateConfinante);
						Confine confinante = tesseraConfinante.getConfine(puntoCardinale.opposto());
						listaConfinante.add(confinante);
					} 
					catch (TesseraNonTrovataException e)
					{
	
						Confine confine = tessera.getConfine(puntoCardinale);
						listaConfini.add(confine);
					}
				}
				this.confinanti.put(costruzione, listaConfinante);
				this.confini.put(costruzione, listaConfini);
			}

		} catch (TesseraNonTrovataException e1)
		{
			e1.printStackTrace();
			System.exit(-1);
		}
	}

	private Map<Costruzione, List<Confine>> confinanti;

	private Map<Costruzione, List<Confine>> confini;

	public Set<Costruzione> getCostruzioni()
	{
		Set<Costruzione> semiSetCostruzioni1 = this.confini.keySet();
		Set<Costruzione> semiSetCostruzioni2 = this.confinanti.keySet();
		semiSetCostruzioni1.addAll(semiSetCostruzioni2);
		return semiSetCostruzioni1;
	}
}
