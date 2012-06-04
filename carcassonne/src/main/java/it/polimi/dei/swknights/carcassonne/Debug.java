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

		String[] s = { descrizione };
		// URL url = ImageLoader.class.getResource("/tiles/" + nomeTessera);

		icon = new ImageIcon(imgTessera);

		JOptionPane.showInputDialog(null, "", descrizione, JOptionPane.INFORMATION_MESSAGE, icon, s,
				descrizione);

	}

	private static PrintWriter		printer		= new PrintWriter(System.out);
	
	private static Icon				icon;

}
