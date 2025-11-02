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
		while (vivo) {
			try {
				Thread.sleep((long) ((Math.random() * 20001) + 10000));
				suciedad++;
				if (suciedad == 5) System.out.println(nombre + " está sucio/a, lávalo/a");
				if (suciedad == 10) {
					System.out.println(nombre + " estaba demasiado sucio/a y se ha enfermado por ello.");
					morir();
					break;
				}
				 if (System.currentTimeMillis() - inicioVida >= 300000) {
	                    System.out.println(nombre + " ha tenido una buena vida.");
	                    morir();
	                    break;
	                }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

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
			return;
		} else if (ocupado) {
			System.out.println(nombre + " no puede comer, está ocupado/a");
			return;
		}
		ocupado = true;
		Thread hilo = new Thread(() -> {
			System.out.println("¡" + nombre + " ha empezado a comer!");
			try {
				Thread.sleep(velocidadComer);
			} catch (InterruptedException e) {
			}
			System.out.println("Ha terminado de comer");
		});
		hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {}
        ocupado = false;
	}

	public void jugar() {
		if (!vivo) {
			System.out.println(nombre + " no puede jugar, está muerto/a");
			return;
		} else if (ocupado) {
			System.out.println(nombre + " no puede jugar, está ocupado/a");
			return;
		}
		Thread hilo = new Thread(() -> {
			int num1, num2, resultado, respuesta;
			String StrRespuesta;
			do {
				num1 = (int) (Math.random() * 10);
				num2 = (int) (Math.random() * 10);
				resultado = num1 + num2;
			} while (resultado > 10);
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
		});
		hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {}
        ocupado = false;
	}
	public void limpiar() {
		if (!vivo) {
			System.out.println(nombre + " no puede jugar, está muerto/a");
			return;
		} else if (ocupado) {
			System.out.println(nombre + " no puede jugar, está ocupado/a");
			return;
		}
        ocupado = true;
        Thread hilo = new Thread(() -> {
            System.out.println(nombre + " se está bañando");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {}
            suciedad = 0;
            System.out.println(nombre + " ya está limpio/a");
            ocupado = false;
        });
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {}
        ocupado = false;
    }
	public void morir() {
		if (!vivo) {
			System.out.println(nombre + " no puede morir, ya está muerto/a");
		}
		if(ocupado) {
			System.out.println("a " + nombre + " no le apetece morir, está ocupado/a");
			return;
		}
		vivo = false;
		System.out.println(nombre + " ha muerto");
	}
}
