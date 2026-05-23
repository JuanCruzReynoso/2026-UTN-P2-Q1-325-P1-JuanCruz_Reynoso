package transporte_turistico;

/**
 *
 * @author juan cruz reynoso
 */
public class ColectivoTuristico extends Vehiculo {
    private int cantidadPisos;
    
    public ColectivoTuristico(String patente, String marca, int capacidadPasajeros, int anioFabricacion, int cantidadPisos){
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.cantidadPisos = cantidadPisos;
    }
    
    @Override
    public void realizarServicio(){
        System.out.println("Colectivo " + getMarca() + " (Patente : " + getPatente() + " Pisos: " + cantidadPisos + ") iniciando excurcion!");
    }
    
    @Override
    public String toString(){
        return super.toString() + " | Tipo: Colectivo | Pisos: " + cantidadPisos;
    }        
}
