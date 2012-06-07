package it.polimi.dei.swknights.carcassonne;

import java.awt.Image;
import java.io.PrintWriter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Debug
{

	public static void print(String message)
	{
		printer.println(message);
		printer.flush();
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

	private static Icon			icon;

}
