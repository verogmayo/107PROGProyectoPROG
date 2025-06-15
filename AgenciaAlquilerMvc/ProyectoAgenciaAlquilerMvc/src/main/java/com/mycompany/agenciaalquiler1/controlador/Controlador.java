/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agenciaalquiler1.controlador;

import com.mycompany.agenciaalquiler1.dao.DaoException;
import com.mycompany.agenciaalquiler1.dao.VehiculoCsv;
import com.mycompany.agenciaalquiler1.modelo.AgenciaAlquiler;
import com.mycompany.agenciaalquiler1.modelo.ComparadorPrecio;
import com.mycompany.agenciaalquiler1.modelo.Furgoneta;
import com.mycompany.agenciaalquiler1.modelo.Grupo;
import com.mycompany.agenciaalquiler1.modelo.MatriculaException;
import com.mycompany.agenciaalquiler1.modelo.Turismo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import com.mycompany.agenciaalquiler1.vista.Ventana;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Véro
 * @version 1.0
 * @since 11 may 2025
 */
public class Controlador {

    private Ventana vista;
    private AgenciaAlquiler modelo;
    private static final Logger LOG = Logger.getLogger(Controlador.class.getName());

    public Controlador(Ventana vista, AgenciaAlquiler modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public AgenciaAlquiler getModelo() {
        return modelo;
    }

    public Ventana getVista() {
        return vista;
    }

    public void setModelo(AgenciaAlquiler modelo) {
        this.modelo = modelo;
    }

    public void setVista(Ventana vista) {
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrar();
    }

    public void crearVehiculo() {
        String matricula = vista.getMatricula();
        String grupoString = vista.getGrupo();
        String tipo = vista.getTipo();
        int plazas = vista.getPlazas();
        float capacidad = vista.getCapacidad();

        boolean vehiculoCreado = false;

        Grupo grupo = Grupo.valueOf(grupoString.toUpperCase());
        switch (tipo) {
            case "TURISMO" -> {
                try {
                    Turismo turismo = new Turismo(matricula, grupo, plazas);
                    vehiculoCreado = modelo.incluirVehiculo(turismo);
                } catch (MatriculaException ex) {
                    LOG.warning(ex.getMessage());
                    vista.mostrarMensaje("Matricula incorrecta");
                }catch (IllegalArgumentException | InputMismatchException ex) {
                    LOG.warning(ex.getMessage());
                    vista.mostrarMensaje("Error en la entrada");
                }
            }
            case "FURGONETA" -> {
                try {
                    Furgoneta furgoneta = new Furgoneta(matricula, grupo, capacidad);
                    vehiculoCreado = modelo.incluirVehiculo(furgoneta);
                } catch (MatriculaException ex) {
                    LOG.warning(ex.getMessage());
                    vista.mostrarMensaje("Matricula incorrecta");
                }catch (IllegalArgumentException | InputMismatchException ex) {
                    LOG.warning(ex.getMessage());
                    vista.mostrarMensaje("Error en la entrada");
                }
            }
            default -> {
                vista.mostrarMensaje("Tipo de vehiculo desconocido");
            }
        }
        if (vehiculoCreado) {
            // vista.mostrarIngresos(empleado.ingresos());
            vista.mostrarMensaje("Vehiculo creado ");
            vista.listarVehiculos(modelo.listarVehiculos());
        } else {
            vista.mostrarMensaje("NO SE HA PODIDO CREAR EL VEHICULO");
        }

    }

    public void buscarVehiculo() {
        String matricula = vista.getMatricula();
        Vehiculo vehiculo = modelo.consultarVehiculo(matricula);
        if (vehiculo != null) {
            vista.mostrarMatricula(vehiculo.getMatricula());
            vista.mostrarGrupo(matricula);
            if (vehiculo instanceof Turismo) {
                vista.mostrarTipo("TURISMO");
                vista.mostrarPlazas(((Turismo) vehiculo).getPlazas());
                vista.mostrarPrecioAlquiler(((Turismo) vehiculo).getPrecioAlquiler());
            } else if (vehiculo instanceof Furgoneta) {
                vista.mostrarTipo("FURGONETA");
                vista.mostrarCapacidad(((Furgoneta) vehiculo).getCapacidad());
                vista.mostrarPrecioAlquiler(((Furgoneta) vehiculo).getPrecioAlquiler());

            }

        } else {
            vista.mostrarMensaje("No exixte un vehiculo con esa matricula");
        }
    }

    public void modificarVehiculo() {
        String matricula = vista.getMatricula();
        String grupoString = vista.getGrupo();
        String tipo = vista.getTipo();
        int plazas = vista.getPlazas();
        float capacidad = vista.getCapacidad();

        Vehiculo vehiculo;
        try {
            Grupo grupo = Grupo.valueOf(grupoString.toUpperCase());
            if (tipo.equals("TURISMO")) {
                vehiculo = new Turismo(matricula, grupo, plazas);
            } else {
                vehiculo = new Furgoneta(matricula, grupo, capacidad);
            }

            if (modelo.modificarVehiculo(vehiculo)) {
                vista.mostrarMensaje("Vehiculo modificado:" + vehiculo);
                vista.listarVehiculos(modelo.listarVehiculos());
            }else {
                vista.mostrarMensaje("No se ha podido modificar el vehiculo");
            }

        } catch (MatriculaException ex) {
                    LOG.warning(ex.getMessage());
                    vista.mostrarMensaje("Matricula incorrecta");
                }catch (IllegalArgumentException | InputMismatchException ex) {
                    LOG.warning(ex.getMessage());
                    vista.mostrarMensaje("Error en la entrada");
                }
    }

    public void eliminarVehiculo() {
        String matricula = vista.getMatricula();
        Vehiculo vehiculo = modelo.consultarVehiculo(matricula);
        if (vista.solicitarConfirmacion()) {
            if (modelo.eliminarVehiculo(vehiculo)) {
                vista.mostrarMensaje("Vehiculo eliminado");
                vista.listarVehiculos(modelo.listarVehiculos());
            } else {
                vista.mostrarMensaje("No se ha podido eliminar el vehiculo");
            }
        }
    }

     public void listarVehiculos() {
        List<Vehiculo> listado = new ArrayList<>();
        List<Vehiculo> listadoTemporal = modelo.listarVehiculos();
        //System.out.println("Radio button seleccionado " + vista.getOrden());
 
       
        try {
            //String grupoString = vista.getGrupo();
            //Grupo grupo = Grupo.valueOf(grupoString.toUpperCase());
           
            switch (vista.getFiltroGrupo()) {
                case "TODOS" ->{
                    listado = listadoTemporal;
                
                }  
                case "A" -> {
                    listadoTemporal = modelo.listarVehiculosPorGrupo(Grupo.A);
                
                }
                case "B" ->{
                    listadoTemporal = modelo.listarVehiculosPorGrupo(Grupo.B);
                
                }
                case "C" ->{
                    listadoTemporal = modelo.listarVehiculosPorGrupo(Grupo.C);
               
                }
            }
        } catch (IllegalArgumentException ex) {
            vista.mostrarMensaje("Grupo no válido: " + ex.getMessage());
        }
        //String tipo = vista.getTipo();
        switch (vista.getFlitroTipo()) {
            case "TODOS" ->{
                listado = listadoTemporal;
                
            }  
            case "TURISMO" -> {
                listadoTemporal = modelo.listarVehiculosTipo("TURISMO");
               
            }
            case "FURGONETA" ->{
                listadoTemporal = modelo.listarVehiculosTipo("FURGONETA");
              
            }
        }
       
        switch (vista.getOrden()) {
            case "MATRÍCULA" ->{
                //System.out.println("en m");
                Arrays.sort(listadoTemporal.toArray());
                listado = listadoTemporal;
                
            }
            case "PRECIO ALQUILER" ->{
                listadoTemporal.sort(new ComparadorPrecio());
                listado = listadoTemporal;
                
            }
        }
        vista.listarVehiculos(listado);
    }

    public void cargarVehiculos() throws DaoException {
        String nombreArchivo = vista.getArchivo();
        modelo.setVehiculoDao(new VehiculoCsv(nombreArchivo));
        try {
            modelo.cargarEmpleados();
            vista.listarVehiculos(modelo.listarVehiculos());
        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    public void guardarVehiculos() {
        String nombreArchivo = vista.getArchivo();
        modelo.setVehiculoDao(new VehiculoCsv(nombreArchivo));
        try {
            modelo.guardarVehiculos();
            vista.mostrarMensaje(String.format("Vehiculos cargados:"));

        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }

    }

    public void buscarVehiculoMasBarato() {
        String matricula = vista.getMatricula();
        Vehiculo vehiculo = modelo.getVehiculoMasBarato();
        if (vehiculo != null) {
            vista.mostrarMatricula(vehiculo.getMatricula());
            vista.mostrarGrupo(matricula);
            if (vehiculo instanceof Turismo) {
                vista.mostrarTipo("TURISMO");
                vista.mostrarPlazas(((Turismo) vehiculo).getPlazas());
                vista.mostrarPrecioAlquiler(((Turismo) vehiculo).getPrecioAlquiler());
            } else if (vehiculo instanceof Furgoneta) {
                vista.mostrarTipo("FURGONETA");
                vista.mostrarCapacidad(((Furgoneta) vehiculo).getCapacidad());
                vista.mostrarPrecioAlquiler(((Furgoneta) vehiculo).getPrecioAlquiler());

            }
        } else {
            vista.mostrarMensaje("No exixte un vehiculo con esa matricula");
        }
    }

    public void buscarVehiculoMasCaro() {

        String matricula = vista.getMatricula();
        Vehiculo vehiculo = modelo.getVehiculoMasCaro();
        if (vehiculo != null) {
            vista.mostrarMatricula(vehiculo.getMatricula());
            vista.mostrarGrupo(matricula);
            if (vehiculo instanceof Turismo) {
                vista.mostrarTipo("TURISMO");
                vista.mostrarPlazas(((Turismo) vehiculo).getPlazas());
                vista.mostrarPrecioAlquiler(((Turismo) vehiculo).getPrecioAlquiler());
            } else if (vehiculo instanceof Furgoneta) {
                vista.mostrarTipo("FURGONETA");
                vista.mostrarCapacidad(((Furgoneta) vehiculo).getCapacidad());
                vista.mostrarPrecioAlquiler(((Furgoneta) vehiculo).getPrecioAlquiler());
            }
        } else {
            vista.mostrarMensaje("No exixte un vehiculo con esa matricula");
        }
    }
    
     public String getEntradaExamen(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }
        
     
     
     public void exercicio1(){
             Vehiculo vehiculo;
        String matricula = vista.getEntradaExamen("Introduzca la matricula:");
        vehiculo=modelo.consultarVehiculo(matricula);
        boolean vehiculoCreado = false;
        if(vehiculo!=null){
            String precio= String.valueOf(modelo.consultarPrecio(matricula));
            vista.mostrarMensaje(precio);
        }else{
                 try {
                     String grupoString = vista.getEntradaExamen("Introduzca el grupo :");
                     Grupo grupo = Grupo.valueOf(grupoString.toUpperCase());
                     int plazas = Integer.parseInt(vista.getEntradaExamen("Introduzca el numero de plazas :"));
                     Turismo turismo = new Turismo(matricula, grupo, plazas);
                     vehiculoCreado = modelo.incluirVehiculo(turismo);
                      if (vehiculoCreado) {
            
            vista.mostrarMensaje("TURISMO INCLUIDO EN EL SISTEMA ");
            vista.listarVehiculos(modelo.listarVehiculos());
        } 
                } catch (MatriculaException ex) {
                     vista.mostrarMensaje("Matricula incorrecta");
                     LOG.log(Level.WARNING, "Matricula incorrecta", ex);
                }
      
        }
        
    }
    public void exercicio2(){
         
      if(modelo.getTurismoMasPlazas()!=null){
                   
      vista.mostrarMensaje("Turismo con más plazas:\n " +
                                 "Matrícula: " + modelo.getTurismoMasPlazas().getMatricula() +"\n"+
                                 "Plazas: " + modelo.getTurismoMasPlazas().getPlazas()); 
      }else{
          vista.mostrarMensaje("Vehiculo no disponible");
          LOG.log(Level.WARNING, "Turismo, no encontrado");
      };
       
   }
    
   
    }

    
    
    
