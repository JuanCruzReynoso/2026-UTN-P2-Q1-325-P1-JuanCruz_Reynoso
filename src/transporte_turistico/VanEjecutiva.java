package transporte_turistico;

/**
 *
 * @author juan cruz reynoso
 */
public class VanEjecutiva extends Vehiculo {
    private boolean aireAcondicionado;
    
    public VanEjecutiva(String patente, String marca, int capacidadPasajeros, int anioFabricacion, boolean aireAcondicionado){
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }
    
    // agrego metodo para evitar repeticion en las sobrecargas
    private String tieneAire(boolean aireAcondicionado){
        return  aireAcondicionado ? "posee aire acondicionado" : "no posee aire acondicionado";
    }
    
    @Override
    public void realizarServicio(){
        System.out.println("Van " + getMarca() + " (Patente : " + getPatente() + " Aire: " + tieneAire(aireAcondicionado) + ") iniciando excurcion!");
    }
    
    @Override
    public String toString(){
        return super.toString() + " | Tipo: Van | Aire: " + tieneAire(aireAcondicionado);
    }        
}
