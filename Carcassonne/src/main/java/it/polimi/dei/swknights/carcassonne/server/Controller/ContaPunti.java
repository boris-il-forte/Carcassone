package it.polimi.dei.swknights.carcassonne.server.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class ContaPunti
{
	public ContaPunti(AreaDiGioco areaDiGioco)
	{
		this.areaDiGioco = areaDiGioco;
		this.Listacitta = new ArrayList<Citta>();
		this.mappaConfiniCostruzione = new HashMap<Confine, Costruzione>();
	}

	public void riceviTessera(Tessera tessera)
	{
		List<Confine> confini= this.calcolaConfini(tessera);
		List<Costruzione> costruzioni=this.calcolaCostruzioni(tessera);
		for(Confine confine : confini)
		{
			Costruzione costruzioneConfinante = mappaConfiniCostruzione.get(confine);
			//TODO da finire e pensare bene
		}
	}

	private List<Confine> calcolaConfini(Tessera tessera)
	{
		List<Confine> confini = new ArrayList<Confine>();
		// TODO da finire e ripensare
		return confini;
	}

	private List<Costruzione> calcolaCostruzioni(Tessera tessera)
	{
		List<Costruzione> costruzioni = new ArrayList<Costruzione>();
		// TODO da finire e ripensare
		return costruzioni;
	}

	private AreaDiGioco					areaDiGioco;

	private Map<Confine, Costruzione>	mappaConfiniCostruzione;

	private List<Citta>					Listacitta;

}
