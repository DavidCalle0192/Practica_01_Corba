package sop_corba;


/**
* sop_corba/GestionPacientesOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ges-asintomatico.idl
* jueves 11 de junio de 2020 04:36:57 PM COT
*/

public interface GestionPacientesOperations 
{
  void registrarAsintomatico (sop_corba.GestionPacientesPackage.asintomaticoDTO asin_reg, org.omg.CORBA.BooleanHolder res);
  boolean consultarAsintomatico (int id, sop_corba.GestionPacientesPackage.asintomaticoDTOHolder asin_bus);
} // interface GestionPacientesOperations
