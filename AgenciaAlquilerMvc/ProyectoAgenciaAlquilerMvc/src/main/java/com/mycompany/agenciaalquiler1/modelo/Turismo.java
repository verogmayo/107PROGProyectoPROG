/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agenciaalquiler1.modelo;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Véro
 * @version 1.0
 */
public class Turismo extends Vehiculo {
    private int plazas;//propiedad
    private static final Logger LOG = Logger.getLogger(Turismo.class.getName());
    
// toda clase tiene que tener un constructor vacío
    //
    public Turismo() {
    }
    // tiene que tener un cosntructor por defecto y si quieres el vacio se lo tienes 
    //que poner y para que pueda tener constructor vacio, tiene que haber uno la clase madre
    public Turismo(String matricula) throws MatriculaException {
        super(matricula);
    }

    public Turismo(String matricula, Grupo grupo, int plazas) throws MatriculaException{
       
        super(matricula, grupo);
        if(plazas<0 || plazas>9){
          LOG.log(Level.WARNING, "El numero de plazas introducido no cumple con los criterios");
        throw new IllegalArgumentException("Numero de plazas, fuera de rango"); // esta es no comprobada
           
        }
       
        this.plazas = plazas;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        if(plazas<0 || plazas>9){
            LOG.log(Level.WARNING, "El numero de plazas introducido no cumple con los criterios");
                throw new IllegalArgumentException("Numero de plazas, fuera de rango"); // esta es no comprobada
        }
        this.plazas = plazas;
    }
// Este toString está en insert code y en override method, para que tome en cuenta lo del super
    @Override
    public String toString() {
        return super.toString()+","+plazas; //le añadimos a lo que no tenía del super, las plazas.
        //(TODO LO SUPER HACE REFERENCIA A LA CLASE PADRE)
    }
    
    @Override
    public float getPrecioAlquiler(){
        /*float precio=0;
        switch(getGrupo()){//hay que poner getGrupo porque es privado en vehiculo
        case A->{precio=50+1.5f*plazas;}// Se puede hacer sin {}
        case B->{precio=55+2f*plazas;}
        case C->{precio=50+2.5f*plazas;}
        default->{System.out.println("Este grupo no existe");}
    }
        return precio;*/
        
        return getGrupo().getPrecioBase()+getGrupo().getFactorTurismo()*plazas;
        
    }
    
    
}
