/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.agenciaalquiler1.dao;

import com.mycompany.agenciaalquiler1.modelo.Furgoneta;
import com.mycompany.agenciaalquiler1.modelo.Turismo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Véro
 * @version 1.0
 * @since 6 abr 2025
 */
public class VehiculoXml implements VehiculoDao {
    private Path path;
    private static final Logger LOG = Logger.getLogger(VehiculoXml.class.getName());
    

    public VehiculoXml(String path) {
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
         List<Vehiculo> listadoVehiculos = null;

        try (BufferedReader br = Files.newBufferedReader(path);) {
            listadoVehiculos = (List<Vehiculo>) getXStream().fromXML(br);
            
        } catch (IOException ioe) {
            LOG.warning(ioe.toString());
            System.out.println(ioe.getMessage());
        }catch (StreamException ioe) {
             LOG.log(Level.WARNING, "El archivo no se ajusta al formato");
             throw new DaoException("Archivo no se ajustqa al formato");
        }
        return listadoVehiculos;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
        
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            getXStream().toXML(vehiculos, bw);
        } catch (IOException ioe) {
            LOG.warning(ioe.toString());
            System.out.println(ioe.getMessage());
        }
        return vehiculos.size();
    }
    private XStream getXStream(){
        XStream xstream = new XStream(new DomDriver());
        xstream.allowTypeHierarchy(Turismo.class);
        xstream.allowTypeHierarchy(Furgoneta.class);
        return xstream;
}
    
    
}
