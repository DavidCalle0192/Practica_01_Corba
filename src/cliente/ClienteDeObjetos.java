package cliente;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import sop_corba.*;
import sop_corba.GestionPacientesPackage.pacienteDTO;

public class ClienteDeObjetos {
    //*** Atributo estático ***

    static GestionPacientesOperations ref;

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
            ref = GestionPacientesHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtenido el manejador sobre el servidor de objetos: " + ref);
            int numHabitacion;
            int edad;
            String nombre;
            String apellido;
            
            int rta = 0;
            do {
                rta = menu();
                
                switch(rta){
                    case 1:
                        System.out.println(" Digite el número de habitación : ");
                        numHabitacion = UtilidadesConsola.leerEntero();

                        System.out.println(" Digite el nombre del paciente: ");
                        nombre = UtilidadesConsola.leerCadena();
                        
                        System.out.println(" Digite el apellido del paciente: ");
                        apellido = UtilidadesConsola.leerCadena();
                        
                        System.out.println(" Digite la edad : ");
                        edad = UtilidadesConsola.leerEntero();
                       
                      
                        pacienteDTO paciente = new pacienteDTO(nombre, apellido, edad, numHabitacion);
                        boolean bandera=ref.registrarPaciente(paciente);
                        
                        if (bandera){
                            System.out.println("Paciente registrado con éxito");
                        }
                        else{
                            System.out.println("No ha sido posible registrar el paciente. Ya hay 5 pacientes");
                        }
                        break;
                        
                    case 2:
                        System.out.println(" Digite el número de habitación del paciente: ");
                        numHabitacion = UtilidadesConsola.leerEntero();                        
                        pacienteDTO pacienteObtenido = ref.consultarPaciente(numHabitacion);
                        if (pacienteObtenido.numeroHabitacion!=-1){
                            mostrarPaciente(pacienteObtenido);
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
        System.out.println(" :1: Registrar Paciente");
        System.out.println(" :2: Consultar Paciente");
        System.out.println(" :3: Salir");
        int rta = UtilidadesConsola.leerEntero();
        
        return rta;
        
    }
    
    public static void mostrarPaciente(pacienteDTO paciente){        
        
        System.out.println("------------------------------");
        System.out.println("Número de habitación: "+paciente.numeroHabitacion);
        System.out.println("Nombre: "+paciente.nombre);
        System.out.println("Apellido: "+paciente.apellido);
        System.out.println("Edad: "+paciente.edad);
        
    }
}
