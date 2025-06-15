/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agenciaalquiler1.modelo;

import com.mycompany.agenciaalquiler1.modelo.Grupo;
import com.mycompany.agenciaalquiler1.modelo.MatriculaException;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 * @verion 1.0
 */
public class Furgoneta extends Vehiculo {
    private Float capacidad;
    private static final Logger LOG = Logger.getLogger(Furgoneta.class.getName());

    public Furgoneta() {
    }

    public Furgoneta(String matricula) throws MatriculaException {
        
        super(matricula);
    }

    public Furgoneta( String matricula, Grupo grupo, Float capacidad) throws MatriculaException {
        
        super(matricula, grupo);// el super siempre tiene que ir primero
         if(capacidad<=0 || capacidad>10){
             LOG.log(Level.WARNING, "La capacidad introducida no cumple con los criterios");
                throw new IllegalArgumentException("Numero de plazas, fuera de rango"); // esta es no comprobada
        }
       
        this.capacidad = capacidad;
    }

    public Float getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Float capacidad) {
          if(capacidad<=0 || capacidad>10){
              LOG.log(Level.WARNING, "La capacidad introducida no cumple con los criterios");
                throw new IllegalArgumentException("Numero de plazas, fuera de rango"); // esta es no comprobada
        }
       
        this.capacidad = capacidad;
    }
    
// Este toString está en insert code y en override method, para que tome en cuenta lo del super
    @Override
    public String toString() {
        return super.toString()+","+capacidad;
    }
    
    @Override
    public float getPrecioAlquiler(){
     float precio=0;
     switch(getGrupo()){
         case A->{ precio= 50+5f*capacidad;}
         case B->{ precio= 55+10f*capacidad;}
         case C->{ precio= 60+15f*capacidad;}
     default->{System.out.println("Este grupo no existe");}    
     }
     return precio;
    }
    
}
