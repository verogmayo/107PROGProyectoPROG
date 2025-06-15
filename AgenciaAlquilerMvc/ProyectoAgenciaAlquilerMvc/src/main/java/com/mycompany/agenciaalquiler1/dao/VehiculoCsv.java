/*
 * Hola Mundo
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agenciaalquiler1.dao;

import com.mycompany.agenciaalquiler1.modelo.Furgoneta;
import com.mycompany.agenciaalquiler1.modelo.Grupo;
import com.mycompany.agenciaalquiler1.modelo.MatriculaException;
import com.mycompany.agenciaalquiler1.modelo.Turismo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author veronique.gru
 */
public class VehiculoCsv implements VehiculoDao {
    private Path path;
    private static final Logger LOG = Logger.getLogger(VehiculoCsv.class.getName());

    public VehiculoCsv(String path) {
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
        List<Vehiculo> listaVehiculos=new ArrayList<>();
        String[] palabra;
        
        String tipo, matricula;
        Grupo grupo;
        
        int plazas;
        float capacidad;
        try(BufferedReader br=Files.newBufferedReader(path)){
            String linea=br.readLine();
            while(linea !=null){
                palabra=linea.split(",");
                tipo = palabra[0];
                matricula=palabra[1];
                grupo=Grupo.valueOf(palabra[2]);
                
                
                
            switch (tipo){
                case"Turismo" ->{
                    plazas=Integer.parseInt(palabra[3]);
                listaVehiculos.add(new Turismo(matricula,grupo,plazas));
            }
                case"Furgoneta" ->{
                    capacidad=Float.parseFloat(palabra[3]);
                listaVehiculos.add(new Furgoneta(matricula,grupo,capacidad));
            }
            }
            linea=br.readLine();
            }
        
        } catch (NoSuchFileException nse) {
            LOG.warning(String.format("No existe el archivo", path.toString()));
            throw new DaoException(String.format("No existe el archivo", path.toString()));
        } catch (IOException ex) {
            Logger.getLogger(VehiculoCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MatriculaException ex) {
            LOG.warning(ex.toString());
            Logger.getLogger(VehiculoCsv.class.getName()).log(Level.SEVERE, null, ex);
           
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            LOG.log(Level.WARNING, "Formato del archivo incorrecto {0}", path.toString());
            throw new DaoException(ex.toString());
        }
        return listaVehiculos;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Vehiculo v :vehiculos) {
                bw.write(v.getClass().getSimpleName() + "," + v.toString());
                bw.newLine();
            }
        } catch (IOException ioe) {
            LOG.warning(String.format("Nombre del archivo incorrecto {0}", path.toString()));
            throw new DaoException("Error al excribir en el archivo");
        }

        return vehiculos.size();
    }

  
    
    
    
}
