package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.Server.Controller.ConfineTessera;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;

import java.awt.Image;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public final class Debug
{
	private Debug(){}

	public static void print(String message)
	{
		printer.println(message);
		printer.flush();
	}
	
	public static void print(Map<Costruzione, List<ConfineTessera>> mappa)
	{
		for(Entry<Costruzione, List<ConfineTessera>> entry : mappa.entrySet())
		{
			printer.print(entry.getKey());
			
		}
	}

	public static void showImg(Image imgTessera, String descrizione)
	{
		Icon icona = new ImageIcon(imgTessera);
		showImg(icona, descrizione);
	}

	public static void showImg(Icon icon, String descrizione)
	{
		JOptionPane.showMessageDialog(null, "descrizione", "Debug", JOptionPane.INFORMATION_MESSAGE, icon);
	}

	private static PrintWriter	printer	= new PrintWriter(System.out);

}
