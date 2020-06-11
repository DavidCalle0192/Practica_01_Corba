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
            String[] vec = new String[4];
            vec[0] = "-ORBInitialPort";
            System.out.println("Ingrese la dirección IP donde escucha el n_s");
            vec[1] = UtilidadesConsola.leerCadena();
            vec[2] = "-ORBInitialPort";
            System.out.println("Ingrese el puerto donde escucha el n_s");
            vec[3] = UtilidadesConsola.leerCadena();

            // se crea e inicia el ORB
            ORB orb = ORB.init(vec, null);

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
                        
                        System.out.println(" Digite la edad : ");
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
                        if (objAsintomatico.id!=-1){
                            mostrarPaciente(objAsintomatico);
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
        //System.out.println("Número de habitación: "+paciente.numeroHabitacion);
        //System.out.println("Nombre: "+paciente.nombre);
        //System.out.println("Apellido: "+paciente.apellido);
        //System.out.println("Edad: "+paciente.edad);
        
    }
}
