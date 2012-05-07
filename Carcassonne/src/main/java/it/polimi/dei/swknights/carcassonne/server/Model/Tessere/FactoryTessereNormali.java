package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FactoryTessereNormali extends FactoryTessere {

	public FactoryTessereNormali()
	{
		this.descrizioniTessere = new ArrayList<String>();
		this.mazzo = new ArrayList<Tessera>();
	}
	
	@Override
	public  void acquisisciMazzoDaFile(String pathFileTessere) 
	{
		  
		  this.estraiDescrizioniTessere(pathFileTessere);  
		  //scrive in this.descrizioneTessere 
		  
		  this.creaMazzoTessere();
		
		  //DEBUG System.out.println(this.mazzo);
	}
	
	private void creaMazzoTessere()
	{
		for(String descrizione : this.descrizioniTessere)
		{
			Tessera tessera = this.tesseraDaDescrzione(descrizione);
			this.mazzo.add(tessera);
			
	     }
		
	}
	
	

	
	private Tessera tesseraDaDescrzione(String descrizione)
	{
		//TODO: controllo sia buona descrizione come??
	   
		Tessera tessera;
		Elemento nord;
		Elemento sud;
		Elemento ovest;
		Elemento est;
		
		Link link= new Link();
		Lati lati;
		
		String [] partiDescrizione;
	    partiDescrizione = descrizione.split(" ");

	    //TODO: magic numbers, private final NORD=0
	     nord = this.charToElemento( partiDescrizione[0].charAt(2) );
	     sud = this.charToElemento( partiDescrizione[1].charAt(2) );
	     ovest = this.charToElemento( partiDescrizione[2].charAt(2) );
	     est = this.charToElemento( partiDescrizione[3].charAt(2) );
	     
	     lati =  new Lati(nord, sud, ovest, est);
	     
	     link.SN = charToBoolLink( partiDescrizione[4].charAt(3) );
	     link.NE = charToBoolLink( partiDescrizione[5].charAt(3) );
	     link.NO = charToBoolLink( partiDescrizione[6].charAt(3) );
	     link.OE = charToBoolLink( partiDescrizione[7].charAt(3) );
   	     link.SE = charToBoolLink( partiDescrizione[8].charAt(3) );
	     link.SO = charToBoolLink( partiDescrizione[9].charAt(3) );


	     
	     
	     tessera = new TesseraNormale(lati, link);
	     
		return tessera;
	}
	
	private Boolean charToBoolLink(char siglaLink) {
		if(siglaLink=='1')
			return true;
		if(siglaLink=='0')
			return false;
		
		//TODO:wrong type exception!!
		return false;
	}

	private  Elemento charToElemento(char siglaElemento)
	{
		if (siglaElemento=='S')
			return new Strada();
		if (siglaElemento=='C')
			return new Citta();
		if (siglaElemento=='N')
			return new Prato();
		
		//TODO: exception!!!
		return null;
		
			
	}

	
	
	////scrive in this.descrizioneTessere il contenuto di tessere.dat, 
	//  una riga -> una stringa dell'ArrayList
	private void estraiDescrizioniTessere(String pathFileTessere)
	{
		
		 String percorsoCompletoFile = PercorsoFullDiRisorsa(pathFileTessere);
         
	        try
	        {
				FileReader fr = new FileReader (new File(percorsoCompletoFile));

				BufferedReader br = new BufferedReader (fr); 
				String line = "";

				line = br.readLine();

				while (line != null) 
				{ 
					//System.out.println(line); 
					this.descrizioniTessere.add(line);
					line = br.readLine();
				}
				
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        catch(IOException e)
	        {
	        	e.printStackTrace();
	        }
		
		
		
	}
	
	
	private String PercorsoFullDiRisorsa(String path)
    {
        java.net.URL imgURL = this.getClass().getResource(path);
            return imgURL.getFile();
    }
	
	
	
	private List<Tessera> mazzo=null;
	private List<String> descrizioniTessere;
}