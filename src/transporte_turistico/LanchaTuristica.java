
package transporte_turistico;

/**
 *
 * @author juan cruz reynoso
 */
public class LanchaTuristica extends Vehiculo{
    private TipoMotor tipoMotor;
    
    public LanchaTuristica(String patente, String marca, int capacidadPasajeros, int anioFabricacion, TipoMotor tipoMotor){
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.tipoMotor = tipoMotor;
    }
        
    @Override
    public void realizarServicio(){
        System.out.println("Lancha " + getMarca() + " (Patente : " + getPatente() + " Motor: " + tipoMotor + ") iniciando excurcion!");
    }
    
    @Override
    public String toString(){
        return super.toString() + " | Tipo: Lancha | Motor: " + tipoMotor;
    }        
}
