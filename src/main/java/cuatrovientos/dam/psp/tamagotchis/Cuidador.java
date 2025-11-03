package cuatrovientos.dam.psp.tamagotchis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cuidador {

	public static void main(String[] args) {
		List<Tamagotchi> tamagotchis = new ArrayList<>();
		List<String> nombresTamagotchi = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		final int MAX_VELOCIDAD_COMER = 4001;		
		String strCantidad;
		int cantidad;
		while (true) {
			System.out.println("¿Cuantos tamagotchis quieres tener?");
			strCantidad = scan.nextLine();
			try {
				cantidad = Integer.parseInt(strCantidad);
				if (cantidad < 1) {
					System.out.println("debes tener por lo menos un Tamagotchi");
				} else if (cantidad > MAX_VELOCIDAD_COMER) {
					System.out.println("Sólo puedes tener hasta " + MAX_VELOCIDAD_COMER + " Tamagotchis.");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				cantidad = 10;
				System.out.println("Por favor, introduce un numero entero.");
			}

		}
		String nuevoNombre;
		for (int i = 0; i < cantidad; i++) {
			boolean seguir;
			do {
				seguir = true;
				System.out.println("Nombra al Tamagotchi " + (i + 1) + ":");
				nuevoNombre = scan.nextLine();
				for (String nombre : nombresTamagotchi) {
					if (nuevoNombre.equals(nombre)) {
						seguir = false;
						System.out.println("Ya tienes a un tamagotchi con ese nombre");
						break;
					}
				}
			} while (seguir == false);
			nombresTamagotchi.add(nuevoNombre);
			tamagotchis.add(new Tamagotchi(nombresTamagotchi.get(i)));
			System.out.println("El tamagotchi " + tamagotchis.get(i).getNombre() + " ha sido añadido.");
		}
		
		for (Tamagotchi t : tamagotchis) {
            new Thread(t).start();
		}
		while (true) {
            System.out.println("\nAcción: 1) Comer  2) Jugar  3) Limpiar  4) Matar  5) Estado  0) Salir");
            String opcion = scan.nextLine();
            if (opcion.equals("0")) break;

            for (int i = 0; i < tamagotchis.size(); i++) {
            System.out.println((i + 1) + ") " + tamagotchis.get(i).getNombre());
            }
            System.out.print("Selecciona Tamagotchi: ");
            System.out.print("Introduce un número: ");
            int indice = scan.nextInt() - 1;
            scan.nextLine();

            if (indice < 0 || indice >= tamagotchis.size()) continue;
            Tamagotchi t = tamagotchis.get(indice);

            switch (opcion) {
                case "1": 
                	t.comer();
                	break;
                case "2": 
                	t.jugar();
                	break;
                case "3": t.limpiar();
                	break;
                case "4": t.morir();
                	break;
                case "5": 
                	if(t.estaVivo()) {
                		System.out.println(t.getNombre() + " está vivo");
                	}else {
                		System.out.println(t.getNombre() + " está muerto");
                	}
                	break;
                default:
                	System.out.println("Introduce una opción válida");
            }
        }

        System.out.println("Cerrando programa cuidador...");
        scan.close();
	}
}
