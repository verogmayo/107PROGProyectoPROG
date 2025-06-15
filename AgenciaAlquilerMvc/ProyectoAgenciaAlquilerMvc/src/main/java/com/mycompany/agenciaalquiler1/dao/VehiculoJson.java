/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.agenciaalquiler1.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.mycompany.agenciaalquiler1.modelo.Furgoneta;
import com.mycompany.agenciaalquiler1.modelo.Turismo;
import com.mycompany.agenciaalquiler1.modelo.Turismo;
import com.mycompany.agenciaalquiler1.modelo.Turismo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Véro
 * @version 1.0
 * @since 6 abr 2025
 */
public class VehiculoJson implements VehiculoDao {
    private Path path;
    private static final Logger LOG = Logger.getLogger(VehiculoJson.class.getName());

    public VehiculoJson(String path) {
        this.path = Paths.get(path);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = Paths.get(path);
    }

    @Override
    public List<Vehiculo> listar() throws DaoException {
           Type tipo = new com.google.gson.reflect.TypeToken<List<Vehiculo>>() {
        }.getType();
        if (!Files.exists(path)) {
            throw new DaoException("El archivo no existe: " + path);
        }
        List<Vehiculo> listadoVehiculos = new ArrayList<>();
        
        try (BufferedReader br = Files.newBufferedReader(path)) {
            listadoVehiculos = getGson().fromJson(br, tipo);
        } catch (IOException ioe) {
             throw new DaoException("Error al leer en el archivo");
        }catch (JsonParseException jpe) {
             throw new DaoException("Archivo no se ajustqa al formato");
        }
        return listadoVehiculos;
    }
    

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
         Type tipo = new com.google.gson.reflect.TypeToken<List<Vehiculo>>() {}.getType();//me sirve porqu estoy trabajando con generico
        
        // Todo esto se puede sacar en un metodo para llamarlo : quiero un archivo gson
        
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            getGson().toJson(vehiculos, tipo, bw);

        } catch (IOException ioe) {
            throw new DaoException("Error al excribir en el archivo");
        }
        return vehiculos.size();
    }
    
     private Gson getGson(){
     Type tipo = new com.google.gson.reflect.TypeToken<List<Vehiculo>>() {}.getType();//me sirve porque estoy trabajando con generico
        RuntimeTypeAdapterFactory<Vehiculo> vehiculoAdapter = RuntimeTypeAdapterFactory.of(Vehiculo.class, "tipo");
        vehiculoAdapter.registerSubtype(Turismo.class, "Turismo"); //a que clase pretenece y que quiero que aparezca
        vehiculoAdapter.registerSubtype(Furgoneta.class, "Furgoneta");
        GsonBuilder builder = new GsonBuilder(); //para que se vea bien y para incluir el adapterfactory
        builder.setPrettyPrinting();
        builder.registerTypeAdapterFactory(vehiculoAdapter);
        return builder.create();
    }
}
