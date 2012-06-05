package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.CostruzioneCitta;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.CostruzioneStrada;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class TesseraTest
{
	private static Map<Costruzione, List<PuntoCardinale>>	mappaExp;
	private static Map<Costruzione, List<PuntoCardinale>>	mappaGot;

	@BeforeClass
	public static void initializeTest() throws Exception
	{

	}

	private Lati creaLatiCittaGrande()
	{
		Lati latiCreandi;
		Elemento nord = Elemento.citta;
		Elemento sud = Elemento.citta;
		Elemento ovest = Elemento.citta;
		Elemento est = Elemento.citta;
		latiCreandi = new Lati(nord, sud, ovest, est);
		return latiCreandi;
	}

	private Link creaLinkCittaGrande() throws IllegalArgumentException
	{
		/* NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl = { true, true, true, true, true, true };
		Link l = new Link(bl);
		return l;

	}

	@Test
	public void testCittaGrande() throws Exception
	{
		TesseraNormale tessera = new TesseraNormale(creaLatiCittaGrande(), creaLinkCittaGrande());

		List<PuntoCardinale> punti = new ArrayList<PuntoCardinale>();
		for (PuntoCardinale p : PuntoCardinale.values()) // tutti connessi e
															// unica città
		{
			punti.add(p);
		}

		mappaExp = new HashMap<Costruzione, List<PuntoCardinale>>();
		mappaExp.put(new CostruzioneCitta(tessera), punti);
		mappaGot = tessera.getMapCostruzioniPunti();

		List<Costruzione> CostruzioniExp = listaDa(mappaExp.keySet());
		List<Costruzione> CostruzioniGot = listaDa(mappaGot.keySet());

		assertTrue(stesseListePuntiCardinali(CostruzioniExp, CostruzioniGot));

	}

	private boolean stesseListePuntiCardinali(List<Costruzione> CostruzioniExp,
			List<Costruzione> CostruzioniGot)
	{
		ordinaCostruzioni(CostruzioniGot);
		ordinaCostruzioni(CostruzioniExp);

		if (CostruzioniExp.size() == CostruzioniGot.size())
		{
			for (int i = 0; i < CostruzioniExp.size(); i++)
			{
				Costruzione costruzioneExp = CostruzioniExp.get(i);
				Costruzione costruzioneGot = CostruzioniGot.get(i);
				List<PuntoCardinale> puntiExp = mappaExp.get(costruzioneExp);
				List<PuntoCardinale> puntiGot = mappaGot.get(costruzioneGot);
				if (puntiExp.equals(puntiGot) == false) { return false; }
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	private void ordinaCostruzioni(List<Costruzione> costruzioniGot)
	{
		Collections.sort(costruzioniGot, new Comparator<Costruzione>()
		{

			public int compare(Costruzione c1, Costruzione c2)
			{
				if (c1.contaElementi() != c2.contaElementi())
				{
					return c1.contaElementi() - c2.contaElementi();
				}
				else
				{
					if (c1 instanceof CostruzioneCitta && c2 instanceof CostruzioneStrada) {
						return 1;
					}
					if (c1 instanceof CostruzioneStrada && c2 instanceof CostruzioneCitta) {
						return -1;
					}

				}
				return 0;
			}
		});
	}

	private List<Costruzione> listaDa(Set<Costruzione> keySet)
	{
		List<Costruzione> listaC = new ArrayList<Costruzione>();
		for (Costruzione c : keySet)
		{
			listaC.add(c);
		}
		return listaC;
	}

}
