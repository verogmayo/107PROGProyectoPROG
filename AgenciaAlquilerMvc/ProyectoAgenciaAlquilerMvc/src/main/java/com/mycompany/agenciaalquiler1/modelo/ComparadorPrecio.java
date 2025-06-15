/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agenciaalquiler1.modelo;

import java.util.Comparator;

/**
 *
 * @author Véro
 * @version 1.0
 */
public class ComparadorPrecio implements Comparator<Vehiculo> {
    
    @Override
    public int compare(Vehiculo v1, Vehiculo v2){
        return Float.compare(v1.getPrecioAlquiler(), v2.getPrecioAlquiler());
    }
}
