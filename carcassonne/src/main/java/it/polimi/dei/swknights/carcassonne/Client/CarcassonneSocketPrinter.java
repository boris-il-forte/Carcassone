package it.polimi.dei.swknights.carcassonne.Client;

import java.io.OutputStream;
import java.io.PrintWriter;

public class CarcassonneSocketPrinter
{
	PrintWriter printer ;
	
	public CarcassonneSocketPrinter(OutputStream out)
	{
		printer = new PrintWriter(out);
	}
	
	public void println(String s)
	{
		this.printer.println(s);
		this.printer.flush();
	}
	

}
