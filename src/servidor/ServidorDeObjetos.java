package servidor;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import sop_corba.GestionAsintomaticos;
import sop_corba.GestionAsintomaticos;
import sop_corba.GestionAsintomaticosHelper;

public class ServidorDeObjetos {

  public static void main(String args[]) {
    try{
                
      // crea e inicia el ORB
      ORB orb = ORB.init(args, null);      
      POA rootpoa =  POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      //*** crea una instancia del servant
      GestionAsintomaticosImpl objRemoto = new GestionAsintomaticosImpl();
       
      //*** se genera la referencia del servant
      org.omg.CORBA.Object obj = rootpoa.servant_to_reference(objRemoto);
      GestionAsintomaticos href = GestionAsintomaticosHelper.narrow(obj);
	  
      // se obtiene una referencia al name service
      org.omg.CORBA.Object objref =orb.resolve_initial_references("NameService");
     NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

      // *** se realiza el binding de la referencia del servant en el N_S ***
      String name = "objAnteproyecto";
      NameComponent path[] = ncref.to_name( name );
      ncref.rebind(path, href);

      System.out.println("El Servidor esta listo y esperando ...");

      // esperan por las invocaciones desde los clientes
      orb.run();
    } 
	
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
	  
      System.out.println("Servidor: Saliendo ...");
	
  }
}
