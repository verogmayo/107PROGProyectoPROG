/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agenciaalquiler1.modelo;



import com.mycompany.agenciaalquiler1.dao.DaoException;

import com.mycompany.agenciaalquiler1.dao.VehiculoDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Véro
 */
public class AgenciaAlquiler {
    // Conjunto ordenado de vehículos (TreeSet garantiza orden y no permite duplicados)
    private TreeSet<Vehiculo> vehiculos; 
    private String nombre; // Nombre de la agencia de alquiler
    private VehiculoDao vehiculoDao;

    // Constructor sin parámetros, inicializa el conjunto de vehículos vacío
    public AgenciaAlquiler(){
        vehiculos = new TreeSet<>();
    }

    // Constructor que recibe el nombre de la agencia y también inicializa la colección
    public AgenciaAlquiler(String nombre) {
        this.nombre = nombre;
        vehiculos = new TreeSet<>();
    }

    // Getter para obtener la colección de vehículos
    public TreeSet<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    
    // Setter para establecer la colección de vehículos
    public void setVehiculos(TreeSet<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    public VehiculoDao getVehiculoDao(){
        return vehiculoDao;
       
    }

    public void setVehiculoDao(VehiculoDao vehiculoDao) {
        this.vehiculoDao = vehiculoDao;
    }
    
    // Método para añadir un vehículo a la colección. 
    // Devuelve true si se añadió correctamente y false si ya existía (por ser TreeSet).
    public boolean incluirVehiculo(Vehiculo vehiculo){
        return vehiculos.add(vehiculo);
    }

    // Método para buscar un vehículo por su matrícula y devolverlo si existe.
    public Vehiculo consultarVehiculo(String matricula){
        for (Vehiculo v : vehiculos) { // Recorremos el conjunto de vehículos
            if (v.getMatricula().equals(matricula)) { // Si encontramos el vehículo con la matrícula buscada
                return v;  
            }
        }
        return null; // Si no se encuentra, devolvemos null
    }

    // Método para consultar el precio de alquiler de un vehículo por su matrícula.
    public float consultarPrecio(String matricula){
        for (Vehiculo v : vehiculos) { // Recorremos la colección de vehículos
            if (v.getMatricula().equals(matricula)) { // Si encontramos el vehículo buscado
                return v.getPrecioAlquiler();  // Retornamos su precio de alquiler
            }
        }
        return 0f; // Si no se encuentra, devolvemos 0
    }
    //Metodo para modificar un vehiculo de la colección
    public boolean modificarVehiculo(Vehiculo vehiculo){
        if(vehiculo !=null  ){
            for (Vehiculo v: vehiculos){
                if(v.getMatricula().equals(vehiculo.getMatricula())){
                    vehiculos.remove(v);//Con treeset es impresindible eliminar el anterior
                    vehiculos.add(vehiculo);
                    return true;
                }
            }
        }
        return false;
    }


    // Método para eliminar un vehículo de la colección.
    // Devuelve true si se eliminó correctamente y false si no estaba en la lista.
    public boolean eliminarVehiculo(Vehiculo vehiculo){
        return vehiculos.remove(vehiculo);
    }

    // Método que devuelve una lista de todos los vehiculos.
    /*public List<Vehiculo> listarVehiculos(Grupo grupo){
        return new ArrayList<>(vehiculos); 
    }*/
    //Metdodo que devuelve una lista de vehiculos en funcion del grupo indicado
    public List<Vehiculo> listarVehiculosPorGrupo(Grupo grupo){
    List<Vehiculo> listadoGrupo = new ArrayList<>();
    for (Vehiculo v : vehiculos) {
        
        if (v.getGrupo() == grupo) {
            listadoGrupo.add(v);
        }
    }
    return listadoGrupo;
    }
   

    //Metodo que devuelve una lista de los vehiculos por matricula
    public List<Vehiculo> listarVehiculos() {
    return new ArrayList<>(vehiculos); // 
    
    }
    /* TAmbien se puede hacer así con Set
    public Set<Vehiculo> listarVehiculos() {
    return vehiculos; // o una copia defensiva
    }*/
    
    //Metodo que devuelve una lista de vehiculos en funcion del tipo indicado
    public List<Vehiculo> listarVehiculosTipo(String tipo) {
        
    List<Vehiculo> listadoTipo = new ArrayList<>();
    for (Vehiculo v : vehiculos) {
        if (tipo.equalsIgnoreCase("TURISMO") && v instanceof Turismo) {
            listadoTipo.add(v);
        } else if (tipo.equalsIgnoreCase("FURGONETA") && v instanceof Furgoneta) {
            listadoTipo.add(v);
        } else if (tipo.equalsIgnoreCase("TODOS")){
            listadoTipo.add(v);
        }
    }
    return listadoTipo;
    }
    // Método que devuelve una lista de vehículos ordenada por precio de alquiler.
    public List<Vehiculo> listarVehiculosPorPrecio(){
        List<Vehiculo> vehiculosPorPrecio = new ArrayList<>(vehiculos); // Copiamos los vehículos en una lista
        vehiculosPorPrecio.sort(new ComparadorPrecio()); // Ordenamos la lista según el comparador de precios
        
        return vehiculosPorPrecio; // Retornamos la lista ordenada
    }

    // Método que devuelve una lista con solo las furgonetas registradas en la agencia.
    public List<Vehiculo> listarFurgonetas() {
        List<Vehiculo> furgonetas = new ArrayList<>();
        for (Vehiculo v : vehiculos) { // Recorremos la colección de vehículos
            if (v instanceof Furgoneta) { // Si el vehículo es una instancia de Furgoneta
                furgonetas.add(v); // Lo añadimos a la lista de furgonetas
            }
        }
        return furgonetas; // Devolvemos la lista de furgonetas
    }
   

    // Método que devuelve una lista con solo los turismos registrados en la agencia.
    public List<Vehiculo> listarTurismos() {
        List<Vehiculo> turismos = new ArrayList<>();
        for (Vehiculo v : vehiculos) { // Recorremos la colección de vehículos
            if (v instanceof Turismo) { // Si el vehículo es una instancia de Turismo
                turismos.add(v); // Lo añadimos a la lista de turismos
            }
        }
        return turismos; // Devolvemos la lista de turismos
    }

    // Método que devuelve el vehículo con el precio de alquiler más bajo.
    public Vehiculo getVehiculoMasBarato(){
        if (!vehiculos.isEmpty()) { // Si hay vehículos en la colección
            return Collections.min(vehiculos, new ComparadorPrecio()); // Retornamos el vehículo con menor precio
        }
        return null; // Si la colección está vacía, retornamos null
    }
     public Vehiculo getVehiculoMasCaro(){
        if (!vehiculos.isEmpty()) { // Si hay vehículos en la colección
            return Collections.max(vehiculos, new ComparadorPrecio()); // Retornamos el vehículo con mayor precio
        }
        return null; // Si la colección está vacía, retornamos null
    }
    
    
    public int guardarVehiculos() throws DaoException{
    return vehiculoDao.insertar(new ArrayList(vehiculos));
    }
    
     public int cargarEmpleados() throws DaoException{//coge los datos de un archivo
        if(vehiculoDao==null){
           throw new DaoException("No está configuarado el DAO");
       }
        List<Vehiculo> listaVehiculos=vehiculoDao.listar();
        vehiculos=new TreeSet<>();
        for(Vehiculo e : listaVehiculos){
           vehiculos.add(e);
        }
        
                return vehiculos.size();
    }
     
   public Turismo getTurismoMasPlazas(){
       List<Turismo> turismos = new ArrayList<>();
       for (Vehiculo v : vehiculos){
           if(v instanceof Turismo){
               turismos.add((Turismo)v);
           }
       }
       if(!turismos.isEmpty()){
           
         Turismo maxPlazas=Collections.max(turismos, new Comparator<Turismo>() {
                @Override
                public int compare(Turismo o1, Turismo o2) {
                    
                    return Integer.compare(o1.getPlazas(), o2.getPlazas());
                }
                  
            });
         
         return maxPlazas;
       }else {
           return null;
       }
       
   }
    
    
    
}

/*
public class AgenciaAlquiler {
    // Mapa donde la clave es la matrícula y el valor es el vehículo
    private Map<String, Vehiculo> flota;
    private String nombre; // Nombre de la agencia

    // Constructor que recibe el nombre de la agencia e inicializa la flota como un TreeMap
    public AgenciaAlquiler(String nombre) {
        this.nombre = nombre;
        flota = new TreeMap<>(); // TreeMap ordena automáticamente las claves (matrículas)
    }

    // Método para obtener el nombre de la agencia
    public String getNombre(){
        return nombre;
    }

    // Método para establecer el nombre de la agencia
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    // Método para obtener la colección de vehículos almacenados en el mapa (como una lista)
    public List<Vehiculo> getVehiculos(){
        return new ArrayList<>(flota.values()); // Convertimos los valores del mapa a una lista
    }

    // Método para agregar un vehículo a la flota si la matrícula no existe
    public boolean incluirVehiculo(Vehiculo vehiculo){
        return flota.putIfAbsent(vehiculo.getMatricula(), vehiculo) == null;
        // `putIfAbsent` solo añade si la matrícula no existe; devuelve `null` si se añadió correctamente
    }

    // Método para buscar un vehículo por su matrícula
    public Vehiculo consultarVehiculo(String matricula){
        return flota.get(matricula); // `get` devuelve el vehículo si existe, o `null` si no
    }

    // Método para consultar el precio de alquiler de un vehículo por matrícula
    public float consultarPrecio(String matricula){
        Vehiculo v = flota.get(matricula); // Buscamos el vehículo en el mapa
        return (v != null) ? v.getPrecioAlquiler() : 0f; // Retornamos el precio si existe, o 0 si no
    }

    // Método para eliminar un vehículo de la flota
    public boolean eliminarVehiculo(Vehiculo vehiculo){
        return flota.remove(vehiculo.getMatricula()) != null;
        // `remove` devuelve el objeto eliminado o `null` si no existía
    }

    // Método que devuelve una lista de vehículos de un grupo específico
    public List<Vehiculo> listarVehiculos(Grupo grupo){
        List<Vehiculo> listado = new ArrayList<>();
        for (Vehiculo v : flota.values()) { // Recorremos todos los vehículos en el mapa
            if (v.getGrupo().equals(grupo)) { // Comparamos el grupo del vehículo con el solicitado
                listado.add(v);
            }
        }
        return listado;
    }

    // Método que devuelve una lista de vehículos ordenados por precio
    public List<Vehiculo> listarVehiculosPorPrecio(){
        List<Vehiculo> vehiculosPorPrecio = new ArrayList<>(flota.values()); // Convertimos el mapa en lista
        vehiculosPorPrecio.sort(new ComparadorPrecio()); // Ordenamos usando el comparador de precios
        return vehiculosPorPrecio;
    }

    // Método que devuelve una lista con solo las furgonetas
    public List<Vehiculo> listarFurgonetas() {
        List<Vehiculo> furgonetas = new ArrayList<>();
        for (Vehiculo v : flota.values()) { // Recorremos todos los vehículos
            if (v instanceof Furgoneta) { // Filtramos los de tipo Furgoneta
                furgonetas.add(v);
            }
        }
        return furgonetas;
    }

    // Método que devuelve una lista con solo los turismos
    public List<Vehiculo> listarTurismos() {
        List<Vehiculo> turismos = new ArrayList<>();
        for (Vehiculo v : flota.values()) { // Recorremos todos los vehículos
            if (v instanceof Turismo) { // Filtramos los de tipo Turismo
                turismos.add(v);
            }
        }
        return turismos;
    }

    // Método que devuelve el vehículo con el precio de alquiler más bajo
    public Vehiculo getVehiculoMasBarato(){
        return flota.isEmpty() ? null : Collections.min(flota.values(), new ComparadorPrecio());
        // `Collections.min` busca el vehículo con menor precio usando el comparador de precios
    }
    
}
*/