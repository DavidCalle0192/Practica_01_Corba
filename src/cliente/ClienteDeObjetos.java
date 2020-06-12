package cliente;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import sop_corba.*;
import sop_corba.GestionAsintomaticosPackage.asintomaticoDTO;
import sop_corba.GestionAsintomaticosPackage.asintomaticoDTOHolder;

public class ClienteDeObjetos {
    //*** Atributo estático ***

    static GestionAsintomaticos ref;

    public static void main(String args[]) {
        try {
            // se crea e inicia el ORB
            ORB orb = ORB.init(args, null);

            // se obtiene la referencia al name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "objAnteproyecto";
            ref = GestionAsintomaticosHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtenido el manejador sobre el servidor de objetos: " + ref);
            String nombre;
            String apellido;
            String tipo_id;
            int id;
            String direccion;
            
            int rta = 0;
            do {
                rta = menu();
                
                switch(rta){
                    case 1:
                        
                        System.out.println(" Digite el nombre del paciente: ");
                        nombre = UtilidadesConsola.leerCadena();
                        
                        System.out.println(" Digite el apellido del paciente: ");
                        apellido = UtilidadesConsola.leerCadena();
                        
                        System.out.println(" Digite el tipo de identificación del paciente: ");
                        tipo_id = UtilidadesConsola.leerCadena();
                        
                        System.out.println(" Digite la identificacion : ");
                        id = UtilidadesConsola.leerEntero();
                        
                        System.out.println(" Digite la dirección de paciente: ");
                        direccion = UtilidadesConsola.leerCadena();
                       
                      
                       asintomaticoDTO paciente = new asintomaticoDTO(nombre, apellido, tipo_id, id, direccion);
                       BooleanHolder res = new BooleanHolder(); 
                       ref.registrarAsintomatico(paciente,res);
                       
                        if (res.value){
                            System.out.println("Paciente registrado con éxito");
                        }
                        else{
                            System.out.println("No ha sido posible registrar el paciente. Ya hay 5 pacientes");
                        }
                        break;
                        
                    case 2:
                        asintomaticoDTO objAsintomatico = new asintomaticoDTO();
                        asintomaticoDTOHolder asin_bus = new asintomaticoDTOHolder();
                        asin_bus.value=objAsintomatico;
                        System.out.println(" Digite el número de identificación del paciente: ");
                        id = UtilidadesConsola.leerEntero();                        
                       
                        boolean pacienteObtenido = ref.consultarAsintomatico(id, asin_bus);
                        if (pacienteObtenido){
                            mostrarPaciente(asin_bus.value);
                        }
                        else{
                            System.out.println("No se ha encontrado el paciente");
                        }
                        break;
                }
                
            }while(rta != 3);
            

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
    
    public static int menu() {
        
        System.out.println(" :: MENU ::");
        System.out.println(" :1: Registrar Asintomatico");
        System.out.println(" :2: Consultar Asintomatico");
        System.out.println(" :3: Salir");
        int rta = UtilidadesConsola.leerEntero();
        
        return rta;
        
    }
    
    public static void mostrarPaciente(asintomaticoDTO paciente){        
        
        System.out.println("------------------------------");
        System.out.println("Número de identificación: "+paciente.id);
        System.out.println("Tipo ID: "+paciente.tipo_id);
        System.out.println("Nombre: "+paciente.nombres);
        System.out.println("Apellido: "+paciente.apellidos);
        System.out.println("Dirección: "+paciente.direccion);
        
    }
}
