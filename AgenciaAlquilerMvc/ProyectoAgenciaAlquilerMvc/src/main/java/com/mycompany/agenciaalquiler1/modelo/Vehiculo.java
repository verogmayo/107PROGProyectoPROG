/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agenciaalquiler1.modelo;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Véro
 * @version 1.0
 * @since 07/02/2025
 */
public abstract class Vehiculo implements Serializable, Comparable<Vehiculo> {
    // se puede crear los atributos con insert code y add property
    private String matricula;
    private Grupo grupo;
    private static final Logger LOG = Logger.getLogger(Vehiculo.class.getName());

    public Vehiculo() {
    }

    public Vehiculo(String matricula) throws MatriculaException {
        if(!esMatriculaValida(matricula)){
            LOG.log(Level.WARNING, "La matricula no se ha introducido correctamente");
            throw new MatriculaException("La Matricula no es valida");
        }
        this.matricula = matricula;
    }

    public Vehiculo(String matricula, Grupo grupo) throws MatriculaException {
        if(!esMatriculaValida(matricula)){
            LOG.log(Level.WARNING, String.format("La matricula no es valida",matricula));
            throw new MatriculaException("La Matricula no es valida");
        }
        this.matricula = matricula;
        this.grupo = grupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public String getMatricula() {
    
        return matricula;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setMatricula(String matricula) throws MatriculaException {
        if(!esMatriculaValida(matricula)){
            LOG.log(Level.WARNING, "La matricula no se ha introducido correctamente");
            throw new MatriculaException("La Matricula no es valida");
        }
        this.matricula = matricula;
    }
    
    
    @Override
    public String toString() {
        return  matricula + "," + grupo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj; //si no hago casting no podría ponr oter.matricula
        return Objects.equals(this.matricula, other.matricula);
    }
    
    @Override
    public int compareTo(Vehiculo v){// como quiero que se comparen los odjetos entre sí. comparte to dice que si devuelve un valor negativo, 
        //el primer objeto es menos al otro, si devuelve = son iguales y si es positivo es mas grande
        return this.matricula.compareTo(v.matricula);
    }
    
    public float getPrecioAlquiler(int dias){//se puede hacer aqui para controlar la fecha
        return this.getPrecioAlquiler()*dias; //días por precio de alquiler abstract que viene de los hjijos
    }
    public abstract float getPrecioAlquiler();
    
     public static boolean esMatriculaValida(String matricula){
        String patron = "[0-9]{4}[B-Z&&[^EIOUQ]]{3}";// https://elcodigoascii.com.ar/
        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(matricula); 
        return m.matches(); 
}
}