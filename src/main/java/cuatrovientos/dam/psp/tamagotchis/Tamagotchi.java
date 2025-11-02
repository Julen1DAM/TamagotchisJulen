package cuatrovientos.dam.psp.tamagotchis;

import java.util.Random;

public class Tamagotchi implements Runnable {
	private String nombre;
	private boolean vivo = true;
	private boolean ocupado = false;
	private long inicioVida;
    private int suciedad = 0;
    private long velocidadComer;
    
    public Tamagotchi(String nombre) {
        this.nombre = nombre;
        this.inicioVida = System.currentTimeMillis();
        this.velocidadComer = (long)(Math.random() * 3001);
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
		if(!vivo) {
			System.out.println(nombre + " no puede comer, está muerto");
		}else if(ocupado){
			System.out.println(nombre + " no puede comer, está ocupado");
		}
		ocupado = true;
		System.out.println("¡" + nombre + " ha empezado a comer!");
		try {
			Thread.sleep(velocidadComer);
		} catch (InterruptedException e) {
			System.out.println("Ha terminado de comer");
			ocupado = false;
		}
	}

}
