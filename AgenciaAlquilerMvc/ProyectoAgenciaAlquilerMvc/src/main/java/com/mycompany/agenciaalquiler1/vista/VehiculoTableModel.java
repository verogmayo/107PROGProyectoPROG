/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.agenciaalquiler1.vista;

import com.mycompany.agenciaalquiler1.modelo.Furgoneta;
import com.mycompany.agenciaalquiler1.modelo.Turismo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Véro
 * @version 1.0
 * @since 11 may 2025
 */
public class VehiculoTableModel extends AbstractTableModel {
    private List<Vehiculo> listadoVehiculo;
    private String[] columnas = {"MATRÍCULA", "TIPO", "GRUPO", "PLAZAS", "CAPACIDAD","PRECIO ALQUILER"};
    private static final Logger LOG = Logger.getLogger(VehiculoTableModel.class.getName());

    public VehiculoTableModel() {
        listadoVehiculo = new ArrayList<>();
    }

       
    public void setListadoVehiculo(List<Vehiculo> listadoVehiculo) {
        this.listadoVehiculo = listadoVehiculo;
        //System.out.println(listadoVehiculo);
        this.fireTableDataChanged();
    }

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }
    
   
    
    @Override
    public int getRowCount() {
       
         return listadoVehiculo.size();
       
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Vehiculo vehiculo = listadoVehiculo.get(rowIndex);

        return switch (columnIndex) {
            case 0 ->
                vehiculo.getMatricula();
            case 1 ->
                vehiculo instanceof Turismo ? "TURISMO" : "FURGONETA";
            case 2 ->
                vehiculo.getGrupo().toString();
            case 3 -> vehiculo instanceof Turismo v ? v.getPlazas(): 0;
            case 4 -> vehiculo instanceof Furgoneta v ? v.getCapacidad():0f;
            case 5 -> vehiculo.getPrecioAlquiler();
            default ->
                null;
        };
    }
    
   /*  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Vehiculo vehiculo = listadoVehiculo.get(rowIndex);
         String matricula;
         Grupo grupo=A;
         int plazas;
         float capacidad;
        switch (columnIndex) {
            case 0 ->
            {              
                    vehiculo.setMatricula(matricula);
               
            }
            case 1 ->
                vehiculo.se;
                
            case 3 -> vehiculo.setGrupo(grupo);
            case 2 ->{
                if (vehiculo instanceof Turismo f) {
                    f.setPlazas(plazas);
                }
            
            }
            case 4 -> {
                if (vehiculo instanceof Furgoneta e) {
                    e.setCapacidad(capacidad);
                }
            }
            //No hace falta la columna ingresos porque es el resultado de la opración 
        }
    }
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    //Las celdas no pueden ser editadas por el usuario, en la tabla.
    }*/
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    //Las celdas no pueden ser editadas por el usuario, en la tabla.
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        return switch (columnIndex) {
        case 0 -> String.class;  // Matricula
        case 1 -> String.class;  // Tipo
        case 2 -> String.class;  // Grupo
        case 3 -> Integer.class; // Plazas
        case 4 -> Float.class;//Capacidad
        case 5 -> Float.class;//  Precio Alquiler   
        default -> Object.class;
    };
    }
    
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
}
