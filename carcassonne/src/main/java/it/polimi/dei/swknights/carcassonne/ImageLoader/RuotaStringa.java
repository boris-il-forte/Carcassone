package it.polimi.dei.swknights.carcassonne.ImageLoader;

import it.polimi.dei.swknights.carcassonne.Util.Bussola;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

/**
 * A class to make rotation of the strings representing tiles resources
 * 
 * @author dave
 * 
 */
public class RuotaStringa
{

	/**
	 * Default constructor
	 * 
	 * @param daRuotare
	 *            the string to be rotated
	 */
	public RuotaStringa(String daRuotare)
	{
		this.daRuotare = daRuotare;
	}

	/**
	 * Method that performs a single clockwise rotation of the string
	 */
	public void ruotaStringa()
	{
		this.daRuotare = this.ruota(this.daRuotare);
	}

	/**
	 * returns the current string saved in this object
	 */
	@Override
	public String toString()
	{
		return this.daRuotare;
	}

	private String ruota(String daRuotare)
	{
		StringBuilder builder = new StringBuilder();
		char costruzioni[] = this.getCostruzioniPunticardinali(daRuotare);
		char collegamenti[] = this.getCollegamenti(daRuotare);
		this.ruotaCostruzioni(builder, costruzioni);
		this.ruotaCollegamenti(builder, collegamenti);
		return builder.toString();
	}

	private void ruotaCostruzioni(StringBuilder builder, char[] costruzioni)
	{
		builder.append(costruzioni[PuntoCardinale.ovest.toInt()]);
		builder.append(costruzioni[PuntoCardinale.est.toInt()]);
		builder.append(costruzioni[PuntoCardinale.sud.toInt()]);
		builder.append(costruzioni[PuntoCardinale.nord.toInt()]);
	}

	private void ruotaCollegamenti(StringBuilder builder, char[] collegamenti)
	{
		builder.append(collegamenti[Bussola.WE.toInt()]);
		builder.append(collegamenti[Bussola.NW.toInt()]);
		builder.append(collegamenti[Bussola.SW.toInt()]);
		builder.append(collegamenti[Bussola.NS.toInt()]);
		builder.append(collegamenti[Bussola.NE.toInt()]);
		builder.append(collegamenti[Bussola.SE.toInt()]);
	}

	private char[] getCostruzioniPunticardinali(String daRuotare)
	{
		char costruzioni[] = new char[PuntoCardinale.NUMERO_DIREZIONI];
		for (PuntoCardinale indice : PuntoCardinale.values())
		{
			costruzioni[indice.toInt()] = daRuotare.charAt(indice.toInt());
		}
		return costruzioni;
	}

	private char[] getCollegamenti(String daRuotare)
	{
		final int overHead = PuntoCardinale.NUMERO_DIREZIONI;
		char collegamenti[] = new char[Bussola.NUMERO_DIREZIONI];
		for (Bussola indice : Bussola.values())
		{
			collegamenti[indice.toInt()] = daRuotare.charAt(indice.toInt() + overHead);
		}
		return collegamenti;
	}

	private String	daRuotare;

}
