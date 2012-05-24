package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.Vicinato;

class Stampante
{

	public Stampante(DatiMappa datiMappa)
	{
		this.datiMappa = datiMappa;
		int width = datiMappa.getLarghezza() * LARGHEZZA_TESSERA;
		int height = datiMappa.getAltezza() * ALTEZZA_TESSERA;
		System.out.println(width + "," + height);
		this.builder = new StringBuilder2D(new Dimension(width, height));
	}

	public void addListTessera(List<EntryTessera> listaTessere)
	{
		for (EntryTessera entryTessera : listaTessere)
		{
			this.addTessera(entryTessera);
		}
	}

	public void addTessera(EntryTessera entryTessera)
	{
		Coordinate coordinate = entryTessera.getCoordinate();
		String tessera = entryTessera.getTessera().toCliString();
		Vicinato vicinato = entryTessera.getVicinato();
		this.addTessera(coordinate, tessera, vicinato);
	}

	public void addTessera(Coordinate coordinate, String tessera, Vicinato vicinato)
	{
		Coordinate primoPuntoTessera = this.getPrimoPuntoTessera(coordinate);
		this.scriviTessera(primoPuntoTessera, tessera, vicinato);
		this.scriviVuoti(coordinate, vicinato);
	}

	@Override
	public String toString()
	{
		return this.builder.toString();
	}

	private Coordinate getPrimoPuntoTessera(Coordinate coordinate)
	{
		int x = coordinate.getX() - this.datiMappa.getMaxA(PuntoCardinale.ovest);
		int y = coordinate.getY() - this.datiMappa.getMaxA(PuntoCardinale.nord);
		x *= LARGHEZZA_TESSERA;
		y *= ALTEZZA_TESSERA;
		return new Coordinate(x, y);
	}

	private void scriviTessera(Coordinate primoPuntoTessera, String tessera, Vicinato vicinato)
	{
		this.scriviAngoli(primoPuntoTessera);
		this.scriviElementi(primoPuntoTessera, tessera);
		this.scriviLati(primoPuntoTessera, vicinato);
	}

	private void scriviVuoti(Coordinate coordinate, Vicinato vicinato)
	{
		for (PuntoCardinale punto : PuntoCardinale.values())
		{
			if (!vicinato.haVicinoA(punto))
			{
				Coordinate coordinateDestinazione = coordinate.getCoordinateA(punto);
				this.scriviTesseraVuota(coordinateDestinazione);
			}
		}

	}

	private void scriviTesseraVuota(Coordinate coordinate)
	{
		Coordinate primoPunto = this.getPrimoPuntoTessera(coordinate);
		this.scriviLati(primoPunto, new Vicinato(true));
		this.scriviCordinate(coordinate);
	}

	private void scriviCordinate(Coordinate coordinate)
	{
		final int x = LARGHEZZA_TESSERA / 2;
		final int y = ALTEZZA_TESSERA / 2;
		final Coordinate centroRelativo = new Coordinate(x, y);
		String coordinateString = coordinate.toString();
		Coordinate primoPunto = this.getPrimoPuntoTessera(coordinate);
		this.builder.scriviStringaCentrata(primoPunto.getCoordinateA(centroRelativo), coordinateString);

	}

	private void scriviLati(Coordinate primoPuntoTessera, Vicinato vicinato)
	{
		Map<PuntoCardinale, Lato> mapLati = this.getMapLati(primoPuntoTessera);

		for (PuntoCardinale punto : PuntoCardinale.values())
		{
			char confine = (vicinato.haVicinoA(punto)) ? '.' : '#';
			Lato lato = mapLati.get(punto);
			Coordinate puntoInternoLato = lato.getPuntoMedioLato();
			if (this.vuotoA(puntoInternoLato))
			{
				System.out.println("Effettivamente Vuoto a: " + puntoInternoLato);
				this.builder.fillConCarattere(lato.getStart(), lato.getEnd(), confine);
			}
		}
	}

