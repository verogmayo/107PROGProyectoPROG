/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.agenciaalquiler1.modelo;

/**
 *
 * @author Véro
 */
public enum Grupo {
    
    
    A(50,1.5f,5.0f),B(55,2.0f,10.0f),C(60,2.5f,15);
private final int precioBase;
private final float factorTurismo;
private final float factorFurgoneta;


private Grupo(int precioBase,float factorTurismo, float factorFurgoneta){
this.precioBase=precioBase;
this.factorTurismo = factorTurismo;
this.factorFurgoneta = factorFurgoneta;
        
}
    
    public int getPrecioBase() {
        return precioBase;
    }

    public float getFactorTurismo() {
        return factorTurismo;
    }

    public float getFactorFurgoneta() {
        return factorFurgoneta;
    }

   /* @Override
    public String toString(){
        return name();
    }*/


}
