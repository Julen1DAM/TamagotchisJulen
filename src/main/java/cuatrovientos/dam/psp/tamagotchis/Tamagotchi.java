package cuatrovientos.dam.psp.tamagotchis;

import java.util.Scanner;

public class Tamagotchi implements Runnable {
	Scanner scan = new Scanner(System.in);
	private String nombre;
	private boolean vivo = true;
	private boolean ocupado = false;
	private long inicioVida;
	private int suciedad = 0;
	private long velocidadComer;

	public Tamagotchi(String nombre) {
		this.nombre = nombre;
		this.inicioVida = System.currentTimeMillis();
		this.velocidadComer = (long) (Math.random() * 3001);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean estaVivo() {
		return vivo;
	}

	public void comer() {
		if (!vivo) {
			System.out.println(nombre + " no puede comer, está muerto/a");
		} else if (ocupado) {
			System.out.println(nombre + " no puede comer, está ocupado/a");
		}
		ocupado = true;
		new Thread(() -> {
			System.out.println("¡" + nombre + " ha empezado a comer!");
			try {
				Thread.sleep(velocidadComer);
			} catch (InterruptedException e) {
			}
			System.out.println("Ha terminado de comer");
			ocupado = false;
		}).start();
	}

	public void jugar() {
		if (!vivo) {
			System.out.println(nombre + " no puede jugar, está muerto/a");
		} else if (ocupado) {
			System.out.println(nombre + " no puede jugar, está ocupado/a");
		}
		new Thread(() -> {
			int num1, num2, resultado, respuesta;
			String StrRespuesta;
			do {
				num1 = (int) (Math.random() * 10);
				num2 = (int) (Math.random() * 10);
				resultado = num1 + num2;
			} while (resultado < 10);
			while (true) {
				System.out.println(nombre + " pregunta: ¿Cuánto es " + num1 + " + " + num2 + "?");
				StrRespuesta = scan.nextLine();
				try {
					respuesta = Integer.parseInt(StrRespuesta);
					if (respuesta == resultado) {
						System.out.println("¡Has acertado! " + nombre + " se ha divertido");
						break;
					}
				} catch (NumberFormatException e) {
					respuesta = 10;
					System.out.println(nombre + " se confunde. Buscaba un numero entero.");
				}
			}
			;
		});
	}

}
