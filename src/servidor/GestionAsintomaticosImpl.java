/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.HashMap;
import org.omg.CORBA.BooleanHolder;
import sop_corba.GestionAsintomaticosPOA;
import sop_corba.GestionAsintomaticosPackage.asintomaticoDTO;
import sop_corba.GestionAsintomaticosPackage.asintomaticoDTOHolder;

public class GestionAsintomaticosImpl extends GestionAsintomaticosPOA{

    HashMap <Integer, asintomaticoDTO> pacientes = new HashMap<>();
    public GestionAsintomaticosImpl() {        
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
        System.out.println("Ejecutando consultarAsintomatico...");
        boolean res=false;
        
        if(asin_bus!=null){
            if(pacientes.containsKey(id)){
                asin_bus.value = pacientes.get(id);
                res = true;
            }else{
                if(asin_bus.value==null)asin_bus.value=new asintomaticoDTO();
                System.out.println("No se encontro el asintomatico con id "+id);
            }
        }else{
            System.out.println("asin_bus null");
        }
        
        return res;
    }
}
