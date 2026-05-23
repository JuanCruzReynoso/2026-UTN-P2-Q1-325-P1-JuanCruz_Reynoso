package transporte_turistico;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author juan cruz reynoso
 */
public class Main {

    private static ArrayList<Vehiculo> flota = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Carga de Datos de prueba para agilizar pruebas y correccion
        cargarDatosPrueba();
        
        int opcion = 0;
        do {
            System.out.println("\n=== MENU DE GESTION DE TRANSPORTE TURISTICO ===");
            System.out.println("1. Agregar vehiculo");
            System.out.println("2. Mostrar todas los vehiculos");
            System.out.println("3. Realizar servicios turisticos");
            System.out.println("4. Buscar vehiculo por patente");
            System.out.println("5. Mostrar vehiculos con capacidad mayor a valor ingresado");
            System.out.println("6. Mostrar vehiculos ordenados por año de fabricacion (desc)");
            System.out.println("7. Mostrar vehiculos ordenados por capacidad de pasajeros (desc)");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");

            String input = scanner.nextLine();
            try {
                opcion = Integer.parseInt(input);
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Opcion invalida. Ingrese un número.");
            }
        } while (opcion != 8);
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarVehiculo();
                break;
            case 2:
                mostrarVehiculos(flota);
                break;
            case 3:
                realizarServicios();
                break;
            case 4:
                buscarPorPatente();
                break;
            case 5:
                filtrarPorCapacidad();
                break;
            case 6:
                ordenarPorAnioFabricacion();
                break;
            case 7:
                ordenarPorCapacidadPasajeros();
                break;
            case 8:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void agregarVehiculo() {
        System.out.println("\nTipo de Vehiculo:");
        System.out.println("1. Colectivo Turistico");
        System.out.println("2. Van ejecutiva");
        System.out.println("3. Lancha turistica");
        System.out.print("Seleccione tipo de vehiculo: ");

        int tipo = Integer.parseInt(scanner.nextLine());
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo invalido.");
            return;
        }

        System.out.print("Ingrese Patente: ");
        String patente = scanner.nextLine();
        if (patente.trim().isEmpty()) {
            System.out.println("Error: La patente no puede estar vacía.");
            return;
        }

        for (int i = 0; i < flota.size(); i++) {
            if (flota.get(i).getPatente().equalsIgnoreCase(patente)) {
                System.out.println("Error: Ya existe un vehiculo con la patente: " + patente + " en la flota.");
                return;
            }
        }

        System.out.print("Ingrese Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Ingrese Capacidad de pasajeros: ");
        int capacidad = Integer.parseInt(scanner.nextLine());
        if (capacidad <= 0) {
            System.out.println("Error: Capacidad debe ser mayor a 0.");
            return;
        }

        System.out.print("Ingrese Año de Fabricacion: ");
        int anio = Integer.parseInt(scanner.nextLine());
        if (anio < 1990 || anio > 2026) {
            System.out.println("Error: Año invalido (Rango permitido: entre 1990 y 2026).");
            return;
        }

        Vehiculo nuevaVehiculo = null;

        switch (tipo) {
            case 1:
                System.out.println("Cantidad de Pisos (1 o 2): ");
                int pisos = Integer.parseInt(scanner.nextLine());
                if (pisos != 1 && pisos != 2) {
                    System.out.println("Error: La cantidad de pisos debe ser 1 o 2.");
                    return;
                }
                flota.add(new ColectivoTuristico(patente, marca, capacidad, anio, pisos));
                break;
            case 2:
                System.out.print("¿Posee aire acondicionado? (S/N): ");
                boolean tieneAire = scanner.nextLine().equalsIgnoreCase("S");
                flota.add(new VanEjecutiva(patente, marca, capacidad, anio, tieneAire));
                break;
            case 3:
                System.out.print("Seleccione Motor: 1.NAFTA | 2.DIESEL | 3.ELECTRICO");
                int opcMotor = Integer.parseInt(scanner.nextLine());
                if (opcMotor < 1 || opcMotor > 3) {
                    System.out.println("Error: Tipo de Motor invalido.");
                    return;
                }
                TipoMotor motor = TipoMotor.values()[opcMotor - 1];
                flota.add(new LanchaTuristica(patente, marca, capacidad, anio, motor));
                break;
        }

        System.out.println("Vehiculo agregado con éxito.");
    }

    private static void mostrarVehiculos(ArrayList<Vehiculo> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay vehiculos registradas.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
    }

    private static void realizarServicios() {
        if (flota.isEmpty()) {
            System.out.println("No hay vehiculos para realizar servicios.");
            return;
        }
        for (int i = 0; i < flota.size(); i++) {
            flota.get(i).realizarServicio();
        }
    }

    private static void buscarPorPatente() {
        System.out.println("Ingrese patente a buscar: ");
        String patente = scanner.nextLine().trim();

        boolean encontrado = false;

        for (int i = 0; i < flota.size(); i++) {
            if (flota.get(i).getPatente().equalsIgnoreCase(patente)) {
                System.out.println("\nVehiculo encontrado: ");
                System.out.println(flota.get(i).toString());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Vehiculo con patente " + patente + " no encontrado.");
        }
    }

    private static void filtrarPorCapacidad() {
        System.out.println("Ingrese capacidad minima de pasajeros: ");
        int minCapacidad = Integer.parseInt(scanner.nextLine());
        boolean huboResultados = false;

        for (int i = 0; i < flota.size(); i++) {
            if (flota.get(i).getCapacidadPasajeros() > minCapacidad) {
                System.out.println(flota.get(i).toString());
                huboResultados = true;
            }
        }
        if (!huboResultados) {
            System.out.println("No hay vehiculos que superen esa capacidad.");
        }
    }

    private static void ordenarPorAnioFabricacion() {
        ArrayList<Vehiculo> copia = new ArrayList<>(flota);
        int n = copia.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (copia.get(j).getAnioFabricacion() < copia.get(j + 1).getAnioFabricacion()) {
                    Vehiculo temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }
        System.out.println("\n--- Vehiculos por Año de Fabricacion(Descendente) ---");
        mostrarVehiculos(copia);
    }

    private static void ordenarPorCapacidadPasajeros() {
        ArrayList<Vehiculo> copia = new ArrayList<>(flota);
        int n = copia.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (copia.get(j).getCapacidadPasajeros() < copia.get(j + 1).getCapacidadPasajeros()) {
                    Vehiculo temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }
        System.out.println("\n--- Vehiculos por Capacidad de Pasajeros(Descendente) ---");
        mostrarVehiculos(copia);
    }

    private static void cargarDatosPrueba() {
        flota.add(new ColectivoTuristico("AA123BB", "Mercedes Benz", 45, 2020, 2));
        flota.add(new ColectivoTuristico("AB456CD", "Scania", 50, 2022, 1));
        flota.add(new VanEjecutiva("AC789EF", "Renault", 15, 2021, true));
        flota.add(new VanEjecutiva("AD321GH", "Toyota", 12, 2019, false));
        flota.add(new LanchaTuristica("LAN001", "Yamaha", 8, 2023, TipoMotor.NAFTA));
        flota.add(new LanchaTuristica("LAN002", "Mercury", 10, 2022, TipoMotor.ELECTRICO));
    }
}