	private Map<PuntoCardinale, Lato> getMapLati(Coordinate primoPuntoTessera)
	{
		Map<PuntoCardinale, Lato> mappaLati = new HashMap<PuntoCardinale, Stampante.Lato>();
		Coordinate incrementoX = new Coordinate(LARGHEZZA_TESSERA, 0);
		Coordinate incrementoY = new Coordinate(0, ALTEZZA_TESSERA);

		Coordinate angoloNO = primoPuntoTessera;
		Coordinate angoloNE = primoPuntoTessera.getCoordinateA(incrementoX);
		Coordinate angoloSO = primoPuntoTessera.getCoordinateA(incrementoY);
		Coordinate angoloSE = primoPuntoTessera.getCoordinateA(incrementoX).getCoordinateA(incrementoY);

		mappaLati.put(PuntoCardinale.nord, new Lato(angoloNO, angoloNE));
		mappaLati.put(PuntoCardinale.sud, new Lato(angoloSO, angoloSE));
		mappaLati.put(PuntoCardinale.ovest, new Lato(angoloNO, angoloSO));
		mappaLati.put(PuntoCardinale.est, new Lato(angoloNE, angoloSE));

		return mappaLati;
	}

	private boolean vuotoA(Coordinate coordinate)
	{
		System.out.println("controllo vuoto in " + coordinate);
		return (this.builder.getCharAt(coordinate) == ' ');
	}

	private void scriviElementi(Coordinate primoPuntoTessera, String tessera)
	{
		Map<PuntoCardinale, Lato> mapLati = this.getMapLati(primoPuntoTessera);
		String elementi[] = tessera.split(" ");
		for (PuntoCardinale puntoCardinale : PuntoCardinale.values())
		{
			Lato lato = mapLati.get(puntoCardinale);
			Coordinate coordinataInserimento = lato.getCoordinateLabel(puntoCardinale,
					elementi[puntoCardinale.toInt()]);
			this.builder.scriviStringa(coordinataInserimento, elementi[puntoCardinale.toInt()]);
		}

	}

	private void scriviAngoli(Coordinate primoPuntoTessera)
	{
		Coordinate incrementoY = new Coordinate(0, ALTEZZA_TESSERA);
		Coordinate incrementoX = new Coordinate(LARGHEZZA_TESSERA, 0);
		this.builder.scriviCarattere(primoPuntoTessera, '+');
		this.builder.scriviCarattere(primoPuntoTessera.getCoordinateA(incrementoX)
				.getCoordinateA(incrementoY), '+');
		this.builder.scriviCarattere(primoPuntoTessera.getCoordinateA(incrementoX), '+');
		this.builder.scriviCarattere(primoPuntoTessera.getCoordinateA(incrementoY), '+');
	}

	private DatiMappa			datiMappa;

	private StringBuilder2D		builder;

	private static final int	ALTEZZA_TESSERA		= 7;

	private static final int	LARGHEZZA_TESSERA	= 14;

	private static class Lato
	{
		public Lato(Coordinate start, Coordinate end)
		{
			this.start = start;
			this.end = end;
			int x = (start.getX() + end.getX()) / 2;
			int y = (start.getY() + end.getY()) / 2;
			this.media = new Coordinate(x, y);
		}

		public Coordinate getCoordinateLabel(PuntoCardinale puntoCardinale, String label)
		{
			PuntoCardinale puntoOpposto = puntoCardinale.opposto();
			Coordinate coordPuntoInterno = this.getPuntoMedioLato();

			Coordinate coordinataInserimento = coordPuntoInterno.getCoordinateA(puntoOpposto);

			if (puntoCardinale == PuntoCardinale.est)
			{
				int x = -label.length();
				Coordinate incremento = new Coordinate(x, 0);
				coordinataInserimento.getCoordinateA(incremento);
			}

			return null;
		}

		public Coordinate getPuntoMedioLato()
		{
			return this.media;
		}

		public Coordinate getStart()
		{
			return this.start;
		}

		public Coordinate getEnd()
		{
			return this.end;
		}

		private Coordinate	media;

		private Coordinate	start;

		private Coordinate	end;
	}

}
