/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.agenciaalquiler1;

import com.mycompany.agenciaalquiler1.modelo.AgenciaAlquiler;
import com.mycompany.agenciaalquiler1.modelo.Turismo;
import com.mycompany.agenciaalquiler1.modelo.Furgoneta;
import com.mycompany.agenciaalquiler1.modelo.Grupo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import com.mycompany.agenciaalquiler1.modelo.MatriculaException;
import com.mycompany.agenciaalquiler1.dao.VehiculoObj;
import com.mycompany.agenciaalquiler1.dao.DaoException;
import com.mycompany.agenciaalquiler1.dao.VehiculoCsv;
import com.mycompany.agenciaalquiler1.dao.VehiculoDao;
import com.mycompany.agenciaalquiler1.dao.VehiculoXml;
import com.mycompany.agenciaalquiler1.dao.VehiculoJson;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author Véro
 */
public class AppAgenciaAlquiler {

    private static final Logger LOG = Logger.getLogger("com.mycompany.agenciaalquiler1");

    public static void main(String[] args) throws IOException {
        AgenciaAlquiler aa = new AgenciaAlquiler();//hay que crear el sistema Nominas
        Vehiculo vehiculo = null;
        String matricula;
        String grupo;
        float capacidad;
        int opcion, precio, plazas;
        boolean error;
        Scanner teclado = new Scanner(System.in);
        aa = new AgenciaAlquiler("Sauces");//aqui habrea que leer desde el archivo de propiedades load..
        List<Vehiculo> listadoAImprimir;
        String seguro;

        LogManager.getLogManager().readConfiguration(ClassLoader.getSystemClassLoader().getResourceAsStream("mylogging.properties"));
        do {
            System.out.println("1- Crear Turismo");
            System.out.println("2- Crear furgoneta");
            System.out.println("3- Consultar vehículo");
            System.out.println("4- Eliminar vehículo");
            System.out.println("5- Listar vehículo por precio");
            System.out.println("6- Listar turismos");
            System.out.println("7- Listar furgonetas");
            System.out.println("8- Listar vehiculos por grupo");
            System.out.println("9- Consultar alquiler más barato");
            System.out.println("10- Guardar vehiculos");
            System.out.println("11- Cargar Vehiculos");
            System.out.println("12- Consultar alquiler más caro");
            System.out.println("0- Salir");
            System.out.print("Introduzca tu opcion:");
            try {
                //exception para que no salga mensage de error de netbeans cuando se introduzca una letra 
                opcion = teclado.nextInt();
                //  LOG.info(seguro);
            } catch (InputMismatchException ime) {
                //los systems estan ahi para ver mque funciona.
                opcion = 1000;
            }
            teclado.nextLine();
            switch (opcion) {
                case 1 -> {
                    do {
                        error = false;
                        try {
                            System.out.print("INTRODUCCIÓN DE UN TURISMO NUEVO");
                            System.out.println(" ");

                            System.out.print("Introducir la matricula del turismo :  ");
                            matricula = teclado.nextLine();
                            while (!Vehiculo.esMatriculaValida(matricula)) {
                                System.out.println("Error en la matricula");
                                System.out.print("Introducir la matricula del turismo :  ");
                                matricula = teclado.nextLine();
                            }
                            System.out.print("Introducir el grupo del vehículo (A,B o C)");
                            System.out.println("Introducir grupo " + Arrays.toString(Grupo.values()));
                            //grupo=teclado.nextLine();
                            //hay que pasar el grupo a String para poder leerlo.
                            grupo = teclado.nextLine();
                            System.out.print("Introducir el numero de plazas:  (0 a 9)");
                            plazas = teclado.nextInt();
                            // vehiculo=new Turismo(matricula, Grupo.valueOf(grupo), plazas);

                            vehiculo = new Turismo(matricula, Grupo.valueOf(grupo), plazas); //Creamos el turimo
                            if (aa.incluirVehiculo(vehiculo)) {//pq es booleano
                                System.out.println("Turismo creado con exito. " + vehiculo);
                            } else {
                                System.out.println("No se puede crear el turismo. ");
                                error = true;
                            }
                        } catch (MatriculaException | InputMismatchException | IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());

                        } finally {
                            teclado.nextLine();
                        }
                    } while (error);
                }

                case 2 -> {
                    try {
                        System.out.print("INTRODUCCIÓN DE UNA FURGONETA NUEVA");
                        System.out.println(" ");
                        System.out.print("Introducir la matricula de la furgoneta:  ");
                        matricula = teclado.nextLine();
                        System.out.print("Introducir el grupo del vehículo (A,B o C)");
                        //hay que pasar el grupo a String para poder leerlo.
                        grupo = teclado.nextLine();
                        System.out.print("Introducir la capacidad del vehículo: (0 a 10) ");
                        capacidad = teclado.nextFloat();

                        vehiculo = new Furgoneta(matricula, Grupo.valueOf(grupo), capacidad); //Creamos el turimo
                        if (aa.incluirVehiculo(vehiculo)) {//pq es bvooleano
                            System.out.println("Furgoneta creado con exito. " + vehiculo);
                        } else {
                            System.out.println("No se puede crear la furgoneta. ");
                        }
                    } catch (MatriculaException | InputMismatchException | IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    } finally {
                        teclado.nextLine();
                    }
                }
                case 3 -> {

                    System.out.println("Introducir la matricula del vehiculo a consultar:");
                    matricula = teclado.nextLine();
                    vehiculo = aa.consultarVehiculo(matricula);
                    if (vehiculo != null) {
                        System.out.println("El vehículo consultado es : " + vehiculo.toString());//no se debería utilizar un metodo lo utilizais dos veces sseguidas
                        //System.out.println(vehículo);  
                    } else {
                        System.out.println("El vehículo no existe");
                    }
                }
                case 4 -> {
                    System.out.print("Introducir la matricula del vehículo a eliminar : ");
                    matricula = teclado.nextLine();
                    vehiculo = aa.consultarVehiculo(matricula);
                    if (vehiculo != null) {
                        System.out.println(vehiculo);
                        System.out.print("¿Estás seguro que quieres eliminar el vehiculo (S/N)? ");
                        seguro = teclado.nextLine();
                        if (seguro.equalsIgnoreCase("S")) { // Comparación ignorando mayúsculas/minúsculas
                            if (aa.eliminarVehiculo(vehiculo)) {
                                System.out.println("Vehículo eliminado");
                            }
                        } else {
                            System.out.println("No se ha podido eliminar el vehículo");
                        }
                    } else {
                        System.out.println("Este vehículo no existe");
                    }
                }
                case 5 -> {

                    for (Vehiculo v : aa.listarVehiculosPorPrecio()) {
                        System.out.printf(v.toString());
                        System.out.println("");
                    }
                    /*for(Vehiculo v: aa.listarVehiculosPorPrecio()){
                        System.out.printf("matricula : %s\n ",v.getMatricula());
                        System.out.printf("Grupo : %s \n",v.getGrupo());
                        }
                    }*/

                }
                case 6 -> {
                    for (Vehiculo v : aa.listarTurismos()) {
                        System.out.println(" Listado de turismos : " + v.toString());
                        System.out.println("");
                    }
                    /*
                    for(Vehiculo v: aa.getVehiculos()){
                    if(v instanceof Turismo t){
                    sout(t)
                    }
                        
                  
                    }
                     */
                }
                case 7 -> {

                    for (Vehiculo v : aa.listarFurgonetas()) {
                        System.out.println("Listado de furgonetas : " + v.toString());
                        System.out.println("");
                    }
                    /*
                    for(Vehiculo v: aa.getVehiculos()){
                    if(v instanceof Furgoneta f){
                    sout(f)
                    }
                        
                  
                    }
                     */
                }
                case 8 -> {

                    System.out.println("Introduzca grupo " + Arrays.toString(Grupo.values()));
                    grupo = teclado.nextLine();
                    listadoAImprimir = aa.listarVehiculosPorGrupo(Grupo.valueOf(grupo));
                    mostrarListadoVehiculos(listadoAImprimir);

                }
                case 9 -> {
                    /*
                   System.out.println("Intriduce la matricula");
                    matricula=teclado.nextLine();
                    System.out.println("Precio del vehículo: " + aa.consultarPrecio(matricula));
                     */

                    if (vehiculo != null) {
                        System.out.printf("Alquiler más barato: %s con precio %.2f€%n", aa.getVehiculoMasBarato(), aa.getVehiculoMasBarato().getPrecioAlquiler());
                    }

                }
                case 10 -> {
                    System.out.println("Intoduzca el nombre del archivo : ");
                    String nombreArchivo = teclado.nextLine();
                    VehiculoDao vdao = getDao(nombreArchivo);
                    aa.setVehiculoDao(vdao);
                    try {
                        System.out.println(aa.guardarVehiculos());
                    } catch (DaoException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 11 -> {
                    System.out.println("Intoduzca el nombre del archivo : ");
                    String nombreArchivo = teclado.nextLine();
                    VehiculoDao vdao = getDao(nombreArchivo);
                    aa.setVehiculoDao(vdao);
                    try {
                        System.out.println(aa.cargarEmpleados());
                    } catch (DaoException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                 case 12 -> {
                    /*
                   System.out.println("Intriduce la matricula");
                    matricula=teclado.nextLine();
                    System.out.println("Precio del vehículo: " + aa.consultarPrecio(matricula));
                     */

                    if (vehiculo != null) {
                        System.out.printf("Alquiler más caro: %s con precio %.2f€%n", aa.getVehiculoMasCaro(), aa.getVehiculoMasCaro().getPrecioAlquiler());
                    }

                }
                  case 13 -> {
                      System.out.println(aa.listarVehiculos());

                }

                case 0 -> {
                    System.out.println("Bye.");
                }
                default -> {
                    System.out.println("Error en la opcion");
                }
            }
        } while (opcion != 0);
    }

    private static void mostrarListadoVehiculos(List<Vehiculo> listado) {
        for (Vehiculo v : listado) {
            System.out.println(v);
        }
    }

    private static VehiculoDao getDao(String nombreArchivo) {
        VehiculoDao vehiculoDao = null;
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
        vehiculoDao = switch (extension) {
            case ".csv" ->
                new VehiculoCsv(nombreArchivo);
            case ".json" ->
                new VehiculoJson(nombreArchivo);
            case ".obj" ->
                new VehiculoObj(nombreArchivo);
            case ".xml" ->
                new VehiculoXml(nombreArchivo);
            default ->
                null;

        };
        return vehiculoDao;
    }
}
