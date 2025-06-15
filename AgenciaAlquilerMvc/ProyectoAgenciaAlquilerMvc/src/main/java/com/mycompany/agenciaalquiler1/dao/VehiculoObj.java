/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.agenciaalquiler1.dao;

import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class VehiculoObj implements VehiculoDao {
    private Path path;
    private static final Logger LOG = Logger.getLogger(VehiculoObj.class.getName());

    public VehiculoObj(String path) {
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
        Vehiculo vehiculo;
        List<Vehiculo> listaEmpleados=new ArrayList<>();
        try (InputStream fis = Files.newInputStream(path); ObjectInputStream entrada = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                listaEmpleados.add((Vehiculo) entrada.readObject());
                /*System.out.format("%s,%s\n",empleado.getClass(),empleado.toString());*/
            }
        }catch (EOFException eofe) {
            LOG.warning("Fin de fichero");
            System.out.println("Fin de fichero");
        } catch (ClassNotFoundException cnfe) {
            LOG.warning("Objeto no esperado");
            System.out.println("Objeto no esperado");
        } catch (IOException ioe) {
            LOG.warning("Error de entrada/salida");
            System.out.println("Error de entrada/salida");
        }
        return listaEmpleados;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
         try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(path.toString()))) {

            for (Vehiculo e : vehiculos) {
                salida.writeObject(e);
                System.out.println(e);
            }
        } catch (FileNotFoundException fnfe) {
            LOG.warning("No existe el fichero");
            throw new DaoException("No existe el fichero");
        } catch (IOException ioe) {
            LOG.warning("Error entra/salida");
             throw new DaoException("Error entra/salida");
        }
        return vehiculos.size();
    }
    
    
    
}
