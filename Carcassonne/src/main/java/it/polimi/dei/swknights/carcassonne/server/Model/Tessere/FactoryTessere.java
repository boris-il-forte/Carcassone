package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import java.io.FileInputStream;
import java.util.List;

public abstract class FactoryTessere {

  private FileInputStream fileSoegente;

  private List<Tessera> listaTessere;

  public Tessera getTessera() {
  return null;
  }
   

	public boolean tesseraDisponibile()
	{
		// TODO Auto-generated method stub
		return false;
	}


	protected abstract void acquisisciMazzoDaFile(String pathFileTessere) ;

}