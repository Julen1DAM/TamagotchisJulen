package cuatrovientos.dam.psp.tamagotchis;

public class Tamagotchi implements Runnable {
	private String nombre;
	private boolean vivo = true;
	private boolean ocupado = false;
	private long inicioVida;
    private int suciedad = 0;
    
    public Tamagotchi(String nombre) {
        this.nombre = nombre;
        this.inicioVida = System.currentTimeMillis();
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

}
