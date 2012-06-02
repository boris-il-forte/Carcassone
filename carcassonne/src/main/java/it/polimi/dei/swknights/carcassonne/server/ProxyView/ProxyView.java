package it.polimi.dei.swknights.carcassonne.server.ProxyView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;



/*
 */
public class ProxyView extends AbstractConnessioneView
{

	public ConnessioneView	realSubject;

	public List				myConnessione;
	public List				myConnessioneAView;
	
	InterpreteSocket  interpreteSocket;
	//TODO?: InterpreteRMI interpreteRMI;

	public ProxyView()
	{
		this.interpreteSocket = new InterpreteSocket(this);
	}
	
	@Override
	public void request()
	{
	}


	public void elabora(Socket socket)
	{
		//legge e chiede al parser di capirla
		try
		{
			socket.getOutputStream();
			
			Scanner in = new Scanner(socket.getInputStream());
			PrintWriter out = new PrintWriter(socket.getOutputStream());

			while (in.hasNextLine()) {
				String line = in.nextLine();
				System.out.println("Proxy view, ricevuta: " + line);
				//se line == connect inizia la partita
				interpreteSocket.capisci(line);
				
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
