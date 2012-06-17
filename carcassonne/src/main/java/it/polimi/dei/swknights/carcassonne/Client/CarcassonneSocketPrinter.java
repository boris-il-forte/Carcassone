package it.polimi.dei.swknights.carcassonne.Client;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Simple class used to get a more high level way to print on sockets
 * 
 * @author edo
 * 
 */
public class CarcassonneSocketPrinter
{
	/**
	 * Default constructor
	 * 
	 * @param out
	 *            the socket output stream
	 */
	public CarcassonneSocketPrinter(OutputStream out)
	{
		printer = new PrintWriter(out);
	}

	/**
	 * Method to print on the stream
	 * 
	 * @param s
	 *            the string used to print on the socket
	 */
	public void println(String s)
	{
		this.printer.println(s);
		this.printer.flush();
	}

	private PrintWriter	printer;
}
