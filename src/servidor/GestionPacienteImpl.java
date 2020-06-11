/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.HashMap;
import sop_corba.GestionPacientesPOA;
import sop_corba.GestionPacientesPackage.pacienteDTO;

public class GestionPacienteImpl extends GestionPacientesPOA{

    HashMap <Integer, pacienteDTO> pacientes = new HashMap<>();
    public GestionPacienteImpl() {        
    }

    @Override
    public boolean registrarPaciente(pacienteDTO objPaciente) {
       System.out.println("Invocando a registrar paciente");
       boolean bandera=false;
       if (pacientes.size() < 5){
           pacientes.put(objPaciente.numeroHabitacion, objPaciente);
           bandera = true;
       }
       return bandera;
    }
    
    @Override
    public pacienteDTO consultarPaciente(int numeroHabitacion) {
        System.out.println("Invocando a consultar paciente");
        pacienteDTO objPaciente= new pacienteDTO("", "",0, -1);
        if (pacientes.get(numeroHabitacion) != null){
            objPaciente = pacientes.get(numeroHabitacion);            
        }
        return objPaciente;
    }    
}
