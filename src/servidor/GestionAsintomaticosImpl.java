/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.HashMap;
import org.omg.CORBA.BooleanHolder;
import sop_corba.GestionPacientesPOA;
import sop_corba.GestionPacientesPackage.asintomaticoDTO;
import sop_corba.GestionPacientesPackage.asintomaticoDTOHolder;

public class GestionPacienteImpl extends GestionPacientesPOA{

    HashMap <Integer, asintomaticoDTO> pacientes = new HashMap<>();
    public GestionPacienteImpl() {        
    }
    
    @Override
    public void registrarAsintomatico(asintomaticoDTO asin_reg, BooleanHolder res) {
        System.out.println("Ejecutando registrarAsintomatico...");
        res.value = false;
        if (pacientes.size() < 5){
            if(!pacientes.containsKey(asin_reg.id)){
                pacientes.put(asin_reg.id, asin_reg);
                res.value = true;
            }else{
                System.out.println("Id "+asin_reg.id+" ya esta registrado");
            }
        }else{
            System.out.println("Registros llenos");
        }
    }

    @Override
    public boolean consultarAsintomatico(int id, asintomaticoDTOHolder asin_bus) {
        asin_bus.value = pacientes.get(id);
        return asin_bus.value != null;
    }
}
