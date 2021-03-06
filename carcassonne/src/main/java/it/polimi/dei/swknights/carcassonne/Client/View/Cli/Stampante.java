package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.Vicinato;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to print on the screen the cards in CLI mode
 * 
 * @author edoardopasi & dave
 * 
 */
class Stampante
{

	/**
	 * default constructor
	 * 
	 * @param datiMappa
	 *            the data of the map we want to print (NW & SE coordinates)
	 */
	public Stampante(DatiMappa datiMappa)
	{
		this.datiMappa = datiMappa;
		int width = (datiMappa.getLarghezza()) * LARGHEZZA_TESSERA;
		int height = (datiMappa.getAltezza()) * ALTEZZA_TESSERA;
		this.builder = new StringBuilder2D(new Dimension(width + 1, height + 1));
	}

	/**
	 * Constructor. Used to print a single card.
	 */
	public Stampante()
	{
		this(new DatiMappa(new Coordinate(0, 0), new Coordinate(0, 0)));
	}

	/**
	 * Add a list of entries that then may be printed
	 * 
	 * @see EntryTessera
	 * @param listaTessere
	 *            the list of entry to be added
	 */
	public void addListTessera(List<EntryTessera> listaTessere)
	{
		for (EntryTessera entryTessera : listaTessere)
		{
			this.addTessera(entryTessera);
		}
	}

	/**
	 * Add a single entry that then may be printed
	 * 
	 * @see EntryTessera
	 * @param entryTessera
	 */
	public void addTessera(EntryTessera entryTessera)
	{
		Coordinate coordinate = entryTessera.getCoordinate();
		String tessera = entryTessera.getTessera().toCliString();
		Vicinato vicinato = entryTessera.getVicinato();
		this.addTessera(coordinate, tessera, vicinato);
	}

	/**
	 * Add a single entry that then may be printed, but given its 3 components
	 * 
	 * @param coordinate
	 * @param tessera
	 * @param vicinato
	 */
	public void addTessera(Coordinate coordinate, String tessera, Vicinato vicinato)
	{
		Coordinate primoPuntoTessera = this.getPrimoPuntoTessera(coordinate);
		this.scriviTessera(primoPuntoTessera, tessera, vicinato);
		this.scriviVuoti(coordinate, vicinato);
	}

	/**
	 * Returns the string representation of the map
	 */
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
			if (!vicinato.haVicinoA(punto) && this.coordinataStampabile(coordinate.getCoordinateA(punto)))
			{
				Coordinate coordinateDestinazione = coordinate.getCoordinateA(punto);
				this.scriviTesseraVuota(coordinateDestinazione);
			}
		}

	}

	private boolean coordinataStampabile(Coordinate coordinate)
	{
		boolean daStampare = coordinate.getX() <= this.datiMappa.getMaxA(PuntoCardinale.est);
		daStampare = daStampare && coordinate.getX() >= this.datiMappa.getMaxA(PuntoCardinale.ovest);
		daStampare = daStampare && coordinate.getY() <= this.datiMappa.getMaxA(PuntoCardinale.sud);
		daStampare = daStampare && coordinate.getY() >= this.datiMappa.getMaxA(PuntoCardinale.nord);
		return daStampare;
	}

	private void scriviTesseraVuota(Coordinate coordinate)
	{
		Coordinate primoPunto = this.getPrimoPuntoTessera(coordinate);
		this.scriviAngoli(primoPunto);
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
			if (this.vuotoA(puntoInternoLato) || confine == '#')
			{
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
		return (this.builder.getCharAt(coordinate) == ' ');
	}

	private void scriviElementi(Coordinate primoPuntoTessera, String tessera)
	{
		Map<PuntoCardinale, Lato> mapLati = this.getMapLati(primoPuntoTessera);
		String elementi[] = tessera.split(" ");
		for (PuntoCardinale puntoCardinale : PuntoCardinale.values())
		{
			if (!elementi[puntoCardinale.toInt()].equals("N0"))
			{
				Lato lato = mapLati.get(puntoCardinale);
				Coordinate coordinataInserimento = lato.getCoordinateLabel(puntoCardinale,
						elementi[puntoCardinale.toInt()]);
				this.builder.scriviStringa(coordinataInserimento, elementi[puntoCardinale.toInt()]);
			}
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
				int x = 1 - label.length();
				Coordinate incremento = new Coordinate(x, 0);
				coordinataInserimento = coordinataInserimento.getCoordinateA(incremento);
			}

			return coordinataInserimento;
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
