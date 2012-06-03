package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.ImageLoader.ImageLoader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Debug
{

	public static void print(String message)
	{
		printer.println(message);
		printer.flush();
	}

	public static void visualizzaImmagineENota(String nomeTessera, String descrizione)
	{

		
		String [] s = { descrizione };
		URL url  = ImageLoader.class.getResource("/tiles/" + nomeTessera);
		try
		{
			icon = new ImageIcon(ImageIO.read(url));
		}
		catch (IOException e)
		{
	
		}
		
		
		String risposta = (String) JOptionPane.showInputDialog(null, "", descrizione,
				JOptionPane.PLAIN_MESSAGE, icon, s, descrizione);
				

	}


	private static PrintWriter	printer	= new PrintWriter(System.out);
	private static Icon					icon;
	
	private static final String[]	TIPO_AVVIO		= {  "oooo",  "oooo" };
	

}
