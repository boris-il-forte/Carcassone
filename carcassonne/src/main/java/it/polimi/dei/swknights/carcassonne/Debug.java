package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.Server.Controller.ConfineTessera;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Image;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Debug class, contains methods used to debug with text or images
 * 
 * @author Edo
 * 
 */
public final class Debug
{
	private Debug()
	{
	}

	/**
	 * Prints a string on the standard input
	 * 
	 * @param message
	 *            the message to be printed
	 */
	public static void print(String message)
	{
		printer.println(message);
		printer.flush();
	}

	/**
	 * Prints in a formatted way a map of cardinal points and constructions
	 * 
	 * @param mappaPuCo
	 *            the map to be printed
	 */
	public static void printMappaPuCo(Map<PuntoCardinale, Costruzione> mappaPuCo)
	{
		Debug.print(" mappa puCo su :  mappaPuCo" + mappaPuCo.toString() + "\n\n");
		StringBuilder builder = new StringBuilder();
		for (Entry<PuntoCardinale, Costruzione> entry : mappaPuCo.entrySet())
		{
			builder.append(" key = " + entry.getKey().toString());
			builder.append("\nvalue = " + entry.getValue().toString());

			printer.println(builder.toString());
			printer.flush();
		}

	}

	/**
	 * Prints in a formatted way a map of constructions al his list of borders
	 * 
	 * @param mappa
	 *            the map to be printed
	 */
	public static void print(Map<Costruzione, List<ConfineTessera>> mappa)
	{
		StringBuilder builder = new StringBuilder();
		for (Entry<Costruzione, List<ConfineTessera>> entry : mappa.entrySet())
		{
			builder.append(entry.getKey().toString());
			for (ConfineTessera confine : entry.getValue())
			{
				builder.append(confine.toString());
			}
			printer.println(builder.toString());
			printer.flush();
		}
	}

	/**
	 * Shows an image and his description
	 * 
	 * @param imgTessera
	 *            the image
	 * @param descrizione
	 *            his description
	 */
	public static void showImg(Image imgTessera, String descrizione)
	{
		Icon icona = new ImageIcon(imgTessera);
		showImg(icona, descrizione);
	
	}
	/**
	 * Shows an icon and his description
	 * 
	 * @param icon
	 *            the icon
	 * @param descrizione
	 *            his description
	 */
	public static void showImg(Icon icon, String descrizione)
	{
		JOptionPane.showMessageDialog(null, "descrizione", "Debug", JOptionPane.INFORMATION_MESSAGE, icon);
	}

	private static PrintWriter	printer	= new PrintWriter(System.out);

}
