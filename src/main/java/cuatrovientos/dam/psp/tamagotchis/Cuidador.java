package cuatrovientos.dam.psp.tamagotchis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Cuidador {

	public static void main(String[] args) {
		List<Tamagotchi> tamagotchis = new ArrayList<>();
		List<String> nombresTamagotchi = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		String strCantidad;
		int cantidad;
		while (true) {
			System.out.println("¿Cuantos tamagotchis quieres tener?");
			strCantidad = scan.nextLine();
			try {
				cantidad = Integer.parseInt(strCantidad);
				if (cantidad < 1) {
					System.out.println("debes tener por lo menos un Tamagotchi");
				} else if (cantidad > 9) {
					System.out.println("Sólo puedes tener hasta 9 Tamagotchis.");
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

	}
}
