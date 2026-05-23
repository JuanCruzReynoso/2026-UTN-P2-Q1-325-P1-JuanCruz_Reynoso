package transporte_turistico;

/**
 *
 * @author juan cruz reynoso
 */
public abstract class Vehiculo {
    private String patente;
    private String marca;
    private int capacidadPasajeros;
    private int anioFabricacion;
    
    public Vehiculo(String patente, String marca, int capacidadPasajeros, int anioFabricacion){
        this.patente = patente;
        this.marca = marca;
        this.capacidadPasajeros = capacidadPasajeros;
        this.anioFabricacion = anioFabricacion;
    }
    
    public String getPatente() { return patente; }
    public String getMarca() { return marca; }
    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public int getAnioFabricacion() { return anioFabricacion; }
    
    // Polimorfismo para el requerimiento de Realizar Servicios
    public abstract void realizarServicio();
    
    @Override
    public String toString(){
        return String.format("Patente: %s | Marca: %s | Capacidad: %d | Año: %d", patente, marca, capacidadPasajeros, anioFabricacion);
    }
}
